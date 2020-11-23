package kit.pano.febs.web.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kit.pano.febs.common.domain.FebsConst;
import kit.pano.febs.common.domain.FebsOrderConst;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.utils.FebsUtil;
import kit.pano.febs.common.utils.SortUtil;
import kit.pano.febs.system.domain.po.User;
import kit.pano.febs.web.dao.LitemallOrderMapper;
import kit.pano.febs.web.domain.mapper.LitemallOrderStructMapper;
import kit.pano.febs.web.domain.po.LitemallOrder;
import kit.pano.febs.web.domain.vo.LitemallOrderLogVO;
import kit.pano.febs.web.domain.vo.LitemallOrderVO;
import kit.pano.febs.web.service.LitemallOrderLogService;
import kit.pano.febs.web.service.LitemallOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 商城订单表 服务实现类
 * </p>
 *
 * @author Pano
 * @since 2019-09-22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LitemallOrderServiceImpl extends ServiceImpl<LitemallOrderMapper, LitemallOrder> implements LitemallOrderService {

    @Resource
    private LitemallOrderLogService litemallOrderLogService;
    @Resource
    private LitemallOrderStructMapper litemallOrderStructMapper;

    @Override
    public void addOrder(LitemallOrderVO orderVO) {

        //新增商品订单
        LitemallOrder order = litemallOrderStructMapper.toPo(orderVO);

        //生成订单编号
        //订单号生成规则：订单号由16位数字组成，按下单时间取年月日8位+8位随机数，保证订单号不重复；
        String orderSn = DateTime.now().toString("yyyyMMdd") + RandomUtil.randomNumbers(8);
        order.setOrderSn(orderSn)
                //订单状态默认为待支付
                .setOrderStatus(FebsOrderConst.PENDING_PAYMENT)
                .setAddTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now())
                .setDeleted(FebsConst.NO);

        baseMapper.insert(order);

        //新增订单后，增加订单日志
        //获取当前用户
        User currentUser = FebsUtil.getCurrentUser();
        LitemallOrderLogVO logVO = new LitemallOrderLogVO()
                .setOrderId(order.getId())
                .setOrderSn(orderSn)
                .setOperator(currentUser.getUsername());

        litemallOrderLogService.addOrderLog(logVO);
    }

    @Override
    public IPage<LitemallOrder> listOrder(LitemallOrderVO order, QueryRequest request) {

        try {
            LambdaQueryWrapper<LitemallOrder> queryWrapper = new LambdaQueryWrapper<>();

            //匹配SN码
            queryWrapper.like(StringUtils.isNotBlank(order.getOrderSn()), LitemallOrder::getOrderSn, order.getOrderSn());
            //匹配状态
            queryWrapper.like(ObjectUtil.isNotNull(order.getOrderStatus()), LitemallOrder::getOrderStatus, order.getOrderStatus());
            //时间筛选
            queryWrapper.gt(ObjectUtil.isNotNull(order.getSearchStartTime()), LitemallOrder::getAddTime, order.getSearchStartTime());
            queryWrapper.lt(ObjectUtil.isNotNull(order.getSearchEndTime()), LitemallOrder::getAddTime, order.getSearchEndTime());

            //未删除的数据
            queryWrapper.eq(LitemallOrder::getDeleted, FebsConst.NO);

            Page<LitemallOrder> page = new Page<>();
            SortUtil.handlePageSort(request, page, true);
            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取商品订单信息失败", e);
            return null;
        }
    }

    @Override
    public void updateOrder(Long id, LitemallOrderVO orderVO) {

        // 修改商品订单信息
        LitemallOrder order = litemallOrderStructMapper.toPo(orderVO);
        order.setId(id);
        order.setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(order);
    }

    @Override
    public void deleteOrder(Long id) {
        LitemallOrder order = new LitemallOrder();
        order.setId(id);
        order.setDeleted(FebsConst.YES);
        order.setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(order);
    }
}

package kit.pano.febs.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kit.pano.febs.common.domain.FebsConst;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.utils.SortUtil;
import kit.pano.febs.web.dao.LitemallOrderLogMapper;
import kit.pano.febs.web.domain.mapper.LitemallOrderLogStructMapper;
import kit.pano.febs.web.domain.po.LitemallOrderLog;
import kit.pano.febs.web.domain.vo.LitemallOrderLogVO;
import kit.pano.febs.web.service.LitemallOrderLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品订单日志 服务实现类
 * </p>
 *
 * @author Pano
 * @since 2019-09-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LitemallOrderLogServiceImpl extends ServiceImpl<LitemallOrderLogMapper, LitemallOrderLog> implements LitemallOrderLogService {

    @Resource
    private LitemallOrderLogStructMapper litemallOrderLogStructMapper;

    @Override
    public void addOrderLog(LitemallOrderLogVO orderLogVO) {

        // 新增订单日志
        LitemallOrderLog orderLog = litemallOrderLogStructMapper.toPo(orderLogVO);
        orderLog.setAddTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now())
                .setDeleted(FebsConst.NO);

        baseMapper.insert(orderLog);
    }

    @Override
    public IPage<LitemallOrderLog> listOrderLog(LitemallOrderLogVO orderLog, QueryRequest request) {

        try {
            LambdaQueryWrapper<LitemallOrderLog> queryWrapper = new LambdaQueryWrapper<>();

            //匹配操作者
            queryWrapper.like(StringUtils.isNotBlank(orderLog.getOperator()), LitemallOrderLog::getOperator, orderLog.getOperator());
            //未删除的数据
            queryWrapper.eq(LitemallOrderLog::getDeleted, FebsConst.NO);

            Page<LitemallOrderLog> page = new Page<>();
            SortUtil.handlePageSort(request, page, true);
            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取订单日志信息失败", e);
            return null;
        }
    }

    @Override
    public void updateOrderLog(Long id, LitemallOrderLogVO orderLogVO) {

        // 修改订单日志信息
        LitemallOrderLog orderLog = litemallOrderLogStructMapper.toPo(orderLogVO);
        orderLog.setId(id)
                .setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(orderLog);
    }

    @Override
    public void deleteOrderLog(Long id) {
        LitemallOrderLog orderLog = new LitemallOrderLog();
        orderLog.setId(id)
                .setDeleted(FebsConst.YES)
                .setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(orderLog);
    }
}

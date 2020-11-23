package kit.pano.febs.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kit.pano.febs.common.domain.FebsConst;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.utils.SortUtil;
import kit.pano.febs.web.dao.LitemallCustomerMapper;
import kit.pano.febs.web.domain.mapper.LitemallCustomerStructMapper;
import kit.pano.febs.web.domain.po.LitemallCustomer;
import kit.pano.febs.web.domain.vo.LitemallCustomerVO;
import kit.pano.febs.web.service.LitemallCustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 商城顾客表 服务实现类
 * </p>
 *
 * @author Pano
 * @since 2019-09-22
 */
@Service
public class LitemallCustomerServiceImpl extends ServiceImpl<LitemallCustomerMapper, LitemallCustomer> implements LitemallCustomerService {

    @Resource
    private LitemallCustomerStructMapper litemallCustomerStructMapper;

    @Override
    public void addCustomer(LitemallCustomerVO customerVO) {

        // 新增顾客类目
        LitemallCustomer customer = litemallCustomerStructMapper.toPo(customerVO);
        customer.setAddTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now())
                .setDeleted(FebsConst.NO);

        baseMapper.insert(customer);
    }

    @Override
    public IPage<LitemallCustomer> listCustomer(LitemallCustomerVO customer, QueryRequest request) {

        try {
            LambdaQueryWrapper<LitemallCustomer> queryWrapper = new LambdaQueryWrapper<>();

            //客户名称匹配
            queryWrapper.like(StringUtils.isNotBlank(customer.getUsername()), LitemallCustomer::getUsername, customer.getUsername());
            //未删除的数据
            queryWrapper.eq(LitemallCustomer::getDeleted, FebsConst.NO);

            Page<LitemallCustomer> page = new Page<>();
            SortUtil.handlePageSort(request, page, true);
            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取顾客类目信息失败", e);
            return null;
        }
    }

    @Override
    public void updateCustomer(Long id, LitemallCustomerVO customerVO) {

        // 修改顾客类目信息
        LitemallCustomer customer = litemallCustomerStructMapper.toPo(customerVO);
        customer.setId(id)
                .setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        LitemallCustomer customer = new LitemallCustomer();
        customer.setId(id)
                .setDeleted(FebsConst.YES)
                .setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(customer);
    }
}

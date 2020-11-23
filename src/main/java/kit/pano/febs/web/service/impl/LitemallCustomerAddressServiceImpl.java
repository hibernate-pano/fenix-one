package kit.pano.febs.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kit.pano.febs.common.domain.FebsConst;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.utils.SortUtil;
import kit.pano.febs.web.dao.LitemallCustomerAddressMapper;
import kit.pano.febs.web.domain.mapper.LitemallCustomerAddressStructMapper;
import kit.pano.febs.web.domain.po.LitemallCustomerAddress;
import kit.pano.febs.web.domain.vo.LitemallCustomerAddressVO;
import kit.pano.febs.web.service.LitemallCustomerAddressService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 顾客收货地址表 服务实现类
 * </p>
 *
 * @author Pano
 * @since 2019-09-22
 */
@Service
public class LitemallCustomerAddressServiceImpl extends ServiceImpl<LitemallCustomerAddressMapper, LitemallCustomerAddress> implements LitemallCustomerAddressService {

    @Resource
    private LitemallCustomerAddressStructMapper litemallCustomerAddressStructMapper;

    @Override
    public void addCustomerAddress(LitemallCustomerAddressVO customerAddressVO) {

        // 新增商品类目
        LitemallCustomerAddress customerAddress = litemallCustomerAddressStructMapper.toPo(customerAddressVO);
        customerAddress.setAddTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now())
                .setDeleted(FebsConst.NO);

        baseMapper.insert(customerAddress);
    }

    @Override
    public IPage<LitemallCustomerAddress> listCustomerAddress(LitemallCustomerAddressVO customerAddress, QueryRequest request) {

        try {
            LambdaQueryWrapper<LitemallCustomerAddress> queryWrapper = new LambdaQueryWrapper<>();

            //姓名匹配
            queryWrapper.like(StringUtils.isNotBlank(customerAddress.getName()), LitemallCustomerAddress::getName, customerAddress.getName());
            //未删除的数据
            queryWrapper.eq(LitemallCustomerAddress::getDeleted, FebsConst.NO);

            Page<LitemallCustomerAddress> page = new Page<>();
            SortUtil.handlePageSort(request, page, true);
            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取商品类目信息失败", e);
            return null;
        }
    }

    @Override
    public void updateCustomerAddress(Long id, LitemallCustomerAddressVO customerAddressVO) {

        // 修改商品类目信息
        LitemallCustomerAddress customerAddress = litemallCustomerAddressStructMapper.toPo(customerAddressVO);
        customerAddress.setId(id);
        customerAddress.setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(customerAddress);
    }

    @Override
    public void deleteCustomerAddress(Long id) {
        LitemallCustomerAddress customerAddress = new LitemallCustomerAddress();
        customerAddress.setId(id);
        customerAddress.setDeleted(FebsConst.YES);
        customerAddress.setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(customerAddress);
    }
}

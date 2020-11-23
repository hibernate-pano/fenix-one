package kit.pano.febs.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.web.domain.po.LitemallCustomerAddress;
import kit.pano.febs.web.domain.vo.LitemallCustomerAddressVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 顾客收货地址表 服务类
 * </p>
 *
 * @author Pano
 * @since 2019-09-22
 */
public interface LitemallCustomerAddressService extends IService<LitemallCustomerAddress> {

    void addCustomerAddress(@Param("customerAddress") LitemallCustomerAddressVO customerAddressVO);

    IPage<LitemallCustomerAddress> listCustomerAddress(@Param("customerAddress") LitemallCustomerAddressVO customerAddressVO, @Param("request") QueryRequest queryRequest);

    void updateCustomerAddress(@Param("id") Long id, @Param("customerAddress") LitemallCustomerAddressVO customerAddressVO);

    void deleteCustomerAddress(@Param("id") Long id);
}

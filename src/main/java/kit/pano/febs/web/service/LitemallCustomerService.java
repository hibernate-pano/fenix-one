package kit.pano.febs.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.web.domain.po.LitemallCustomer;
import kit.pano.febs.web.domain.vo.LitemallCustomerVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商城顾客表 服务类
 * </p>
 *
 * @author Pano
 * @since 2019-09-22
 */
public interface LitemallCustomerService extends IService<LitemallCustomer> {

    void addCustomer(@Param("customer") LitemallCustomerVO customerVO);

    IPage<LitemallCustomer> listCustomer(@Param("customer") LitemallCustomerVO customerVO, @Param("request") QueryRequest queryRequest);

    void updateCustomer(@Param("id") Long id, @Param("customer") LitemallCustomerVO customerVO);

    void deleteCustomer(@Param("id") Long id);
}

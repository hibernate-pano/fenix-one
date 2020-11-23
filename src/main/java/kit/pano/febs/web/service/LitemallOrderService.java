package kit.pano.febs.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.web.domain.po.LitemallOrder;
import kit.pano.febs.web.domain.vo.LitemallOrderVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商城订单表 服务类
 * </p>
 *
 * @author Pano
 * @since 2019-09-22
 */
public interface LitemallOrderService extends IService<LitemallOrder> {

    void addOrder(@Param("order") LitemallOrderVO orderVO);

    IPage<LitemallOrder> listOrder(@Param("order") LitemallOrderVO orderVO, @Param("request") QueryRequest queryRequest);

    void updateOrder(@Param("id") Long id, @Param("order") LitemallOrderVO orderVO);

    void deleteOrder(@Param("id") Long id);
}

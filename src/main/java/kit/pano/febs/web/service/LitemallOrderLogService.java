package kit.pano.febs.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.web.domain.po.LitemallOrderLog;
import kit.pano.febs.web.domain.vo.LitemallOrderLogVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品订单日志 服务类
 * </p>
 *
 * @author Pano
 * @since 2019-09-23
 */
public interface LitemallOrderLogService extends IService<LitemallOrderLog> {

    void addOrderLog(@Param("orderLog") LitemallOrderLogVO orderLogVO);

    IPage<LitemallOrderLog> listOrderLog(@Param("orderLog") LitemallOrderLogVO orderLogVO, @Param("request") QueryRequest queryRequest);

    void updateOrderLog(@Param("id") Long id, @Param("orderLog") LitemallOrderLogVO orderLogVO);

    void deleteOrderLog(@Param("id") Long id);
}

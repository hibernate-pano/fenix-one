package kit.pano.febs.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.web.domain.po.LitemallShop;
import kit.pano.febs.web.domain.vo.LitemallShopVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author pano
 * @since 2019-07-15
 */
public interface LitemallShopService extends IService<LitemallShop> {

    void addShop(@Param("shop") LitemallShopVO shopVO);

    IPage<LitemallShop> listShop(@Param("shop") LitemallShopVO shopVO, @Param("request") QueryRequest queryRequest);

    void updateShop(@Param("id") Long id, @Param("shop") LitemallShopVO shopVO);

    void deleteShop(@Param("id") Long id);

    void changeStatus(@Param("id") Long id, @Param("status") Integer status);
}

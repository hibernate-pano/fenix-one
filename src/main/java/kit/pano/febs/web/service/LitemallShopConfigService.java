package kit.pano.febs.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import kit.pano.febs.web.domain.po.LitemallShopConfig;
import kit.pano.febs.web.domain.vo.LitemallShopConfigVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Pano
 * @since 2019-09-07
 */
public interface LitemallShopConfigService extends IService<LitemallShopConfig> {

    void addShopConfig(@Param("shopConfig") LitemallShopConfigVO shopConfigVO);

    void updateShopConfig(@Param("id") Long id, @Param("shopConfig") LitemallShopConfigVO shopConfigVO);

    void deleteShopConfig(@Param("id") Long id);

}

package kit.pano.febs.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.web.domain.po.LitemallShopkeeper;
import kit.pano.febs.web.domain.vo.LitemallShopkeeperVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 店主表 服务类
 * </p>
 *
 * @author pano
 * @since 2019-07-15
 */
public interface LitemallShopkeeperService extends IService<LitemallShopkeeper> {

    void addShopkeeper(@Param("shopkeeper") LitemallShopkeeperVO shopkeeperVO) throws Exception;

    void updateShopkeeper(@Param("id") Long id, @Param("shopkeeper") LitemallShopkeeperVO shopkeeperVO) throws Exception;

    void deleteShopkeeper(@Param("id") Long id, String phone) throws Exception;

    LitemallShopkeeperVO queryOneById(@Param("id") Long id);

    void changeStatus(@Param("id") Long id, @Param("status") Integer status) throws Exception;

    IPage<LitemallShopkeeperVO> listShopkeeper(@Param("shopkeeper") LitemallShopkeeperVO shopkeeper, @Param("request") QueryRequest request);

}

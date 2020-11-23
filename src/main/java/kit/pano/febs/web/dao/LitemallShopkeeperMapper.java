package kit.pano.febs.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import kit.pano.febs.web.domain.po.LitemallShopkeeper;
import kit.pano.febs.web.domain.vo.LitemallShopkeeperVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author pano
 * @since 2019-07-15
 */
public interface LitemallShopkeeperMapper extends BaseMapper<LitemallShopkeeper> {

    LitemallShopkeeperVO queryOneById(@Param("id") Long id);

    IPage<LitemallShopkeeperVO> listShopkeeper(@Param("page") Page<LitemallShopkeeperVO> page, @Param("shopkeeper") LitemallShopkeeperVO shopkeeper);
}

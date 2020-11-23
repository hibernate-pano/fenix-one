package kit.pano.febs.web.domain.mapper;

import kit.pano.febs.web.domain.po.LitemallShopConfig;
import kit.pano.febs.web.domain.vo.LitemallShopConfigVO;
import org.mapstruct.Mapper;

/**
 * @author Pano
 */
@Mapper(componentModel = "spring")
public interface LitemallShopConfigStructMapper {

    LitemallShopConfig toPo(LitemallShopConfigVO vo);
}

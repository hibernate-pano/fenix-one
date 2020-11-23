package kit.pano.febs.web.domain.mapper;

import kit.pano.febs.web.domain.po.LitemallShop;
import kit.pano.febs.web.domain.vo.LitemallShopVO;
import org.mapstruct.Mapper;

/**
 * @author Pano
 */
@Mapper(componentModel = "spring")
public interface LitemallShopStructMapper {

    LitemallShop toPo(LitemallShopVO vo);
}

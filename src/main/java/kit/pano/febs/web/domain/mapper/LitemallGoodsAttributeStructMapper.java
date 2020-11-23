package kit.pano.febs.web.domain.mapper;

import kit.pano.febs.web.domain.po.LitemallGoodsAttribute;
import kit.pano.febs.web.domain.vo.LitemallGoodsAttributeVO;
import org.mapstruct.Mapper;

/**
 * @author Pano
 */
@Mapper(componentModel = "spring")
public interface LitemallGoodsAttributeStructMapper {

    LitemallGoodsAttribute toPo(LitemallGoodsAttributeVO vo);
}

package kit.pano.febs.web.domain.mapper;

import kit.pano.febs.web.domain.po.LitemallGoodsProduct;
import kit.pano.febs.web.domain.vo.LitemallGoodsProductVO;
import org.mapstruct.Mapper;

/**
 * @author Pano
 */
@Mapper(componentModel = "spring")
public interface LitemallGoodsProductStructMapper {

    LitemallGoodsProduct toPo(LitemallGoodsProductVO vo);
}

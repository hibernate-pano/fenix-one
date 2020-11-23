package kit.pano.febs.web.domain.mapper;

import kit.pano.febs.web.domain.po.LitemallGoods;
import kit.pano.febs.web.domain.vo.LitemallGoodsVO;
import org.mapstruct.Mapper;

/**
 * @author Pano
 */
@Mapper(componentModel = "spring")
public interface LitemallGoodsStructMapper {

    LitemallGoods toPo(LitemallGoodsVO vo);
}

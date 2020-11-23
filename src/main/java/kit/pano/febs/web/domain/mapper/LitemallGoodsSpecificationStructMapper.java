package kit.pano.febs.web.domain.mapper;

import kit.pano.febs.web.domain.po.LitemallGoodsSpecification;
import kit.pano.febs.web.domain.vo.LitemallGoodsSpecificationVO;
import org.mapstruct.Mapper;

/**
 * @author Pano
 */
@Mapper(componentModel = "spring")
public interface LitemallGoodsSpecificationStructMapper {

    LitemallGoodsSpecification toPo(LitemallGoodsSpecificationVO vo);
}

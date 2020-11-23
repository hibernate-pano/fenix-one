package kit.pano.febs.web.domain.mapper;

import kit.pano.febs.web.domain.po.LitemallCategory;
import kit.pano.febs.web.domain.vo.LitemallCategoryVO;
import org.mapstruct.Mapper;

/**
 * @author Pano
 */
@Mapper(componentModel = "spring")
public interface LitemallCategoryStructMapper {

    LitemallCategory toPo(LitemallCategoryVO vo);
}

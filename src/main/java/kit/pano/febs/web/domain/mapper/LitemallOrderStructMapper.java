package kit.pano.febs.web.domain.mapper;

import kit.pano.febs.web.domain.po.LitemallOrder;
import kit.pano.febs.web.domain.vo.LitemallOrderVO;
import org.mapstruct.Mapper;

/**
 * @author Pano
 */
@Mapper(componentModel = "spring")
public interface LitemallOrderStructMapper {

    LitemallOrder toPo(LitemallOrderVO vo);
}

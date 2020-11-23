package kit.pano.febs.web.domain.mapper;

import kit.pano.febs.web.domain.po.LitemallDeliver;
import kit.pano.febs.web.domain.vo.LitemallDeliverVO;
import org.mapstruct.Mapper;

/**
 * @author Pano
 */
@Mapper(componentModel = "spring")
public interface LitemallDeliverStructMapper {

    LitemallDeliver toPo(LitemallDeliverVO vo);
}

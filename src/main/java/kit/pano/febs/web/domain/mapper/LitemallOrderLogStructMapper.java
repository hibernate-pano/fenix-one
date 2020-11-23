package kit.pano.febs.web.domain.mapper;

import kit.pano.febs.web.domain.po.LitemallOrderLog;
import kit.pano.febs.web.domain.vo.LitemallOrderLogVO;
import org.mapstruct.Mapper;

/**
 * @author Pano
 */
@Mapper(componentModel = "spring")
public interface LitemallOrderLogStructMapper {

    LitemallOrderLog toPo(LitemallOrderLogVO vo);
}

package kit.pano.febs.web.domain.mapper;

import kit.pano.febs.web.domain.po.LitemallNurse;
import kit.pano.febs.web.domain.vo.LitemallNurseVO;
import org.mapstruct.Mapper;

/**
 * @author Pano
 */
@Mapper(componentModel = "spring")
public interface LitemallNurseStructMapper {

    LitemallNurse toPo(LitemallNurseVO vo);
}

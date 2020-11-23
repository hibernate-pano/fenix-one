package kit.pano.febs.web.domain.mapper;

import kit.pano.febs.web.domain.po.LitemallRunner;
import kit.pano.febs.web.domain.vo.LitemallRunnerVO;
import org.mapstruct.Mapper;

/**
 * @author Pano
 */
@Mapper(componentModel = "spring")
public interface LitemallRunnerStructMapper {

    LitemallRunner toPo(LitemallRunnerVO vo);
}

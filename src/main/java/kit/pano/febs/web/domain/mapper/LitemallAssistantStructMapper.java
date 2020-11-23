package kit.pano.febs.web.domain.mapper;

import kit.pano.febs.system.domain.po.User;
import kit.pano.febs.web.domain.po.LitemallAssistant;
import kit.pano.febs.web.domain.vo.LitemallAssistantVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author Pano
 */
@Mapper(componentModel = "spring")
public interface LitemallAssistantStructMapper {

    LitemallAssistant toPo(LitemallAssistantVO vo);

    @Mappings({
            @Mapping(target = "username", source = "phone"),
    })
    User toUserPo(LitemallAssistantVO vo);
}

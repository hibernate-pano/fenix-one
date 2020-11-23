package kit.pano.febs.web.domain.mapper;

import kit.pano.febs.system.domain.po.User;
import kit.pano.febs.web.domain.po.LitemallShopkeeper;
import kit.pano.febs.web.domain.vo.LitemallShopkeeperVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author Pano
 */
@Mapper(componentModel = "spring")
public interface LitemallShopkeeperStructMapper {

    LitemallShopkeeper toPo(LitemallShopkeeperVO vo);

    /**
     * @param shopkeeperVO 店主信息
     */
    @Mappings({
            @Mapping(target = "username", source = "phone")
    })
    User toUserPo(LitemallShopkeeperVO shopkeeperVO);
}

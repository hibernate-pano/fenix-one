package kit.pano.febs.web.domain.mapper;

import kit.pano.febs.web.domain.po.LitemallCustomer;
import kit.pano.febs.web.domain.vo.LitemallCustomerVO;
import org.mapstruct.Mapper;

/**
 * @author Pano
 */
@Mapper(componentModel = "spring")
public interface LitemallCustomerStructMapper {

    LitemallCustomer toPo(LitemallCustomerVO vo);
}

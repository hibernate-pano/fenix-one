package kit.pano.febs.web.domain.mapper;

import kit.pano.febs.web.domain.po.LitemallCustomerAddress;
import kit.pano.febs.web.domain.vo.LitemallCustomerAddressVO;
import org.mapstruct.Mapper;

/**
 * Created with IntelliJ IDEA.
 * Date: 2019-09-23
 * Time: 10:42
 *
 * @author Pano
 */
@Mapper(componentModel = "spring")
public interface LitemallCustomerAddressStructMapper {

    LitemallCustomerAddress toPo(LitemallCustomerAddressVO vo);
}

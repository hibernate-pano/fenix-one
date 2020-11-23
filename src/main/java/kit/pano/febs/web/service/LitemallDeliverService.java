package kit.pano.febs.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.web.domain.po.LitemallDeliver;
import kit.pano.febs.web.domain.vo.LitemallDeliverVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商城配送人员 服务类
 * </p>
 *
 * @author Pano
 * @since 2019-10-26
 */
public interface LitemallDeliverService extends IService<LitemallDeliver> {

    void addDeliver(@Param("deliver") LitemallDeliverVO deliverVO);

    void updateDeliver(@Param("id") Long id, @Param("deliver") LitemallDeliverVO deliverVO);

    void deleteDeliver(@Param("id") Long id);

    IPage<LitemallDeliver> listDeliver(@Param("deliver") LitemallDeliverVO deliver, @Param("request") QueryRequest request);

    void changeStatus(@Param("id") Long id, @Param("status") Integer status);
}

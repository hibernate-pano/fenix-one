package kit.pano.febs.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.web.domain.po.LitemallNurse;
import kit.pano.febs.web.domain.vo.LitemallNurseVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商城护工人员 服务类
 * </p>
 *
 * @author Pano
 * @since 2019-10-26
 */
public interface LitemallNurseService extends IService<LitemallNurse> {

    void addNurse(@Param("nurse") LitemallNurseVO nurseVO);

    void updateNurse(@Param("id") Long id, @Param("nurse") LitemallNurseVO nurseVO);

    void deleteNurse(@Param("id") Long id);

    IPage<LitemallNurse> listNurse(@Param("nurse") LitemallNurseVO nurse, @Param("request") QueryRequest request);

    void changeStatus(@Param("id") Long id, @Param("status") Integer status);

    void changeOnline(@Param("id") Long id, @Param("online") Integer online);
}

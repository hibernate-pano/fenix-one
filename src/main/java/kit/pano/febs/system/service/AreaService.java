package kit.pano.febs.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.system.domain.po.Area;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Pano
 * @since 2019-11-03
 */
public interface AreaService extends IService<Area> {

    IPage<Area> listArea(@Param("area") Area area, @Param("queryRequest") QueryRequest queryRequest);
}

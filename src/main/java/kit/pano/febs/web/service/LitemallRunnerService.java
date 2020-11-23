package kit.pano.febs.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.web.domain.po.LitemallRunner;
import kit.pano.febs.web.domain.vo.LitemallRunnerVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商城配送人员 服务类
 * </p>
 *
 * @author Pano
 * @since 2019-10-26
 */
public interface LitemallRunnerService extends IService<LitemallRunner> {

    void addRunner(@Param("runner") LitemallRunnerVO runnerVO);

    void updateRunner(@Param("id") Long id, @Param("runner") LitemallRunnerVO runnerVO);

    void deleteRunner(@Param("id") Long id);

    IPage<LitemallRunner> listRunner(@Param("runner") LitemallRunnerVO runner, @Param("request") QueryRequest request);

    void changeStatus(@Param("id") Long id, @Param("status") Integer status);
}

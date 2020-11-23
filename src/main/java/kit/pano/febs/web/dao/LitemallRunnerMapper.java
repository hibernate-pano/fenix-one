package kit.pano.febs.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import kit.pano.febs.web.domain.po.LitemallRunner;
import kit.pano.febs.web.domain.vo.LitemallRunnerVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商城跑腿人员 Mapper 接口
 * </p>
 *
 * @author Pano
 * @since 2019-10-26
 */
public interface LitemallRunnerMapper extends BaseMapper<LitemallRunner> {

    IPage<LitemallRunner> listRunner(@Param("page") Page<LitemallRunner> page, @Param("runner") LitemallRunnerVO runner);
}

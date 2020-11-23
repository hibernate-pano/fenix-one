package kit.pano.febs.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import kit.pano.febs.web.domain.po.LitemallAssistant;
import kit.pano.febs.web.domain.vo.LitemallAssistantVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author pano
 * @since 2019-07-15
 */
public interface LitemallAssistantMapper extends BaseMapper<LitemallAssistant> {

    LitemallAssistantVO queryOneById(@Param("id") Long id);

    IPage<LitemallAssistantVO> listAssistant(@Param("page") Page<LitemallAssistantVO> page, @Param("assistant") LitemallAssistantVO assistant);
}

package kit.pano.febs.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.web.domain.po.LitemallAssistant;
import kit.pano.febs.web.domain.vo.LitemallAssistantVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author pano
 * @since 2019-07-15
 */
public interface LitemallAssistantService extends IService<LitemallAssistant> {

    void addAssistant(@Param("assistant") LitemallAssistantVO assistantVO) throws Exception;

    void updateAssistant(@Param("id") Long id, @Param("assistant") LitemallAssistantVO assistantVO) throws Exception;

    void deleteAssistant(@Param("id") Long id) throws Exception;

    LitemallAssistantVO queryOneById(@Param("id") Long id);

    void changeStatus(@Param("id") Long id, @Param("status") Integer status) throws Exception;

    IPage<LitemallAssistantVO> listAssistant(@Param("assistant") LitemallAssistantVO assistant, @Param("request") QueryRequest request);
}

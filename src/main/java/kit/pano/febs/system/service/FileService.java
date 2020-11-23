package kit.pano.febs.system.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.system.domain.po.File;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 文件存储表 服务类
 * </p>
 *
 * @author pano
 * @since 2019-05-20
 */
public interface FileService extends IService<File> {

    IPage<File> listFile(@Param("file") File file, @Param("queryRequest") QueryRequest queryRequest);

    File uploadFile(@Param("file") MultipartFile file);
}

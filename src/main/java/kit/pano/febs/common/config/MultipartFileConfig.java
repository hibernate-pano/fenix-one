package kit.pano.febs.common.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * Created with IntelliJ IDEA.
 * Date: 2019-12-06
 * Time: 16:23
 * <p>
 * Multipart文件上传大小修改
 *
 * @author Pano
 */
@Configuration
public class MultipartFileConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //指定文件代销
        factory.setMaxFileSize(DataSize.ofMegabytes(50));
        //设定上传文件大小
        factory.setMaxRequestSize(DataSize.ofMegabytes(50));
        return factory.createMultipartConfig();
    }
}

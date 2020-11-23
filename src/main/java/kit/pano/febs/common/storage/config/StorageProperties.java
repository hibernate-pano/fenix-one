package kit.pano.febs.common.storage.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Pano
 */
@Data
@ConfigurationProperties(prefix = "febs.storage")
public class StorageProperties {

    private String active;

    private Local local;

    private Aliyun aliyun;

    @Data
    static class Local {
        private String address;
        private String storagePath;
    }

    @Data
    static class Aliyun {
        private String endpoint;
        private String accessKeyId;
        private String accessKeySecret;
        private String bucketName;
    }
}

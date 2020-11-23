package kit.pano.febs.common.storage;

import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * 对象存储接口
 *
 * @author Pano
 */
public interface Storage {

    /**
     * 存储一个文件对象
     *
     * @param inputStream   文件输入流
     * @param contentLength 文件长度
     * @param contentType   文件类型
     * @param keyName       文件名
     */
    void store(InputStream inputStream, long contentLength, String contentType, String keyName);

    Stream<Path> loadAll();

    Path load(String keyName);

    /**
     * 根据文件key值获取resource对象
     */
    Resource loadAsResource(String keyName);

    /**
     * 根据文件key值删除文件
     */
    void delete(String keyName);

    /**
     * 根据文件key值获取文件地址
     */
    String generateUrl(String keyName);
}
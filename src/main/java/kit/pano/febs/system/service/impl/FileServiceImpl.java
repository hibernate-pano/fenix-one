package kit.pano.febs.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.storage.StorageService;
import kit.pano.febs.common.utils.SortUtil;
import kit.pano.febs.system.dao.FileMapper;
import kit.pano.febs.system.domain.po.File;
import kit.pano.febs.system.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 文件存储表 服务实现类
 * </p>
 *
 * @author pano
 * @since 2019-05-20
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    @Resource
    private StorageService storageService;

    @Override
    public IPage<File> listFile(File file, QueryRequest queryRequest) {
        try {
            LambdaQueryWrapper<File> queryWrapper = new LambdaQueryWrapper<>();

            //匹配类型名称
            queryWrapper.like(StringUtils.isNotBlank(file.getFileName()), File::getFileName, file.getFileName());
            //匹配父ID
            queryWrapper.eq(StringUtils.isNotBlank(file.getFileType()), File::getFileType, file.getFileType());

            Page<File> page = new Page<>();
            SortUtil.handlePageSort(queryRequest, page, true);
            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取文件失败", e);
            return null;
        }
    }

    @Override
    public File uploadFile(MultipartFile file) {

        try {
            long startTime = System.currentTimeMillis();
            InputStream inputStream = file.getInputStream();
            File store = storageService.store(inputStream, file.getSize(), file.getContentType(), file.getOriginalFilename());
            long endTime = System.currentTimeMillis();
            log.info("存储时间为 " + (endTime - startTime));
            return store;
        } catch (IOException e) {
            log.error("上传文件失败", e);
            return null;
        }
    }
}

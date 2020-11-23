package kit.pano.febs.system.controller;

import cn.hutool.http.HttpStatus;
import kit.pano.febs.common.controller.BaseController;
import kit.pano.febs.common.domain.FebsResponse;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.system.domain.po.File;
import kit.pano.febs.system.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Date: 2019-11-04
 * Time: 9:11
 *
 * @author Pano
 */
@Slf4j
@RestController
@RequestMapping("file")
public class FileController extends BaseController {

    @Resource
    private FileService fileService;

    /**
     * 查询单个文件信息
     */
    @GetMapping("{id}")
    @RequiresPermissions("file:view")
    public FebsResponse getFile(@PathVariable String id) {
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(fileService.getById(id));
    }

    /**
     * 查询文件列表
     */
    @GetMapping
    @RequiresPermissions("file:view")
    public FebsResponse listFile(QueryRequest queryRequest, File file) {
        Map<String, Object> dataTable = getDataTable(fileService.listFile(file, queryRequest));
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(dataTable);
    }

    /**
     * 上传文件
     */
    @PostMapping("upload")
    public FebsResponse uploadFile(MultipartFile file) {
        File store = fileService.uploadFile(file);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(store.getFileUrl());
    }

}

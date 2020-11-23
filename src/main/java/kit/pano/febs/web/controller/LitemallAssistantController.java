package kit.pano.febs.web.controller;


import cn.hutool.http.HttpStatus;
import kit.pano.febs.common.controller.BaseController;
import kit.pano.febs.common.domain.FebsResponse;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.validator.ValidatorUtils;
import kit.pano.febs.common.validator.group.AddGroup;
import kit.pano.febs.common.validator.group.UpdateGroup;
import kit.pano.febs.web.domain.vo.LitemallAssistantVO;
import kit.pano.febs.web.service.LitemallAssistantService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pano
 * @since 2019-07-15
 */
@Slf4j
@RestController
@RequestMapping("assistant")
public class LitemallAssistantController extends BaseController {

    private String message;

    @Resource
    private LitemallAssistantService litemallAssistantService;

    /**
     * 查询单个店员信息
     */
    @GetMapping("/{id}")
    @RequiresPermissions("assistant:view")
    public FebsResponse getAssistant(@PathVariable Long id) {
        LitemallAssistantVO assistantVO = litemallAssistantService.queryOneById(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(assistantVO);
    }

    /**
     * 查询店员列表
     */
    @GetMapping
    @RequiresPermissions("assistant:view")
    public FebsResponse listAssistant(QueryRequest request, LitemallAssistantVO assistant) {
        Map<String, Object> dataTable = getDataTable(litemallAssistantService.listAssistant(assistant, request));
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(dataTable);
    }

    /**
     * 新增店员
     */
    @PostMapping
    @RequiresPermissions("assistant:manage")
    public FebsResponse addAssistant(@RequestBody LitemallAssistantVO assistantVO) throws Exception {
        ValidatorUtils.validate(assistantVO, AddGroup.class);
        litemallAssistantService.addAssistant(assistantVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 修改店员
     */
    @PutMapping("/{id}")
    @RequiresPermissions("assistant:manage")
    public FebsResponse updateAssistant(@PathVariable Long id, @RequestBody LitemallAssistantVO assistantVO) throws Exception {
        ValidatorUtils.validate(assistantVO, UpdateGroup.class);

        litemallAssistantService.updateAssistant(id, assistantVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 删除店员
     */
    @DeleteMapping("/{id}")
    @RequiresPermissions("assistant:manage")
    public FebsResponse deleteAssistant(@PathVariable Long id) throws Exception {
        litemallAssistantService.deleteAssistant(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 店员禁用/启用
     */
    @PutMapping("/status")
    @RequiresPermissions("assistant:manage")
    public FebsResponse changeStatus(@RequestBody LitemallAssistantVO vo) throws Exception {
        litemallAssistantService.changeStatus(vo.getId(), vo.getStatus());
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

}

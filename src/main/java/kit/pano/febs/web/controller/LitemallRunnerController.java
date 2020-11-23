package kit.pano.febs.web.controller;


import cn.hutool.http.HttpStatus;
import kit.pano.febs.common.controller.BaseController;
import kit.pano.febs.common.domain.FebsResponse;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.exception.FebsException;
import kit.pano.febs.common.validator.ValidatorUtils;
import kit.pano.febs.common.validator.group.AddGroup;
import kit.pano.febs.common.validator.group.UpdateGroup;
import kit.pano.febs.web.domain.po.LitemallRunner;
import kit.pano.febs.web.domain.vo.LitemallRunnerVO;
import kit.pano.febs.web.service.LitemallRunnerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 商城跑腿人员 前端控制器
 * </p>
 *
 * @author Pano
 * @since 2019-10-26
 */
@Slf4j
@RestController
@RequestMapping("runner")
public class LitemallRunnerController extends BaseController {

    private String message;

    @Resource
    private LitemallRunnerService litemallRunnerService;

    /**
     * 查询单个跑腿信息
     */
    @RequiresPermissions("runner:view")
    @GetMapping("/{id}")
    public FebsResponse getRunner(@PathVariable Long id) {
        LitemallRunner runner = litemallRunnerService.getById(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(runner);
    }

    /**
     * 查询跑腿列表
     */
    @RequiresPermissions("runner:view")
    @GetMapping
    public FebsResponse listRunner(QueryRequest request, LitemallRunnerVO runner) {
        Map<String, Object> dataTable = getDataTable(litemallRunnerService.listRunner(runner, request));
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(dataTable);
    }

    /**
     * 新增跑腿
     */
    @RequiresPermissions("runner:manage")
    @PostMapping
    public FebsResponse addRunner(@RequestBody LitemallRunnerVO runnerVO) throws FebsException {
        ValidatorUtils.validate(runnerVO, AddGroup.class);
        litemallRunnerService.addRunner(runnerVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 修改跑腿
     */
    @RequiresPermissions("runner:manage")
    @PutMapping("/{id}")
    public FebsResponse updateRunner(@PathVariable Long id, @RequestBody LitemallRunnerVO runnerVO) throws FebsException {
        ValidatorUtils.validate(runnerVO, UpdateGroup.class);
        litemallRunnerService.updateRunner(id, runnerVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 删除跑腿
     */
    @RequiresPermissions("runner:manage")
    @DeleteMapping("/{id}")
    public FebsResponse deleteRunner(@PathVariable Long id) {
        litemallRunnerService.deleteRunner(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 店员禁用/启用
     */
    @PutMapping("/status")
    @RequiresPermissions("runner:manage")
    public FebsResponse changeStatus(@RequestBody LitemallRunnerVO vo) {
        litemallRunnerService.changeStatus(vo.getId(), vo.getStatus());
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }
}

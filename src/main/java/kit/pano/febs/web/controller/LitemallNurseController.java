package kit.pano.febs.web.controller;


import cn.hutool.http.HttpStatus;
import kit.pano.febs.common.controller.BaseController;
import kit.pano.febs.common.domain.FebsResponse;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.exception.FebsException;
import kit.pano.febs.common.validator.ValidatorUtils;
import kit.pano.febs.common.validator.group.AddGroup;
import kit.pano.febs.common.validator.group.UpdateGroup;
import kit.pano.febs.web.domain.po.LitemallNurse;
import kit.pano.febs.web.domain.vo.LitemallNurseVO;
import kit.pano.febs.web.service.LitemallNurseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 商城护工人员 前端控制器
 * </p>
 *
 * @author Pano
 * @since 2019-10-26
 */
@Slf4j
@RestController
@RequestMapping("nurse")
public class LitemallNurseController extends BaseController {

    @Resource
    private LitemallNurseService litemallNurseService;

    /**
     * 查询单个护工信息
     */
    @RequiresPermissions("nurse:view")
    @GetMapping("/{id}")
    public FebsResponse getNurse(@PathVariable Long id) {
        LitemallNurse nurse = litemallNurseService.getById(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(nurse);
    }

    /**
     * 查询护工列表
     */
    @RequiresPermissions("nurse:view")
    @GetMapping
    public FebsResponse listNurse(QueryRequest request, LitemallNurseVO nurse) {
        Map<String, Object> dataTable = getDataTable(litemallNurseService.listNurse(nurse, request));
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(dataTable);
    }

    /**
     * 新增护工
     */
    @RequiresPermissions("nurse:manage")
    @PostMapping
    public FebsResponse addNurse(@RequestBody LitemallNurseVO nurseVO) throws FebsException {
        ValidatorUtils.validate(nurseVO, AddGroup.class);
        litemallNurseService.addNurse(nurseVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 修改护工
     */
    @RequiresPermissions("nurse:manage")
    @PutMapping("/{id}")
    public FebsResponse updateNurse(@PathVariable Long id, @RequestBody LitemallNurseVO nurseVO) throws FebsException {
        ValidatorUtils.validate(nurseVO, UpdateGroup.class);
        litemallNurseService.updateNurse(id, nurseVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 删除护工
     */
    @RequiresPermissions("nurse:manage")
    @DeleteMapping("/{id}")
    public FebsResponse deleteNurse(@PathVariable Long id) {
        litemallNurseService.deleteNurse(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 护工禁用/启用
     */
    @PutMapping("/status")
    @RequiresPermissions("nurse:manage")
    public FebsResponse changeStatus(@RequestBody LitemallNurseVO vo) {
        litemallNurseService.changeStatus(vo.getId(), vo.getStatus());
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 护工上线/显现
     */
    @PutMapping("/online")
    @RequiresPermissions("nurse:manage")
    public FebsResponse changeOnline(@RequestBody LitemallNurseVO vo) {
        litemallNurseService.changeOnline(vo.getId(), vo.getOnline());
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

}

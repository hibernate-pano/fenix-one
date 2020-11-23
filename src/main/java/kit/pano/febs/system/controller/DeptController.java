package kit.pano.febs.system.controller;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.wuwenze.poi.ExcelKit;
import kit.pano.febs.common.annotation.Log;
import kit.pano.febs.common.controller.BaseController;
import kit.pano.febs.common.domain.FebsResponse;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.exception.FebsException;
import kit.pano.febs.system.domain.po.Dept;
import kit.pano.febs.system.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @author Pano
 */
@Slf4j
@Validated
@RestController
@RequestMapping("dept")
public class DeptController extends BaseController {

    private String message;

    @Resource
    private DeptService deptService;

    @GetMapping
    public FebsResponse deptList(QueryRequest request, Dept dept) {
        Map<String, Object> depts = this.deptService.findDepts(request, dept);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(depts);
    }

    @Log("新增部门")
    @PostMapping
    @RequiresPermissions("dept:add")
    public FebsResponse addDept(@Valid @RequestBody Dept dept) throws FebsException {
        try {
            this.deptService.createDept(dept);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "新增部门失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除部门")
    @DeleteMapping("/{deptIds}")
    @RequiresPermissions("dept:delete")
    public FebsResponse deleteDepts(@NotBlank(message = "{required}") @PathVariable String deptIds) throws FebsException {
        try {
            String[] ids = deptIds.split(StringPool.COMMA);
            this.deptService.deleteDepts(ids);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "删除部门失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改部门")
    @PutMapping
    @RequiresPermissions("dept:update")
    public FebsResponse updateDept(@Valid @RequestBody Dept dept) throws FebsException {
        try {
            this.deptService.updateDept(dept);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "修改部门失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("excel")
    @RequiresPermissions("dept:export")
    public void export(Dept dept, QueryRequest request, HttpServletResponse response) throws FebsException {
        try {
            List<Dept> depts = this.deptService.findDepts(dept, request);
            ExcelKit.$Export(Dept.class, response).downXlsx(depts, false);
        } catch (Exception e) {
            message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}

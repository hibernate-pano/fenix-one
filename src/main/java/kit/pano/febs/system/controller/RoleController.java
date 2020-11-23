package kit.pano.febs.system.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.wuwenze.poi.ExcelKit;
import kit.pano.febs.common.annotation.Log;
import kit.pano.febs.common.controller.BaseController;
import kit.pano.febs.common.domain.FebsResponse;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.exception.FebsException;
import kit.pano.febs.system.domain.po.Role;
import kit.pano.febs.system.domain.po.RoleMenu;
import kit.pano.febs.system.service.RoleMenuServie;
import kit.pano.febs.system.service.RoleService;
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
import java.util.stream.Collectors;

/**
 * @author Pano
 */
@Slf4j
@Validated
@RestController
@RequestMapping("role")
public class RoleController extends BaseController {

    @Resource
    private RoleService roleService;
    @Resource
    private RoleMenuServie roleMenuServie;

    private String message;

    @GetMapping
    @RequiresPermissions("role:view")
    public FebsResponse roleList(QueryRequest queryRequest, Role role) {
        Map<String, Object> dataTable = getDataTable(roleService.findRoles(role, queryRequest));
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(dataTable);
    }

    @GetMapping("check/{roleName}")
    public FebsResponse checkRoleName(@NotBlank(message = "{required}") @PathVariable String roleName) {
        Role result = this.roleService.findByName(roleName);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(ObjectUtil.isNull(result));
    }

    @GetMapping("menu/{roleId}")
    public FebsResponse getRoleMenus(@NotBlank(message = "{required}") @PathVariable String roleId) {
        List<RoleMenu> list = this.roleMenuServie.getRoleMenusByRoleId(roleId);
        List<String> result = list.stream().map(roleMenu -> String.valueOf(roleMenu.getMenuId())).collect(Collectors.toList());
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(result);
    }

    @Log("新增角色")
    @PostMapping
    @RequiresPermissions("role:add")
    public FebsResponse addRole(@Valid @RequestBody Role role) throws FebsException {
        try {
            this.roleService.createRole(role);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "新增角色失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除角色")
    @DeleteMapping("/{roleIds}")
    @RequiresPermissions("role:delete")
    public FebsResponse deleteRoles(@NotBlank(message = "{required}") @PathVariable String roleIds) throws FebsException {
        try {
            String[] ids = roleIds.split(StringPool.COMMA);
            this.roleService.deleteRoles(ids);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "删除角色失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改角色")
    @PutMapping
    @RequiresPermissions("role:update")
    public FebsResponse updateRole(@RequestBody Role role) throws FebsException {
        try {
            this.roleService.updateRole(role);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "修改角色失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("excel")
    @RequiresPermissions("role:export")
    public void export(QueryRequest queryRequest, Role role, HttpServletResponse response) throws FebsException {
        try {
            List<Role> roles = this.roleService.findRoles(role, queryRequest).getRecords();
            ExcelKit.$Export(Role.class, response).downXlsx(roles, false);
        } catch (Exception e) {
            message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}

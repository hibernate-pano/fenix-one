package kit.pano.febs.system.controller;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.wuwenze.poi.ExcelKit;
import kit.pano.febs.common.annotation.Log;
import kit.pano.febs.common.controller.BaseController;
import kit.pano.febs.common.domain.FebsResponse;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.exception.FebsException;
import kit.pano.febs.common.utils.MD5Util;
import kit.pano.febs.system.domain.po.User;
import kit.pano.febs.system.domain.po.UserConfig;
import kit.pano.febs.system.domain.vo.UserVO;
import kit.pano.febs.system.service.UserConfigService;
import kit.pano.febs.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("user")
public class UserController extends BaseController {

    private String message;

    @Resource
    private UserService userService;
    @Resource
    private UserConfigService userConfigService;

    @GetMapping("check/{username}")
    public FebsResponse checkUserName(@NotBlank(message = "{required}") @PathVariable String username) {
        boolean status = this.userService.findByName(username) == null;
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(status);
    }

    @GetMapping("/{username}")
    public FebsResponse detail(@NotBlank(message = "{required}") @PathVariable String username) {
        User user = this.userService.findByName(username);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(user);
    }

    @GetMapping
    @RequiresPermissions("user:view")
    public Map<String, Object> userList(QueryRequest queryRequest, User user) {
        Map<String, Object> dataTable = getDataTable(userService.findUserDetail(user, queryRequest));
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(dataTable);
    }

    @Log("新增用户")
    @PostMapping
    @RequiresPermissions("user:add")
    public FebsResponse addUser(@Valid @RequestBody User user) throws FebsException {
        try {
            this.userService.createUser(user);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "新增用户失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改用户")
    @PutMapping
    @RequiresPermissions("user:update")
    public FebsResponse updateUser(@Valid @RequestBody User user) throws FebsException {
        try {
            this.userService.updateUser(user);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "修改用户失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("用户状态修改")
    @PutMapping("/status")
    @RequiresPermissions("user:update")
    public FebsResponse changeStatus(@RequestBody UserVO vo) throws FebsException {
        try {
            this.userService.changeStatus(vo);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "用户状态修改失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除用户")
    @DeleteMapping("/{userIds}")
    @RequiresPermissions("user:delete")
    public FebsResponse deleteUsers(@NotBlank(message = "{required}") @PathVariable String userIds) throws FebsException {
        try {
            String[] ids = userIds.split(StringPool.COMMA);
            this.userService.deleteUsers(ids);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "删除用户失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PutMapping("profile")
    public FebsResponse updateProfile(@Valid @RequestBody User user) throws FebsException {
        try {
            this.userService.updateProfile(user);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "修改个人信息失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PutMapping("avatar")
    public FebsResponse updateAvatar(@RequestBody UserVO vo) throws FebsException {
        try {
            this.userService.updateAvatar(vo.getUsername(), vo.getAvatar());
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "修改头像失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PutMapping("userConfig")
    public FebsResponse updateUserConfig(@Valid @RequestBody UserConfig userConfig) throws FebsException {
        try {
            this.userConfigService.update(userConfig);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "修改个性化配置失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @GetMapping("password/check")
    public FebsResponse checkPassword(@RequestBody UserVO vo) {
        String encryptPassword = MD5Util.encrypt(vo.getUsername(), vo.getPassword());
        User user = userService.findByName(vo.getUsername());
        boolean status;
        if (user != null) {
            status = StringUtils.equals(user.getPassword(), encryptPassword);
        } else {
            status = false;
        }
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(status);
    }

    @PutMapping("password")
    public FebsResponse updatePassword(@RequestBody UserVO vo) throws FebsException {
        try {
            userService.updatePassword(vo.getUsername(), vo.getPassword());
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "修改密码失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PutMapping("password/reset")
    @RequiresPermissions("user:reset")
    public FebsResponse resetPassword(@RequestBody UserVO vo) throws FebsException {
        try {
            Assert.notNull(vo.getUserNames(), "userNames不能为空");
            String[] usernameArr = vo.getUserNames().split(StringPool.COMMA);
            this.userService.resetPassword(usernameArr);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "重置用户密码失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("excel")
    @RequiresPermissions("user:export")
    public void export(QueryRequest queryRequest, User user, HttpServletResponse response) throws FebsException {
        try {
            List<User> users = this.userService.findUserDetail(user, queryRequest).getRecords();
            ExcelKit.$Export(User.class, response).downXlsx(users, false);
        } catch (Exception e) {
            message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}

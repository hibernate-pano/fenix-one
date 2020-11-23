package kit.pano.febs.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import kit.pano.febs.system.domain.po.RoleMenu;

import java.util.List;

public interface RoleMenuServie extends IService<RoleMenu> {

    void deleteRoleMenusByRoleId(String[] roleIds);

    void deleteRoleMenusByMenuId(String[] menuIds);

    List<RoleMenu> getRoleMenusByRoleId(String roleId);
}

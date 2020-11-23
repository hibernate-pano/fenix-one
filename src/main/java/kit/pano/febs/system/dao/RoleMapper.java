package kit.pano.febs.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import kit.pano.febs.system.domain.po.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    List<Role> findUserRole(String userName);

}
package kit.pano.febs.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import kit.pano.febs.system.domain.po.LoginLog;

public interface LoginLogService extends IService<LoginLog> {

    void saveLoginLog(LoginLog loginLog);
}

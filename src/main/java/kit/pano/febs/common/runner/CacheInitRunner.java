package kit.pano.febs.common.runner;

import kit.pano.febs.common.exception.RedisConnectException;
import kit.pano.febs.common.service.CacheService;
import kit.pano.febs.system.domain.po.User;
import kit.pano.febs.system.manager.UserManager;
import kit.pano.febs.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 缓存初始化
 *
 * @author pano
 */
@Slf4j
@Component
public class CacheInitRunner implements ApplicationRunner {

    @Resource
    private UserService userService;
    @Resource
    private CacheService cacheService;
    @Resource
    private UserManager userManager;

    @Resource
    private ConfigurableApplicationContext context;

    @Override
    public void run(ApplicationArguments args) {
        try {
            log.info("Redis连接中 ······");
            cacheService.testConnect();

            log.info("缓存初始化 ······");
            log.info("缓存用户数据 ······");
            List<User> list = this.userService.list();
            for (User user : list) {
                userManager.loadUserRedisCache(user);
            }
        } catch (Exception e) {
            log.error("缓存初始化失败，{}", e.getMessage());
            log.error(" ____   __    _   _ ");
            log.error("| |_   / /\\  | | | |");
            log.error("|_|   /_/--\\ |_| |_|__");
            log.error("                        ");
            log.error("FEBS启动失败              ");
            if (e instanceof RedisConnectException) {
                log.error("Redis连接异常，请检查Redis连接配置并确保Redis服务已启动");
            }
            // 关闭 FEBS
            context.close();
        }
    }
}

package kit.pano.febs.web.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.service.CacheService;
import kit.pano.febs.system.domain.po.User;
import kit.pano.febs.system.service.UserService;
import kit.pano.febs.web.dao.LitemallShopkeeperMapper;
import kit.pano.febs.web.domain.mapper.LitemallShopkeeperStructMapper;
import kit.pano.febs.web.domain.po.LitemallShopkeeper;
import kit.pano.febs.web.domain.vo.LitemallShopkeeperVO;
import kit.pano.febs.web.service.LitemallShopkeeperService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 店主表 服务实现类
 * </p>
 *
 * @author pano
 * @since 2019-07-15
 */
@Service
public class LitemallShopkeeperServiceImpl extends ServiceImpl<LitemallShopkeeperMapper, LitemallShopkeeper> implements LitemallShopkeeperService {

    @Resource
    private CacheService cacheService;
    @Resource
    private UserService userService;
    @Resource
    private LitemallShopkeeperMapper litemallShopkeeperMapper;
    @Resource
    private LitemallShopkeeperStructMapper litemallShopkeeperStructMapper;

    @Override
    public void addShopkeeper(LitemallShopkeeperVO shopkeeperVO) throws Exception {

        // 添加管理员
        User user = litemallShopkeeperStructMapper.toUserPo(shopkeeperVO);
        userService.createUser(user);

        // 新增店主
        LitemallShopkeeper shopkeeper = litemallShopkeeperStructMapper.toPo(shopkeeperVO);
        shopkeeper.setUserId(user.getUserId());
        baseMapper.insert(shopkeeper);
    }

    @Override
    public void updateShopkeeper(Long id, LitemallShopkeeperVO shopkeeperVO) throws Exception {
        // 修改店主信息
        LitemallShopkeeper shopkeeper = litemallShopkeeperStructMapper.toPo(shopkeeperVO);
        shopkeeper.setId(id);
        int status = baseMapper.updateById(shopkeeper);

        //手机号码更改则会影响账号登录账户,有效期也是关联在管理员账号内
        if (status > 0) {
            shopkeeper = baseMapper.selectById(id);
            User user = userService.getById(shopkeeper.getUserId());

            //断言确定账户存在
            Assert.state(Objects.nonNull(user), "数据错误");
            user.setUsername(shopkeeperVO.getPhone());
            user.setExpireTime(shopkeeperVO.getExpireTime());
            userService.updateById(user);

            // 重新将用户信息加载到 redis中
            cacheService.saveUser(user.getUsername());
        }
    }

    @Override
    public void deleteShopkeeper(Long id, String phone) throws Exception {
        LitemallShopkeeper shopkeeper = baseMapper.selectById(id);
        //断言手机号码匹配
        Assert.state(shopkeeper.getPhone().equalsIgnoreCase(phone), "手机号码对应错误，请重试");
        //删除对应账户
        if (Objects.nonNull(shopkeeper.getUserId())) {
            userService.deleteUsers(new String[]{shopkeeper.getUserId().toString()});
        }

        //删除当前店员
        litemallShopkeeperMapper.deleteById(id);
    }

    @Override
    public LitemallShopkeeperVO queryOneById(Long id) {
        return litemallShopkeeperMapper.queryOneById(id);
    }

    @Override
    public void changeStatus(Long id, Integer status) throws Exception {
        LitemallShopkeeper shopkeeper = baseMapper.selectById(id);
        Assert.state(Objects.nonNull(shopkeeper), "ID错误");

        User user = userService.getById(shopkeeper.getUserId());
        Assert.state(Objects.nonNull(user), "无对应管理员账号");

        user.setStatus(status.toString());
        user.setModifyTime(new Date());
        userService.updateById(user);

        // 重新将用户信息加载到 redis中
        cacheService.saveUser(user.getUsername());
    }

    @Override
    public IPage<LitemallShopkeeperVO> listShopkeeper(LitemallShopkeeperVO shopkeeper, QueryRequest request) {

        try {
            Page<LitemallShopkeeperVO> page = new Page<>(request.getPageNum(), request.getPageSize());
            return baseMapper.listShopkeeper(page, shopkeeper);
        } catch (Exception e) {
            log.error("获取店主信息失败", e);
            return null;
        }
    }
}

package kit.pano.febs.web.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.service.CacheService;
import kit.pano.febs.common.utils.FebsUtil;
import kit.pano.febs.system.domain.po.User;
import kit.pano.febs.system.service.UserService;
import kit.pano.febs.web.dao.LitemallAssistantMapper;
import kit.pano.febs.web.domain.mapper.LitemallAssistantStructMapper;
import kit.pano.febs.web.domain.po.LitemallAssistant;
import kit.pano.febs.web.domain.po.LitemallShopkeeper;
import kit.pano.febs.web.domain.vo.LitemallAssistantVO;
import kit.pano.febs.web.service.LitemallAssistantService;
import kit.pano.febs.web.service.LitemallShopkeeperService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author pano
 * @since 2019-07-15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LitemallAssistantServiceImpl extends ServiceImpl<LitemallAssistantMapper, LitemallAssistant> implements LitemallAssistantService {

    @Resource
    private CacheService cacheService;
    @Resource
    private UserService userService;
    @Resource
    private LitemallAssistantMapper litemallAssistantMapper;
    @Resource
    private LitemallAssistantStructMapper litemallAssistantStructMapper;
    @Resource
    private LitemallShopkeeperService litemallShopkeeperService;

    @Override
    public void addAssistant(LitemallAssistantVO assistantVO) throws Exception {

        // 添加管理员
        User user = litemallAssistantStructMapper.toUserPo(assistantVO);
        userService.createUser(user);

        // 新增店员
        LitemallAssistant assistant = litemallAssistantStructMapper.toPo(assistantVO);
        assistant.setUserId(user.getUserId());

        //店主身份才能新增店员，需要查询到当前登录用户
        User currentUser = FebsUtil.getCurrentUser();
        Assert.state(ObjectUtil.isNotNull(currentUser), "数据错误");
        User shopkeeperUser = userService.getOne(
                new LambdaQueryWrapper<User>()
                        .eq(User::getUsername, currentUser.getUsername())
        );
        Assert.notNull(shopkeeperUser, "数据错误");

        LitemallShopkeeper shopkeeper = litemallShopkeeperService.getOne(
                new LambdaQueryWrapper<LitemallShopkeeper>()
                        .eq(LitemallShopkeeper::getUserId, shopkeeperUser.getUserId())
        );
        Assert.notNull(shopkeeper, "数据错误，只有店主可以新增店员");

        assistant.setShopkeeperId(shopkeeper.getId());
        baseMapper.insert(assistant);
    }

    @Override
    public void updateAssistant(Long id, LitemallAssistantVO assistantVO) throws Exception {

        // 修改店员信息
        LitemallAssistant assistant = litemallAssistantStructMapper.toPo(assistantVO);
        assistant.setId(id);
        int status = baseMapper.updateById(assistant);

        //手机号码更改则会影响账号登录账户,有效期也是关联在管理员账号内
        if (status > 0) {
            assistant = baseMapper.selectById(id);
            User user = userService.getById(assistant.getUserId());

            //断言确定账户存在
            Assert.state(Objects.nonNull(user), "数据错误");
            user.setUsername(assistantVO.getPhone())
                    .setExpireTime(assistantVO.getExpireTime());
            userService.updateById(user);

            // 重新将用户信息加载到 redis中
            cacheService.saveUser(user.getUsername());
        }
    }

    @Override
    public void deleteAssistant(Long id) throws Exception {
        LitemallAssistant assistant = baseMapper.selectById(id);
        //删除对应账户
        if (Objects.nonNull(assistant.getUserId())) {
            userService.deleteUsers(new String[]{assistant.getUserId().toString()});
        }
        //删除当前店员
        litemallAssistantMapper.deleteById(id);
    }

    @Override
    public LitemallAssistantVO queryOneById(Long id) {
        return litemallAssistantMapper.queryOneById(id);
    }

    @Override
    public void changeStatus(Long id, Integer status) throws Exception {
        LitemallAssistant assistant = baseMapper.selectById(id);
        Assert.notNull(assistant, "ID错误");

        User user = userService.getById(assistant.getUserId());
        Assert.notNull(user, "无对应管理员账号");

        user.setStatus(status.toString());
        user.setModifyTime(new Date());
        userService.updateById(user);

        // 重新将用户信息加载到 redis中
        cacheService.saveUser(user.getUsername());
    }

    @Override
    public IPage<LitemallAssistantVO> listAssistant(LitemallAssistantVO assistant, QueryRequest request) {
        Page<LitemallAssistantVO> page = new Page<>(request.getPageNum(), request.getPageSize());
        return baseMapper.listAssistant(page, assistant);
    }

}

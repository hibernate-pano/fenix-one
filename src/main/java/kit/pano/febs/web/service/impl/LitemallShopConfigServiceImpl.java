package kit.pano.febs.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kit.pano.febs.web.dao.LitemallShopConfigMapper;
import kit.pano.febs.web.domain.mapper.LitemallShopConfigStructMapper;
import kit.pano.febs.web.domain.po.LitemallShopConfig;
import kit.pano.febs.web.domain.vo.LitemallShopConfigVO;
import kit.pano.febs.web.service.LitemallShopConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Pano
 * @since 2019-09-07
 */
@Service
public class LitemallShopConfigServiceImpl extends ServiceImpl<LitemallShopConfigMapper, LitemallShopConfig> implements LitemallShopConfigService {

    @Resource
    private LitemallShopConfigStructMapper litemallShopConfigStructMapper;

    @Override
    public void addShopConfig(LitemallShopConfigVO shopConfigVO) {

        // 新增商品
        LitemallShopConfig shopConfig = litemallShopConfigStructMapper.toPo(shopConfigVO);
        baseMapper.insert(shopConfig);
    }

    @Override
    public void updateShopConfig(Long id, LitemallShopConfigVO shopConfigVO) {

        // 修改商品信息
        LitemallShopConfig shopConfig = litemallShopConfigStructMapper.toPo(shopConfigVO);
        shopConfig.setId(id);
        baseMapper.updateById(shopConfig);
    }

    @Override
    public void deleteShopConfig(Long id) {
        LitemallShopConfig shopConfig = new LitemallShopConfig();
        shopConfig.setId(id);
        baseMapper.updateById(shopConfig);
    }

}

package kit.pano.febs.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kit.pano.febs.common.domain.FebsConst;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.utils.SortUtil;
import kit.pano.febs.web.dao.LitemallShopMapper;
import kit.pano.febs.web.domain.mapper.LitemallShopStructMapper;
import kit.pano.febs.web.domain.po.LitemallShop;
import kit.pano.febs.web.domain.po.LitemallShopkeeper;
import kit.pano.febs.web.domain.vo.LitemallShopVO;
import kit.pano.febs.web.service.LitemallShopService;
import kit.pano.febs.web.service.LitemallShopkeeperService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
public class LitemallShopServiceImpl extends ServiceImpl<LitemallShopMapper, LitemallShop> implements LitemallShopService {

    @Resource
    private LitemallShopStructMapper litemallShopStructMapper;
    @Resource
    private LitemallShopkeeperService litemallShopkeeperService;

    @Override
    public void addShop(LitemallShopVO shopVO) {
        // 新增店铺
        LitemallShop shop = litemallShopStructMapper.toPo(shopVO);
        shop.setAddTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now())
                .setDeleted(FebsConst.NO);
        //默认初始商品种类为0
        shop.setGoodsNum(0);
        //将店主姓名作为冗余收据存储
        if (Objects.nonNull(shopVO.getShopkeeperId())) {
            LitemallShopkeeper shopkeeper = litemallShopkeeperService.getById(shopVO.getShopkeeperId());
            if (Objects.nonNull(shopkeeper)) {
                shop.setShopkeeperName(shopkeeper.getRealName());
            }
        }
        baseMapper.insert(shop);
    }

    @Override
    public IPage<LitemallShop> listShop(LitemallShopVO shop, QueryRequest request) {
        LambdaQueryWrapper<LitemallShop> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(shop.getShopName()), LitemallShop::getShopName, shop.getShopCode());
        //未删除的数据
        queryWrapper.eq(LitemallShop::getDeleted, FebsConst.NO);
        Page<LitemallShop> page = new Page<>();
        SortUtil.handlePageSort(request, page, true);
        return this.page(page, queryWrapper);
    }

    @Override
    public void updateShop(Long id, LitemallShopVO shopVO) {
        // 修改店铺信息
        LitemallShop shop = litemallShopStructMapper.toPo(shopVO);
        shop.setId(id)
                .setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(shop);
    }

    @Override
    public void deleteShop(Long id) {
        LitemallShop shop = new LitemallShop();
        shop.setId(id)
                .setDeleted(FebsConst.YES)
                .setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(shop);
    }

    @Override
    public void changeStatus(Long id, Integer status) {
        // 一键开关
        LitemallShop shop = new LitemallShop();
        shop.setId(id)
                .setStatus(status)
                .setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(shop);
    }

}

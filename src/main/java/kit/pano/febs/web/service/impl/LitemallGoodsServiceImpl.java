package kit.pano.febs.web.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kit.pano.febs.common.domain.FebsConst;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.utils.SortUtil;
import kit.pano.febs.web.dao.LitemallGoodsMapper;
import kit.pano.febs.web.domain.mapper.LitemallGoodsStructMapper;
import kit.pano.febs.web.domain.po.LitemallGoods;
import kit.pano.febs.web.domain.vo.LitemallGoodsVO;
import kit.pano.febs.web.service.LitemallGoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品基本信息表 服务实现类
 * </p>
 *
 * @author pano
 * @since 2019-05-20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LitemallGoodsServiceImpl extends ServiceImpl<LitemallGoodsMapper, LitemallGoods> implements LitemallGoodsService {

    @Resource
    private LitemallGoodsStructMapper litemallGoodsStructMapper;

    @Override
    public void addGoods(LitemallGoodsVO goodsVO) {

        // 新增商品
        LitemallGoods goods = litemallGoodsStructMapper.toPo(goodsVO);
        goods.setAddTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now())
                .setDeleted(FebsConst.NO);
        baseMapper.insert(goods);
    }

    @Override
    public void updateGoods(Long id, LitemallGoodsVO goodsVO) {

        // 修改商品信息
        LitemallGoods goods = litemallGoodsStructMapper.toPo(goodsVO);
        goods.setId(id)
                .setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(goods);
    }

    @Override
    public void deleteGoods(Long id) {
        LitemallGoods goods = new LitemallGoods();
        goods.setId(id)
                .setDeleted(FebsConst.YES)
                .setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(goods);
    }

    @Override
    public IPage<LitemallGoods> listGoods(LitemallGoodsVO goods, QueryRequest request) {
        try {
            LambdaQueryWrapper<LitemallGoods> queryWrapper = new LambdaQueryWrapper<>();

            //匹配产品SN码
            queryWrapper.like(StringUtils.isNotBlank(goods.getGoodsSn()), LitemallGoods::getGoodsSn, goods.getGoodsSn());
            //匹配店铺
            queryWrapper.eq(ObjectUtil.isNotNull(goods.getShopId()), LitemallGoods::getShopId, goods.getShopId());

            //未删除的数据
            queryWrapper.eq(LitemallGoods::getDeleted, FebsConst.NO);

            Page<LitemallGoods> page = new Page<>();
            SortUtil.handlePageSort(request, page, true);
            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取商品信息失败", e);
            return null;
        }
    }
}

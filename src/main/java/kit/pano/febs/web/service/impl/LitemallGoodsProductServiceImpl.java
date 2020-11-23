package kit.pano.febs.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kit.pano.febs.common.domain.FebsConst;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.utils.SortUtil;
import kit.pano.febs.web.dao.LitemallGoodsProductMapper;
import kit.pano.febs.web.domain.mapper.LitemallGoodsProductStructMapper;
import kit.pano.febs.web.domain.po.LitemallGoodsProduct;
import kit.pano.febs.web.domain.vo.LitemallGoodsProductVO;
import kit.pano.febs.web.service.LitemallGoodsProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 货品货品表 服务实现类
 * </p>
 *
 * @author pano
 * @since 2019-05-20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LitemallGoodsProductServiceImpl extends ServiceImpl<LitemallGoodsProductMapper, LitemallGoodsProduct> implements LitemallGoodsProductService {

    @Resource
    private LitemallGoodsProductStructMapper litemallGoodsProductStructMapper;

    @Override
    public void addGoodsProduct(LitemallGoodsProductVO goodsProductVO) {

        // 新增店铺
        LitemallGoodsProduct goodsProduct = litemallGoodsProductStructMapper.toPo(goodsProductVO);
        goodsProduct.setAddTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now())
                .setDeleted(FebsConst.NO);
        baseMapper.insert(goodsProduct);
    }

    @Override
    public IPage<LitemallGoodsProduct> listGoodsProduct(LitemallGoodsProductVO goodsProduct, QueryRequest request) {

        try {
            LambdaQueryWrapper<LitemallGoodsProduct> queryWrapper = new LambdaQueryWrapper<>();

            //商品货品规格匹配
            queryWrapper.like(StringUtils.isNotBlank(goodsProduct.getSpecifications()), LitemallGoodsProduct::getSpecifications, goodsProduct.getSpecifications());
            //未删除的数据
            queryWrapper.eq(LitemallGoodsProduct::getDeleted, FebsConst.NO);

            Page<LitemallGoodsProduct> page = new Page<>();
            SortUtil.handlePageSort(request, page, true);
            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取店铺信息失败", e);
            return null;
        }
    }

    @Override
    public void updateGoodsProduct(Long id, LitemallGoodsProductVO goodsProductVO) {

        // 修改店铺信息
        LitemallGoodsProduct goodsProduct = litemallGoodsProductStructMapper.toPo(goodsProductVO);
        goodsProduct.setId(id)
                .setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(goodsProduct);
    }

    @Override
    public void deleteGoodsProduct(Long id) {
        LitemallGoodsProduct goodsProduct = new LitemallGoodsProduct();
        goodsProduct.setId(id)
                .setDeleted(FebsConst.YES)
                .setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(goodsProduct);
    }
}

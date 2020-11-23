package kit.pano.febs.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kit.pano.febs.common.domain.FebsConst;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.utils.SortUtil;
import kit.pano.febs.web.dao.LitemallGoodsAttributeMapper;
import kit.pano.febs.web.domain.mapper.LitemallGoodsAttributeStructMapper;
import kit.pano.febs.web.domain.po.LitemallGoodsAttribute;
import kit.pano.febs.web.domain.vo.LitemallGoodsAttributeVO;
import kit.pano.febs.web.service.LitemallGoodsAttributeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品参数参数表 服务实现类
 * </p>
 *
 * @author pano
 * @since 2019-05-20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LitemallGoodsAttributeServiceImpl extends ServiceImpl<LitemallGoodsAttributeMapper, LitemallGoodsAttribute> implements LitemallGoodsAttributeService {

    @Resource
    private LitemallGoodsAttributeStructMapper litemallGoodsAttributeStructMapper;

    @Override
    public void addGoodsAttribute(LitemallGoodsAttributeVO goodsAttributeVO) {

        // 新增店铺
        LitemallGoodsAttribute goodsAttribute = litemallGoodsAttributeStructMapper.toPo(goodsAttributeVO);
        goodsAttribute.setAddTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now())
                .setDeleted(FebsConst.NO);
        baseMapper.insert(goodsAttribute);
    }

    @Override
    public IPage<LitemallGoodsAttribute> listGoodsAttribute(LitemallGoodsAttributeVO goodsAttribute, QueryRequest request) {

        try {
            LambdaQueryWrapper<LitemallGoodsAttribute> queryWrapper = new LambdaQueryWrapper<>();

            //商品属性匹配
            queryWrapper.like(StringUtils.isNotBlank(goodsAttribute.getAttribute()), LitemallGoodsAttribute::getAddTime, goodsAttribute.getAddTime());
            //未删除的数据
            queryWrapper.eq(LitemallGoodsAttribute::getDeleted, FebsConst.NO);

            Page<LitemallGoodsAttribute> page = new Page<>();
            SortUtil.handlePageSort(request, page, true);
            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取商品属性失败", e);
            return null;
        }
    }

    @Override
    public void updateGoodsAttribute(Long id, LitemallGoodsAttributeVO goodsAttributeVO) {

        // 修改商品属性
        LitemallGoodsAttribute goodsAttribute = litemallGoodsAttributeStructMapper.toPo(goodsAttributeVO);
        goodsAttribute.setId(id)
                .setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(goodsAttribute);
    }

    @Override
    public void deleteGoodsAttribute(Long id) {
        LitemallGoodsAttribute goodsAttribute = new LitemallGoodsAttribute();
        goodsAttribute.setId(id)
                .setDeleted(FebsConst.YES)
                .setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(goodsAttribute);
    }
}

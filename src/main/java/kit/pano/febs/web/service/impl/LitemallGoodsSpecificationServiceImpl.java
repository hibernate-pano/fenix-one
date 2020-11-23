package kit.pano.febs.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kit.pano.febs.common.domain.FebsConst;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.utils.SortUtil;
import kit.pano.febs.web.dao.LitemallGoodsSpecificationMapper;
import kit.pano.febs.web.domain.mapper.LitemallGoodsSpecificationStructMapper;
import kit.pano.febs.web.domain.po.LitemallGoodsSpecification;
import kit.pano.febs.web.domain.vo.LitemallGoodsSpecificationVO;
import kit.pano.febs.web.service.LitemallGoodsSpecificationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品规格规格表 服务实现类
 * </p>
 *
 * @author pano
 * @since 2019-05-20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LitemallGoodsSpecificationServiceImpl extends ServiceImpl<LitemallGoodsSpecificationMapper, LitemallGoodsSpecification> implements LitemallGoodsSpecificationService {

    @Resource
    private LitemallGoodsSpecificationStructMapper litemallGoodsSpecificationStructMapper;

    @Override
    public void addGoodsSpecification(LitemallGoodsSpecificationVO goodsSpecificationVO) {

        // 新增店铺
        LitemallGoodsSpecification goodsSpecification = litemallGoodsSpecificationStructMapper.toPo(goodsSpecificationVO);
        goodsSpecification.setAddTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now())
                .setDeleted(FebsConst.NO);
        baseMapper.insert(goodsSpecification);
    }

    @Override
    public IPage<LitemallGoodsSpecification> listGoodsSpecification(LitemallGoodsSpecificationVO goodsSpecification, QueryRequest request) {

        try {
            LambdaQueryWrapper<LitemallGoodsSpecification> queryWrapper = new LambdaQueryWrapper<>();

            //匹配规格名称
            queryWrapper.like(StringUtils.isNotBlank(goodsSpecification.getSpecification()), LitemallGoodsSpecification::getSpecification, goodsSpecification.getSpecification());
            //未删除的数据
            queryWrapper.eq(LitemallGoodsSpecification::getDeleted, FebsConst.NO);

            Page<LitemallGoodsSpecification> page = new Page<>();
            SortUtil.handlePageSort(request, page, true);
            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取店铺信息失败", e);
            return null;
        }
    }

    @Override
    public void updateGoodsSpecification(Long id, LitemallGoodsSpecificationVO goodsSpecificationVO) {

        // 修改店铺信息
        LitemallGoodsSpecification goodsSpecification = litemallGoodsSpecificationStructMapper.toPo(goodsSpecificationVO);
        goodsSpecification.setId(id)
                .setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(goodsSpecification);
    }

    @Override
    public void deleteGoodsSpecification(Long id) {
        LitemallGoodsSpecification goodsSpecification = new LitemallGoodsSpecification();
        goodsSpecification.setId(id)
                .setDeleted(FebsConst.YES)
                .setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(goodsSpecification);
    }
}

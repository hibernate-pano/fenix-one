package kit.pano.febs.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kit.pano.febs.common.domain.FebsConst;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.utils.SortUtil;
import kit.pano.febs.web.dao.LitemallCategoryMapper;
import kit.pano.febs.web.domain.mapper.LitemallCategoryStructMapper;
import kit.pano.febs.web.domain.po.LitemallCategory;
import kit.pano.febs.web.domain.vo.LitemallCategoryVO;
import kit.pano.febs.web.service.LitemallCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 类目表 服务实现类
 * </p>
 *
 * @author pano
 * @since 2019-05-20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LitemallCategoryServiceImpl extends ServiceImpl<LitemallCategoryMapper, LitemallCategory> implements LitemallCategoryService {

    @Resource
    private LitemallCategoryStructMapper litemallCategoryStructMapper;

    @Override
    public void addCategory(LitemallCategoryVO categoryVO) {

        // 新增商品类目
        LitemallCategory category = litemallCategoryStructMapper.toPo(categoryVO);

        category.setAddTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now())
                .setDeleted(FebsConst.NO);

        baseMapper.insert(category);
    }

    @Override
    public IPage<LitemallCategory> listCategory(LitemallCategoryVO category, QueryRequest request) {

        try {
            LambdaQueryWrapper<LitemallCategory> queryWrapper = new LambdaQueryWrapper<>();

            //匹配类型名称
            queryWrapper.like(StringUtils.isNotBlank(category.getName()), LitemallCategory::getName, category.getName());
            //未删除的数据
            queryWrapper.eq(LitemallCategory::getDeleted, FebsConst.NO);

            Page<LitemallCategory> page = new Page<>();
            SortUtil.handlePageSort(request, page, true);
            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取商品类目信息失败", e);
            return null;
        }
    }

    @Override
    public void updateCategory(Long id, LitemallCategoryVO categoryVO) {

        // 修改商品类目信息
        LitemallCategory category = litemallCategoryStructMapper.toPo(categoryVO);
        category.setId(id)
                .setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(category);
    }

    @Override
    public void deleteCategory(Long id) {
        LitemallCategory category = new LitemallCategory();
        category.setId(id)
                .setDeleted(FebsConst.YES)
                .setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(category);
    }
}

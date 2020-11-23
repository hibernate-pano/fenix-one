package kit.pano.febs.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.utils.SortUtil;
import kit.pano.febs.system.dao.AreaMapper;
import kit.pano.febs.system.domain.po.Area;
import kit.pano.febs.system.service.AreaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Pano
 * @since 2019-11-03
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {

    @Override
    public IPage<Area> listArea(Area area, QueryRequest queryRequest) {
        try {
            LambdaQueryWrapper<Area> queryWrapper = new LambdaQueryWrapper<>();

            //匹配类型名称
            queryWrapper.like(StringUtils.isNotBlank(area.getName()), Area::getName, area.getName());
            //匹配父ID
            queryWrapper.eq(ObjectUtil.isNotNull(area.getPid()), Area::getPid, area.getPid());

            Page<Area> page = new Page<>();
            SortUtil.handlePageSort(queryRequest, page, true);
            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取区域信息失败", e);
            return null;
        }

    }
}

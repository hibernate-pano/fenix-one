package kit.pano.febs.web.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kit.pano.febs.common.domain.FebsConst;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.utils.SortUtil;
import kit.pano.febs.web.dao.LitemallDeliverMapper;
import kit.pano.febs.web.domain.mapper.LitemallDeliverStructMapper;
import kit.pano.febs.web.domain.po.LitemallDeliver;
import kit.pano.febs.web.domain.vo.LitemallDeliverVO;
import kit.pano.febs.web.service.LitemallDeliverService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 商城配送人员 服务实现类
 * </p>
 *
 * @author Pano
 * @since 2019-10-26
 */
@Service
public class LitemallDeliverServiceImpl extends ServiceImpl<LitemallDeliverMapper, LitemallDeliver> implements LitemallDeliverService {

    @Resource
    private LitemallDeliverStructMapper litemallDeliverStructMapper;

    @Override
    public void addDeliver(LitemallDeliverVO deliverVO) {

        // 新增配送
        LitemallDeliver deliver = litemallDeliverStructMapper.toPo(deliverVO);
        deliver.setAddTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now())
                .setDeleted(FebsConst.NO);
        baseMapper.insert(deliver);
    }

    @Override
    public void updateDeliver(Long id, LitemallDeliverVO deliverVO) {

        // 修改配送信息
        LitemallDeliver deliver = litemallDeliverStructMapper.toPo(deliverVO);
        deliver.setId(id)
                .setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(deliver);
    }

    @Override
    public void deleteDeliver(Long id) {
        LitemallDeliver deliver = new LitemallDeliver();
        deliver.setId(id)
                .setDeleted(FebsConst.YES)
                .setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(deliver);
    }

    @Override
    public IPage<LitemallDeliver> listDeliver(LitemallDeliverVO deliver, QueryRequest request) {
        try {
            LambdaQueryWrapper<LitemallDeliver> queryWrapper = new LambdaQueryWrapper<>();

            //匹配姓名
            queryWrapper.like(StringUtils.isNotBlank(deliver.getRealName()), LitemallDeliver::getRealName, deliver.getRealName());
            //匹配店铺
            queryWrapper.eq(ObjectUtil.isNotNull(deliver.getShopId()), LitemallDeliver::getShopId, deliver.getShopId());

            //未删除的数据
            queryWrapper.eq(LitemallDeliver::getDeleted, FebsConst.NO);

            Page<LitemallDeliver> page = new Page<>();
            SortUtil.handlePageSort(request, page, true);
            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取配送信息失败", e);
            return null;
        }
    }

    @Override
    public void changeStatus(Long id, Integer status) {
        LitemallDeliver deliver = baseMapper.selectById(id);
        Assert.notNull(deliver, "ID错误");

        deliver.setStatus(status);
        deliver.setUpdateTime(LocalDateTime.now());
        this.updateById(deliver);
    }
}

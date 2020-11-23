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
import kit.pano.febs.web.dao.LitemallNurseMapper;
import kit.pano.febs.web.domain.mapper.LitemallNurseStructMapper;
import kit.pano.febs.web.domain.po.LitemallNurse;
import kit.pano.febs.web.domain.vo.LitemallNurseVO;
import kit.pano.febs.web.service.LitemallNurseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 商城护工人员 服务实现类
 * </p>
 *
 * @author Pano
 * @since 2019-10-26
 */
@Service
public class LitemallNurseServiceImpl extends ServiceImpl<LitemallNurseMapper, LitemallNurse> implements LitemallNurseService {

    @Resource
    private LitemallNurseStructMapper litemallNurseStructMapper;

    @Override
    public void addNurse(LitemallNurseVO nurseVO) {

        // 新增护工
        LitemallNurse nurse = litemallNurseStructMapper.toPo(nurseVO);
        nurse.setAddTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now())
                .setDeleted(FebsConst.NO);
        baseMapper.insert(nurse);
    }

    @Override
    public void updateNurse(Long id, LitemallNurseVO nurseVO) {

        // 修改护工信息
        LitemallNurse nurse = litemallNurseStructMapper.toPo(nurseVO);
        nurse.setId(id)
                .setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(nurse);
    }

    @Override
    public void deleteNurse(Long id) {
        LitemallNurse nurse = new LitemallNurse();
        nurse.setId(id)
                .setDeleted(FebsConst.YES)
                .setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(nurse);
    }

    @Override
    public IPage<LitemallNurse> listNurse(LitemallNurseVO nurse, QueryRequest request) {
        try {
            LambdaQueryWrapper<LitemallNurse> queryWrapper = new LambdaQueryWrapper<>();

            //匹配姓名
            queryWrapper.like(StringUtils.isNotBlank(nurse.getRealName()), LitemallNurse::getRealName, nurse.getRealName());
            //匹配店铺
            queryWrapper.eq(ObjectUtil.isNotNull(nurse.getShopId()), LitemallNurse::getShopId, nurse.getShopId());

            //未删除的数据
            queryWrapper.eq(LitemallNurse::getDeleted, FebsConst.NO);

            Page<LitemallNurse> page = new Page<>();
            SortUtil.handlePageSort(request, page, true);
            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取护工信息失败", e);
            return null;
        }
    }

    @Override
    public void changeStatus(Long id, Integer status) {
        LitemallNurse nurse = baseMapper.selectById(id);
        Assert.notNull(nurse, "ID错误");

        nurse.setStatus(status);
        nurse.setUpdateTime(LocalDateTime.now());
        this.updateById(nurse);
    }

    @Override
    public void changeOnline(Long id, Integer online) {
        LitemallNurse nurse = baseMapper.selectById(id);
        Assert.notNull(nurse, "ID错误");

        // TODO: 2019/11/5 判断身份，店主可以申请上线、管理员可以审核上线
        // TODO: 2019/11/5 后面功能待拓展

        nurse.setOnline(online);
        nurse.setUpdateTime(LocalDateTime.now());
        this.updateById(nurse);
    }
}

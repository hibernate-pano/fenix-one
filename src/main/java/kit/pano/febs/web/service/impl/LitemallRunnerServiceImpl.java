package kit.pano.febs.web.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kit.pano.febs.common.domain.FebsConst;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.utils.SortUtil;
import kit.pano.febs.web.dao.LitemallRunnerMapper;
import kit.pano.febs.web.domain.mapper.LitemallRunnerStructMapper;
import kit.pano.febs.web.domain.po.LitemallRunner;
import kit.pano.febs.web.domain.vo.LitemallRunnerVO;
import kit.pano.febs.web.service.LitemallRunnerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 商城跑腿人员 服务实现类
 * </p>
 *
 * @author Pano
 * @since 2019-10-26
 */
@Service
public class LitemallRunnerServiceImpl extends ServiceImpl<LitemallRunnerMapper, LitemallRunner> implements LitemallRunnerService {

    @Resource
    private LitemallRunnerStructMapper litemallRunnerStructMapper;

    @Override
    public void addRunner(LitemallRunnerVO runnerVO) {

        // 新增跑腿
        LitemallRunner runner = litemallRunnerStructMapper.toPo(runnerVO);
        runner.setAddTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now())
                .setDeleted(FebsConst.NO);
        baseMapper.insert(runner);
    }

    @Override
    public void updateRunner(Long id, LitemallRunnerVO runnerVO) {

        // 修改跑腿信息
        LitemallRunner runner = litemallRunnerStructMapper.toPo(runnerVO);
        runner.setId(id)
                .setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(runner);
    }

    @Override
    public void deleteRunner(Long id) {
        LitemallRunner runner = new LitemallRunner();
        runner.setId(id)
                .setDeleted(FebsConst.YES)
                .setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(runner);
    }

    @Override
    public IPage<LitemallRunner> listRunner(LitemallRunnerVO runner, QueryRequest request) {
        try {
            Page<LitemallRunner> page = new Page<>();
            SortUtil.handlePageSort(request, page, true);
            return this.baseMapper.listRunner(page, runner);
        } catch (Exception e) {
            log.error("获取跑腿信息失败", e);
            return null;
        }
    }

    @Override
    public void changeStatus(Long id, Integer status) {
        LitemallRunner runner = baseMapper.selectById(id);
        Assert.notNull(runner, "ID错误");

        runner.setStatus(status);
        runner.setUpdateTime(LocalDateTime.now());
        this.updateById(runner);
    }
}

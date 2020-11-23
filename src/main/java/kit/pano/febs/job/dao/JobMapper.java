package kit.pano.febs.job.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import kit.pano.febs.job.domain.Job;

import java.util.List;

public interface JobMapper extends BaseMapper<Job> {

    List<Job> queryList();
}
package kit.pano.febs.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import kit.pano.febs.system.domain.po.Test;

import java.util.List;

public interface TestService extends IService<Test> {

    List<Test> findTests();

    /**
     * 批量插入
     *
     * @param list List<Test>
     */
    void batchInsert(List<Test> list);
}

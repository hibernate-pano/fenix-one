package kit.pano.febs.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.web.domain.po.LitemallCategory;
import kit.pano.febs.web.domain.vo.LitemallCategoryVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 类目表 服务类
 * </p>
 *
 * @author pano
 * @since 2019-05-20
 */
public interface LitemallCategoryService extends IService<LitemallCategory> {

    void addCategory(@Param("category") LitemallCategoryVO categoryVO);

    IPage<LitemallCategory> listCategory(@Param("category") LitemallCategoryVO categoryVO, @Param("request") QueryRequest queryRequest);

    void updateCategory(@Param("id") Long id, @Param("category") LitemallCategoryVO categoryVO);

    void deleteCategory(@Param("id") Long id);
}

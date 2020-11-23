package kit.pano.febs.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.web.domain.po.LitemallGoodsSpecification;
import kit.pano.febs.web.domain.vo.LitemallGoodsSpecificationVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品规格表 服务类
 * </p>
 *
 * @author pano
 * @since 2019-05-20
 */
public interface LitemallGoodsSpecificationService extends IService<LitemallGoodsSpecification> {

    void addGoodsSpecification(@Param("goodsSpecification") LitemallGoodsSpecificationVO goodsSpecificationVO);

    IPage<LitemallGoodsSpecification> listGoodsSpecification(@Param("goodsSpecification") LitemallGoodsSpecificationVO goodsSpecificationVO, @Param("request") QueryRequest queryRequest);

    void updateGoodsSpecification(@Param("id") Long id, @Param("goodsSpecification") LitemallGoodsSpecificationVO goodsSpecificationVO);

    void deleteGoodsSpecification(@Param("id") Long id);
}

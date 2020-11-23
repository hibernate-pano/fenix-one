package kit.pano.febs.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.web.domain.po.LitemallGoodsProduct;
import kit.pano.febs.web.domain.vo.LitemallGoodsProductVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品货品表 服务类
 * </p>
 *
 * @author pano
 * @since 2019-05-20
 */
public interface LitemallGoodsProductService extends IService<LitemallGoodsProduct> {

    void addGoodsProduct(@Param("goodsProduct") LitemallGoodsProductVO goodsProductVO);

    IPage<LitemallGoodsProduct> listGoodsProduct(@Param("goodsProduct") LitemallGoodsProductVO goodsProductVO, @Param("request") QueryRequest queryRequest);

    void updateGoodsProduct(@Param("id") Long id, @Param("goodsProduct") LitemallGoodsProductVO goodsProductVO);

    void deleteGoodsProduct(@Param("id") Long id);
}

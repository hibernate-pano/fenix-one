package kit.pano.febs.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.web.domain.po.LitemallGoodsAttribute;
import kit.pano.febs.web.domain.vo.LitemallGoodsAttributeVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品参数表 服务类
 * </p>
 *
 * @author pano
 * @since 2019-05-20
 */
public interface LitemallGoodsAttributeService extends IService<LitemallGoodsAttribute> {

    void addGoodsAttribute(@Param("goodsAttribute") LitemallGoodsAttributeVO goodsAttributeVO);

    IPage<LitemallGoodsAttribute> listGoodsAttribute(@Param("goodsAttribute") LitemallGoodsAttributeVO goodsAttributeVO, @Param("request") QueryRequest queryRequest);

    void updateGoodsAttribute(@Param("id") Long id, @Param("goodsAttribute") LitemallGoodsAttributeVO goodsAttributeVO);

    void deleteGoodsAttribute(@Param("id") Long id);
}

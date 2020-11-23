package kit.pano.febs.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.web.domain.po.LitemallGoods;
import kit.pano.febs.web.domain.vo.LitemallGoodsVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品基本信息表 服务类
 * </p>
 *
 * @author pano
 * @since 2019-05-20
 */
public interface LitemallGoodsService extends IService<LitemallGoods> {

    void addGoods(@Param("goods") LitemallGoodsVO goodsVO);

    void updateGoods(@Param("id") Long id, @Param("goods") LitemallGoodsVO goodsVO);

    void deleteGoods(@Param("id") Long id);

    IPage<LitemallGoods> listGoods(@Param("goods") LitemallGoodsVO goods, @Param("request") QueryRequest request);
}

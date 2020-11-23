package kit.pano.febs.web.controller;


import cn.hutool.http.HttpStatus;
import kit.pano.febs.common.controller.BaseController;
import kit.pano.febs.common.domain.FebsResponse;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.exception.FebsException;
import kit.pano.febs.common.validator.ValidatorUtils;
import kit.pano.febs.common.validator.group.AddGroup;
import kit.pano.febs.common.validator.group.UpdateGroup;
import kit.pano.febs.web.domain.po.LitemallGoodsProduct;
import kit.pano.febs.web.domain.vo.LitemallGoodsProductVO;
import kit.pano.febs.web.service.LitemallGoodsProductService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 商品货品表 前端控制器
 * </p>
 *
 * @author pano
 * @since 2019-05-20
 */
@RestController
@RequestMapping("goods/product")
public class LitemallGoodsProductController extends BaseController {

    @Resource
    private LitemallGoodsProductService litemallGoodsProductService;

    /**
     * 查询单个商品货品信息
     */
    @RequiresPermissions("goods:view")
    @GetMapping("/{id}")
    public FebsResponse getGoodsProduct(@PathVariable Long id) {
        LitemallGoodsProduct goodsProduct = litemallGoodsProductService.getById(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(goodsProduct);
    }

    /**
     * 查询商品货品分页信息
     */
    @RequiresPermissions("goods:view")
    @GetMapping
    public FebsResponse listGoodsProduct(QueryRequest queryRequest, LitemallGoodsProductVO goodsProduct) {
        Map<String, Object> dataTable = getDataTable(litemallGoodsProductService.listGoodsProduct(goodsProduct, queryRequest));
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(dataTable);
    }

    /**
     * 新增商品货品
     */
    @RequiresPermissions("goods:manage")
    @PostMapping
    public FebsResponse addGoodsProduct(@RequestBody LitemallGoodsProductVO goodsProductVO) throws FebsException {
        ValidatorUtils.validate(goodsProductVO, AddGroup.class);
        litemallGoodsProductService.addGoodsProduct(goodsProductVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 修改商品货品
     */
    @RequiresPermissions("goods:manage")
    @PutMapping("/{id}")
    public FebsResponse updateGoodsProduct(@PathVariable Long id, @RequestBody LitemallGoodsProductVO goodsProductVO) throws FebsException {
        ValidatorUtils.validate(goodsProductVO, UpdateGroup.class);
        litemallGoodsProductService.updateGoodsProduct(id, goodsProductVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 删除商品货品
     */
    @RequiresPermissions("goods:manage")
    @DeleteMapping("/{id}")
    public FebsResponse deleteGoodsProduct(@PathVariable Long id) {
        litemallGoodsProductService.deleteGoodsProduct(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }
}

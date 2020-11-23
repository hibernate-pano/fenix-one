package kit.pano.febs.web.controller;


import cn.hutool.http.HttpStatus;
import kit.pano.febs.common.controller.BaseController;
import kit.pano.febs.common.domain.FebsResponse;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.exception.FebsException;
import kit.pano.febs.common.validator.ValidatorUtils;
import kit.pano.febs.common.validator.group.AddGroup;
import kit.pano.febs.common.validator.group.UpdateGroup;
import kit.pano.febs.web.domain.po.LitemallGoods;
import kit.pano.febs.web.domain.vo.LitemallGoodsVO;
import kit.pano.febs.web.service.LitemallGoodsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 商品基本信息表 前端控制器
 * </p>
 *
 * @author pano
 * @since 2019-05-20
 */
@RestController
@RequestMapping("goods")
public class LitemallGoodsController extends BaseController {

    @Resource
    private LitemallGoodsService litemallGoodsService;

    /**
     * 查询单个商品信息
     */
    @RequiresPermissions("goods:view")
    @GetMapping("/{id}")
    public FebsResponse getGoods(@PathVariable Long id) {
        LitemallGoods goods = litemallGoodsService.getById(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(goods);
    }

    /**
     * 查询商品列表
     */
    @RequiresPermissions("goods:view")
    @GetMapping
    public FebsResponse listGoods(QueryRequest request, LitemallGoodsVO goods) {
        Map<String, Object> dataTable = getDataTable(litemallGoodsService.listGoods(goods, request));
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(dataTable);
    }

    /**
     * 新增商品
     */
    @RequiresPermissions("goods:manage")
    @PostMapping
    public FebsResponse addGoods(@RequestBody LitemallGoodsVO goodsVO) throws FebsException {
        ValidatorUtils.validate(goodsVO, AddGroup.class);
        litemallGoodsService.addGoods(goodsVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 修改商品
     */
    @RequiresPermissions("goods:manage")
    @PutMapping("/{id}")
    public FebsResponse updateGoods(@PathVariable Long id, @RequestBody LitemallGoodsVO goodsVO) throws FebsException {
        ValidatorUtils.validate(goodsVO, UpdateGroup.class);
        litemallGoodsService.updateGoods(id, goodsVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 删除商品
     */
    @RequiresPermissions("goods:manage")
    @DeleteMapping("/{id}")
    public FebsResponse deleteGoods(@PathVariable Long id) {
        litemallGoodsService.deleteGoods(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

}

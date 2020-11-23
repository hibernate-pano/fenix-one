package kit.pano.febs.web.controller;


import cn.hutool.http.HttpStatus;
import kit.pano.febs.common.controller.BaseController;
import kit.pano.febs.common.domain.FebsResponse;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.exception.FebsException;
import kit.pano.febs.common.validator.ValidatorUtils;
import kit.pano.febs.common.validator.group.AddGroup;
import kit.pano.febs.common.validator.group.UpdateGroup;
import kit.pano.febs.web.domain.po.LitemallGoodsAttribute;
import kit.pano.febs.web.domain.vo.LitemallGoodsAttributeVO;
import kit.pano.febs.web.service.LitemallGoodsAttributeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 商品属性参数表 前端控制器
 * </p>
 *
 * @author pano
 * @since 2019-05-20
 */
@RestController
@RequestMapping("goods/attribute")
public class LitemallGoodsAttributeController extends BaseController {

    @Resource
    private LitemallGoodsAttributeService litemallGoodsAttributeService;

    /**
     * 查询单个商品属性信息
     */
    @RequiresPermissions("goods:view")
    @GetMapping("/{id}")
    public FebsResponse getGoodsAttribute(@PathVariable Long id) {
        LitemallGoodsAttribute goodsAttribute = litemallGoodsAttributeService.getById(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(goodsAttribute);
    }

    /**
     * 查询商品属性分页信息
     */
    @RequiresPermissions("goods:view")
    @GetMapping
    public FebsResponse listGoodsAttribute(QueryRequest queryRequest, LitemallGoodsAttributeVO goodsAttribute) {
        Map<String, Object> dataTable = getDataTable(litemallGoodsAttributeService.listGoodsAttribute(goodsAttribute, queryRequest));
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(dataTable);
    }

    /**
     * 新增商品属性
     */
    @RequiresPermissions("goods:manage")
    @PostMapping
    public FebsResponse addGoodsAttribute(@RequestBody LitemallGoodsAttributeVO goodsAttributeVO) throws FebsException {
        ValidatorUtils.validate(goodsAttributeVO, AddGroup.class);
        litemallGoodsAttributeService.addGoodsAttribute(goodsAttributeVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 修改商品属性
     */
    @RequiresPermissions("goods:manage")
    @PutMapping("/{id}")
    public FebsResponse updateGoodsAttribute(@PathVariable Long id, @RequestBody LitemallGoodsAttributeVO goodsAttributeVO) throws FebsException {
        ValidatorUtils.validate(goodsAttributeVO, UpdateGroup.class);
        litemallGoodsAttributeService.updateGoodsAttribute(id, goodsAttributeVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 删除商品属性
     */
    @RequiresPermissions("goods:manage")
    @DeleteMapping("/{id}")
    public FebsResponse deleteGoodsAttribute(@PathVariable Long id) {
        litemallGoodsAttributeService.deleteGoodsAttribute(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

}

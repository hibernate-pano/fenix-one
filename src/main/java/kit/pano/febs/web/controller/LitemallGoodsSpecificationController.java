package kit.pano.febs.web.controller;


import cn.hutool.http.HttpStatus;
import kit.pano.febs.common.controller.BaseController;
import kit.pano.febs.common.domain.FebsResponse;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.exception.FebsException;
import kit.pano.febs.common.validator.ValidatorUtils;
import kit.pano.febs.common.validator.group.AddGroup;
import kit.pano.febs.common.validator.group.UpdateGroup;
import kit.pano.febs.web.domain.po.LitemallGoodsSpecification;
import kit.pano.febs.web.domain.vo.LitemallGoodsSpecificationVO;
import kit.pano.febs.web.service.LitemallGoodsSpecificationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 商品规格表 前端控制器
 * </p>
 *
 * @author pano
 * @since 2019-05-20
 */
@RestController
@RequestMapping("goods/specification")
public class LitemallGoodsSpecificationController extends BaseController {

    @Resource
    private LitemallGoodsSpecificationService litemallGoodsSpecificationService;

    /**
     * 查询单个商品规格信息
     */
    @RequiresPermissions("goods:view")
    @GetMapping("/{id}")
    public FebsResponse getGoodsSpecification(@PathVariable Long id) {
        LitemallGoodsSpecification goodsSpecification = litemallGoodsSpecificationService.getById(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(goodsSpecification);
    }

    /**
     * 查询商品规格分页信息
     */
    @RequiresPermissions("goods:view")
    @GetMapping
    public FebsResponse listGoodsSpecification(QueryRequest queryRequest, LitemallGoodsSpecificationVO goodsSpecification) {
        Map<String, Object> dataTable = getDataTable(litemallGoodsSpecificationService.listGoodsSpecification(goodsSpecification, queryRequest));
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(dataTable);
    }

    /**
     * 新增商品规格
     */
    @RequiresPermissions("goods:manage")
    @PostMapping
    public FebsResponse addGoodsSpecification(@RequestBody LitemallGoodsSpecificationVO goodsSpecificationVO) throws FebsException {
        ValidatorUtils.validate(goodsSpecificationVO, AddGroup.class);
        litemallGoodsSpecificationService.addGoodsSpecification(goodsSpecificationVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 修改商品规格
     */
    @RequiresPermissions("goods:manage")
    @PutMapping("/{id}")
    public FebsResponse updateGoodsSpecification(@PathVariable Long id, @RequestBody LitemallGoodsSpecificationVO goodsSpecificationVO) throws FebsException {
        ValidatorUtils.validate(goodsSpecificationVO, UpdateGroup.class);
        litemallGoodsSpecificationService.updateGoodsSpecification(id, goodsSpecificationVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 删除商品规格
     */
    @RequiresPermissions("goods:manage")
    @DeleteMapping("/{id}")
    public FebsResponse deleteGoodsSpecification(@PathVariable Long id) {
        litemallGoodsSpecificationService.deleteGoodsSpecification(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }
}

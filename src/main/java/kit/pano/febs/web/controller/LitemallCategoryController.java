package kit.pano.febs.web.controller;


import cn.hutool.http.HttpStatus;
import kit.pano.febs.common.controller.BaseController;
import kit.pano.febs.common.domain.FebsResponse;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.exception.FebsException;
import kit.pano.febs.common.validator.ValidatorUtils;
import kit.pano.febs.common.validator.group.AddGroup;
import kit.pano.febs.common.validator.group.UpdateGroup;
import kit.pano.febs.web.domain.po.LitemallCategory;
import kit.pano.febs.web.domain.vo.LitemallCategoryVO;
import kit.pano.febs.web.service.LitemallCategoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 类目表 前端控制器
 * </p>
 *
 * @author pano
 * @since 2019-05-20
 */
@RestController
@RequestMapping("category")
public class LitemallCategoryController extends BaseController {

    @Resource
    private LitemallCategoryService litemallCategoryService;

    /**
     * 查询单个商品类目信息
     */
    @GetMapping("/{id}")
    @RequiresPermissions("goods:view")
    public FebsResponse getCategory(@PathVariable Long id) {
        LitemallCategory category = litemallCategoryService.getById(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(category);
    }

    /**
     * 查询商品类目分页信息
     */
    @GetMapping
    @RequiresPermissions("goods:view")
    public FebsResponse listCategory(QueryRequest queryRequest, LitemallCategoryVO category) {
        Map<String, Object> dataTable = getDataTable(litemallCategoryService.listCategory(category, queryRequest));
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(dataTable);
    }

    /**
     * 新增商品类目
     */
    @PostMapping
    @RequiresPermissions("goods:manage")
    public FebsResponse addCategory(@RequestBody LitemallCategoryVO categoryVO) throws FebsException {
        ValidatorUtils.validate(categoryVO, AddGroup.class);
        litemallCategoryService.addCategory(categoryVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 修改商品类目
     */
    @PutMapping("/{id}")
    @RequiresPermissions("goods:manage")
    public FebsResponse updateCategory(@PathVariable Long id, @RequestBody LitemallCategoryVO categoryVO) throws FebsException {
        ValidatorUtils.validate(categoryVO, UpdateGroup.class);
        litemallCategoryService.updateCategory(id, categoryVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 删除商品类目
     */
    @DeleteMapping("/{id}")
    @RequiresPermissions("goods:manage")
    public FebsResponse deleteCategory(@PathVariable Long id) {
        litemallCategoryService.deleteCategory(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }
}

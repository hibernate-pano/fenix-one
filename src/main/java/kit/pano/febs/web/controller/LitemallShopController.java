package kit.pano.febs.web.controller;


import cn.hutool.http.HttpStatus;
import kit.pano.febs.common.controller.BaseController;
import kit.pano.febs.common.domain.FebsResponse;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.exception.FebsException;
import kit.pano.febs.common.validator.ValidatorUtils;
import kit.pano.febs.common.validator.group.AddGroup;
import kit.pano.febs.common.validator.group.UpdateGroup;
import kit.pano.febs.web.domain.po.LitemallShop;
import kit.pano.febs.web.domain.vo.LitemallShopVO;
import kit.pano.febs.web.service.LitemallShopService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pano
 * @since 2019-07-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("shop")
public class LitemallShopController extends BaseController {

    private String message;

    @Resource
    private LitemallShopService litemallShopService;

    /**
     * 查询单个店铺信息
     */
    @GetMapping("/{id}")
    @RequiresPermissions("shop:view")
    public FebsResponse getShop(@PathVariable Long id) {
        LitemallShop shop = litemallShopService.getById(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(shop);
    }

    /**
     * 查询店铺分页信息
     */
    @GetMapping
    @RequiresPermissions("shop:view")
    public FebsResponse listShop(QueryRequest queryRequest, LitemallShopVO shop) {
        Map<String, Object> dataTable = getDataTable(litemallShopService.listShop(shop, queryRequest));
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(dataTable);
    }

    /**
     * 新增店铺
     */
    @PostMapping
    @RequiresPermissions("shop:manage")
    public FebsResponse addShop(@RequestBody LitemallShopVO shopVO) throws FebsException {
        ValidatorUtils.validate(shopVO, AddGroup.class);
        litemallShopService.addShop(shopVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 修改店铺
     */
    @PutMapping("/{id}")
    @RequiresPermissions("shop:manage")
    public FebsResponse updateShop(@PathVariable Long id, @RequestBody LitemallShopVO shopVO) throws FebsException {
        ValidatorUtils.validate(shopVO, UpdateGroup.class);
        litemallShopService.updateShop(id, shopVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 删除店铺
     */
    @DeleteMapping("/{id}")
    @RequiresPermissions("shop:manage")
    public FebsResponse deleteShop(@PathVariable Long id) throws FebsException {
        try {
            litemallShopService.deleteShop(id);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "删除店铺失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 一键开关店
     */
    @PutMapping("/status")
    @RequiresPermissions("shop:manage")
    public FebsResponse changeStatus(@RequestBody LitemallShopVO litemallShopVO) throws FebsException {
        try {
            litemallShopService.changeStatus(litemallShopVO.getId(), litemallShopVO.getStatus());
        } catch (Exception e) {
            message = "一键开关店失败";
            log.error(message, e);
            throw new FebsException(message);
        }
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

}

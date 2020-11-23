package kit.pano.febs.web.controller;


import cn.hutool.http.HttpStatus;
import kit.pano.febs.common.controller.BaseController;
import kit.pano.febs.common.domain.FebsResponse;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.validator.ValidatorUtils;
import kit.pano.febs.common.validator.group.AddGroup;
import kit.pano.febs.common.validator.group.UpdateGroup;
import kit.pano.febs.web.domain.vo.LitemallShopkeeperVO;
import kit.pano.febs.web.service.LitemallShopkeeperService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 店主表 前端控制器
 * </p>
 *
 * @author pano
 * @since 2019-07-15
 */
@Slf4j
@RestController
@RequestMapping("shopkeeper")
public class LitemallShopkeeperController extends BaseController {

    private String message;

    @Resource
    private LitemallShopkeeperService litemallShopkeeperService;

    /**
     * 查询单个店主信息
     */
    @GetMapping("/{id}")
    @RequiresPermissions("shopkeeper:view")
    public FebsResponse getShopkeeper(@PathVariable Long id) {
        LitemallShopkeeperVO shopkeeperVO = litemallShopkeeperService.queryOneById(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(shopkeeperVO);
    }

    /**
     * 查询店主列表
     */
    @GetMapping
    @RequiresPermissions("shopkeeper:view")
    public FebsResponse listShopkeeper(QueryRequest request, LitemallShopkeeperVO shopkeeper) {
        Map<String, Object> dataTable = getDataTable(litemallShopkeeperService.listShopkeeper(shopkeeper, request));
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(dataTable);
    }

    /**
     * 新增店主
     */
    @PostMapping
    @RequiresPermissions("shopkeeper:manage")
    public FebsResponse addShopkeeper(@RequestBody LitemallShopkeeperVO shopkeeperVO) throws Exception {
        ValidatorUtils.validate(shopkeeperVO, AddGroup.class);
        litemallShopkeeperService.addShopkeeper(shopkeeperVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 修改店主
     */
    @PutMapping("/{id}")
    @RequiresPermissions("shopkeeper:manage")
    public FebsResponse updateShopkeeper(@PathVariable Long id, @RequestBody LitemallShopkeeperVO shopkeeperVO) throws Exception {
        ValidatorUtils.validate(shopkeeperVO, UpdateGroup.class);
        litemallShopkeeperService.updateShopkeeper(id, shopkeeperVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 删除店主
     */
    @DeleteMapping("/{id}")
    @RequiresPermissions("shopkeeper:manage")
    public FebsResponse deleteShopkeeper(@PathVariable Long id, String phone) throws Exception {
        litemallShopkeeperService.deleteShopkeeper(id, phone);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 店主禁用/启用
     */
    @PutMapping("/status")
    @RequiresPermissions("shopkeeper:manage")
    public FebsResponse changeStatus(@RequestBody LitemallShopkeeperVO vo) throws Exception {
        litemallShopkeeperService.changeStatus(vo.getId(), vo.getStatus());
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

}

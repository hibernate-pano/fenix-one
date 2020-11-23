package kit.pano.febs.web.controller;


import cn.hutool.http.HttpStatus;
import kit.pano.febs.common.domain.FebsResponse;
import kit.pano.febs.common.exception.FebsException;
import kit.pano.febs.common.validator.ValidatorUtils;
import kit.pano.febs.common.validator.group.AddGroup;
import kit.pano.febs.common.validator.group.UpdateGroup;
import kit.pano.febs.web.domain.po.LitemallShopConfig;
import kit.pano.febs.web.domain.vo.LitemallShopConfigVO;
import kit.pano.febs.web.service.LitemallShopConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Pano
 * @since 2019-09-07
 */
@Slf4j
@RestController
@RequestMapping("shop/config")
public class LitemallShopConfigController {

    @Resource
    private LitemallShopConfigService litemallShopConfigService;

    /**
     * 查询单个店铺配置信息
     */
    @GetMapping("/{id}")
    @RequiresPermissions("shopConfig:view")
    public FebsResponse getShopConfig(@PathVariable Long id) {
        LitemallShopConfig shopConfig = litemallShopConfigService.getById(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(shopConfig);
    }

    /**
     * 新增店铺配置
     */
    @PostMapping
    @RequiresPermissions("shopConfig:manage")
    public FebsResponse addShopConfig(@RequestBody LitemallShopConfigVO shopConfigVO) throws Exception {
        ValidatorUtils.validate(shopConfigVO, AddGroup.class);
        litemallShopConfigService.addShopConfig(shopConfigVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 修改店铺配置
     */
    @PutMapping("/{id}")
    @RequiresPermissions("shopConfig:manage")
    public FebsResponse updateShopConfig(@PathVariable Long id, @RequestBody LitemallShopConfigVO shopConfigVO) throws FebsException {
        ValidatorUtils.validate(shopConfigVO, UpdateGroup.class);
        litemallShopConfigService.updateShopConfig(id, shopConfigVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 删除店铺配置
     */
    @DeleteMapping("/{id}")
    @RequiresPermissions("shopConfig:manage")
    public FebsResponse deleteShopConfig(@PathVariable Long id) {
        litemallShopConfigService.deleteShopConfig(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

}

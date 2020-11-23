package kit.pano.febs.web.controller;


import cn.hutool.http.HttpStatus;
import kit.pano.febs.common.annotation.Log;
import kit.pano.febs.common.controller.BaseController;
import kit.pano.febs.common.domain.FebsResponse;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.exception.FebsException;
import kit.pano.febs.common.validator.ValidatorUtils;
import kit.pano.febs.common.validator.group.AddGroup;
import kit.pano.febs.common.validator.group.UpdateGroup;
import kit.pano.febs.web.domain.po.LitemallOrder;
import kit.pano.febs.web.domain.vo.LitemallOrderVO;
import kit.pano.febs.web.service.LitemallOrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 商城订单表 前端控制器
 * </p>
 *
 * @author Pano
 * @since 2019-09-22
 */
@RestController
@RequestMapping("order")
public class LitemallOrderController extends BaseController {

    @Resource
    private LitemallOrderService litemallOrderService;

    /**
     * 查询单个商品订单信息
     */
    @GetMapping("/{id}")
    @RequiresPermissions("order:view")
    public FebsResponse getOrder(@PathVariable Long id) {
        LitemallOrder order = litemallOrderService.getById(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(order);
    }

    /**
     * 查询商品订单分页信息
     */
    @GetMapping
    @RequiresPermissions("order:view")
    public FebsResponse listOrder(QueryRequest queryRequest, LitemallOrderVO order) {
        Map<String, Object> dataTable = getDataTable(litemallOrderService.listOrder(order, queryRequest));
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(dataTable);
    }

    /**
     * 新增商品订单
     */
    @Log("新增订单")
    @PostMapping
    @RequiresPermissions("order:manage")
    public FebsResponse addOrder(@RequestBody LitemallOrderVO orderVO) throws FebsException {
        ValidatorUtils.validate(orderVO, AddGroup.class);
        litemallOrderService.addOrder(orderVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 修改商品订单
     */
    @PutMapping("/{id}")
    @RequiresPermissions("order:manage")
    public FebsResponse updateOrder(@PathVariable Long id, @RequestBody LitemallOrderVO orderVO) throws FebsException {
        ValidatorUtils.validate(orderVO, UpdateGroup.class);
        litemallOrderService.updateOrder(id, orderVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 删除商品订单
     */
    @DeleteMapping("/{id}")
    @RequiresPermissions("order:manage")
    public FebsResponse deleteOrder(@PathVariable Long id) {
        litemallOrderService.deleteOrder(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

}

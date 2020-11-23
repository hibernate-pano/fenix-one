package kit.pano.febs.web.controller;


import cn.hutool.http.HttpStatus;
import kit.pano.febs.common.controller.BaseController;
import kit.pano.febs.common.domain.FebsResponse;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.exception.FebsException;
import kit.pano.febs.common.validator.ValidatorUtils;
import kit.pano.febs.common.validator.group.AddGroup;
import kit.pano.febs.common.validator.group.UpdateGroup;
import kit.pano.febs.web.domain.po.LitemallOrderLog;
import kit.pano.febs.web.domain.vo.LitemallOrderLogVO;
import kit.pano.febs.web.service.LitemallOrderLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 商品订单日志 前端控制器
 * </p>
 *
 * @author Pano
 * @since 2019-09-23
 */
@Slf4j
@RestController
@RequestMapping("order/log")
public class LitemallOrderLogController extends BaseController {

    @Resource
    private LitemallOrderLogService litemallOrderLogService;

    /**
     * 查询单个订单日志信息
     */
    @GetMapping("/{id}")
    @RequiresPermissions("order:view")
    public FebsResponse getOrderLog(@PathVariable Long id) {
        LitemallOrderLog orderLog = litemallOrderLogService.getById(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(orderLog);
    }

    /**
     * 查询订单日志分页信息
     */
    @GetMapping
    @RequiresPermissions("order:view")
    public FebsResponse listOrderLog(QueryRequest queryRequest, LitemallOrderLogVO orderLog) {
        Map<String, Object> dataTable = getDataTable(litemallOrderLogService.listOrderLog(orderLog, queryRequest));
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(dataTable);
    }

    /**
     * 新增订单日志
     */
    @PostMapping
    @RequiresPermissions("order:manage")
    public FebsResponse addOrderLog(@RequestBody LitemallOrderLogVO orderLogVO) throws FebsException {
        ValidatorUtils.validate(orderLogVO, AddGroup.class);
        litemallOrderLogService.addOrderLog(orderLogVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 修改订单日志
     */
    @PutMapping("/{id}")
    @RequiresPermissions("order:manage")
    public FebsResponse updateOrderLog(@PathVariable Long id, @RequestBody LitemallOrderLogVO orderLogVO) throws FebsException {
        ValidatorUtils.validate(orderLogVO, UpdateGroup.class);
        litemallOrderLogService.updateOrderLog(id, orderLogVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 删除订单日志
     */
    @DeleteMapping("/{id}")
    @RequiresPermissions("order:manage")
    public FebsResponse deleteOrderLog(@PathVariable Long id) {
        litemallOrderLogService.deleteOrderLog(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }
}

package kit.pano.febs.web.controller;


import cn.hutool.http.HttpStatus;
import kit.pano.febs.common.controller.BaseController;
import kit.pano.febs.common.domain.FebsResponse;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.exception.FebsException;
import kit.pano.febs.common.validator.ValidatorUtils;
import kit.pano.febs.common.validator.group.AddGroup;
import kit.pano.febs.common.validator.group.UpdateGroup;
import kit.pano.febs.web.domain.po.LitemallCustomer;
import kit.pano.febs.web.domain.vo.LitemallCustomerVO;
import kit.pano.febs.web.service.LitemallCustomerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 商城顾客表 前端控制器
 * </p>
 *
 * @author Pano
 * @since 2019-09-22
 */
@Slf4j
@RestController
@RequestMapping("customer")
public class LitemallCustomerController extends BaseController {

    @Resource
    private LitemallCustomerService litemallCustomerService;

    /**
     * 查询单个顾客信息
     */
    @GetMapping("/{id}")
    @RequiresPermissions("customer:view")
    public FebsResponse getCustomer(@PathVariable Long id) {
        LitemallCustomer customer = litemallCustomerService.getById(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(customer);
    }

    /**
     * 查询顾客分页信息
     */
    @GetMapping
    @RequiresPermissions("customer:view")
    public FebsResponse listCustomer(QueryRequest queryRequest, LitemallCustomerVO customer) {
        Map<String, Object> dataTable = getDataTable(litemallCustomerService.listCustomer(customer, queryRequest));
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(dataTable);
    }

    /**
     * 新增顾客
     */
    @PostMapping
    @RequiresPermissions("customer:manage")
    public FebsResponse addCustomer(@RequestBody LitemallCustomerVO customerVO) throws FebsException {
        ValidatorUtils.validate(customerVO, AddGroup.class);
        litemallCustomerService.addCustomer(customerVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 修改顾客
     */
    @PutMapping("/{id}")
    @RequiresPermissions("customer:manage")
    public FebsResponse updateCustomer(@PathVariable Long id, @RequestBody LitemallCustomerVO customerVO) throws FebsException {
        ValidatorUtils.validate(customerVO, UpdateGroup.class);
        litemallCustomerService.updateCustomer(id, customerVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 删除顾客
     */
    @DeleteMapping("/{id}")
    @RequiresPermissions("customer:manage")
    public FebsResponse deleteCustomer(@PathVariable Long id) {
        litemallCustomerService.deleteCustomer(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }
}

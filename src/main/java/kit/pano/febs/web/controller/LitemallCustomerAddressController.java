package kit.pano.febs.web.controller;


import cn.hutool.http.HttpStatus;
import kit.pano.febs.common.controller.BaseController;
import kit.pano.febs.common.domain.FebsResponse;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.exception.FebsException;
import kit.pano.febs.common.validator.ValidatorUtils;
import kit.pano.febs.common.validator.group.AddGroup;
import kit.pano.febs.common.validator.group.UpdateGroup;
import kit.pano.febs.web.domain.po.LitemallCustomerAddress;
import kit.pano.febs.web.domain.vo.LitemallCustomerAddressVO;
import kit.pano.febs.web.service.LitemallCustomerAddressService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 顾客收货地址表 前端控制器
 * </p>
 *
 * @author Pano
 * @since 2019-09-22
 */
@RestController
@RequestMapping("customer/address")
public class LitemallCustomerAddressController extends BaseController {

    @Resource
    private LitemallCustomerAddressService litemallCustomerAddressService;

    /**
     * 查询单个顾客收货人信息
     */
    @GetMapping("/{id}")
    @RequiresPermissions("customer:view")
    public FebsResponse getCustomerAddress(@PathVariable Long id) {
        LitemallCustomerAddress customerAddress = litemallCustomerAddressService.getById(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(customerAddress);
    }

    /**
     * 查询顾客收货人分页信息
     */
    @GetMapping
    @RequiresPermissions("customer:view")
    public FebsResponse listCustomerAddress(QueryRequest queryRequest, LitemallCustomerAddressVO customerAddress) {
        Map<String, Object> dataTable = getDataTable(litemallCustomerAddressService.listCustomerAddress(customerAddress, queryRequest));
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(dataTable);
    }

    /**
     * 新增顾客收货人
     */
    @PostMapping
    @RequiresPermissions("customer:manage")
    public FebsResponse addCustomerAddress(@RequestBody LitemallCustomerAddressVO customerAddressVO) throws FebsException {
        ValidatorUtils.validate(customerAddressVO, AddGroup.class);
        litemallCustomerAddressService.addCustomerAddress(customerAddressVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 修改顾客收货人
     */
    @PutMapping("/{id}")
    @RequiresPermissions("customer:manage")
    public FebsResponse updateCustomerAddress(@PathVariable Long id, @RequestBody LitemallCustomerAddressVO customerAddressVO) throws FebsException {
        ValidatorUtils.validate(customerAddressVO, UpdateGroup.class);
        litemallCustomerAddressService.updateCustomerAddress(id, customerAddressVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 删除顾客收货人
     */
    @DeleteMapping("/{id}")
    @RequiresPermissions("customer:manage")
    public FebsResponse deleteCustomerAddress(@PathVariable Long id) {
        litemallCustomerAddressService.deleteCustomerAddress(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }
}

package kit.pano.febs.web.controller;


import cn.hutool.http.HttpStatus;
import kit.pano.febs.common.controller.BaseController;
import kit.pano.febs.common.domain.FebsResponse;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.exception.FebsException;
import kit.pano.febs.common.validator.ValidatorUtils;
import kit.pano.febs.common.validator.group.AddGroup;
import kit.pano.febs.common.validator.group.UpdateGroup;
import kit.pano.febs.web.domain.po.LitemallDeliver;
import kit.pano.febs.web.domain.vo.LitemallDeliverVO;
import kit.pano.febs.web.service.LitemallDeliverService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 商城配送人员 前端控制器
 * </p>
 *
 * @author Pano
 * @since 2019-10-26
 */
@Slf4j
@RestController
@RequestMapping("deliver")
public class LitemallDeliverController extends BaseController {

    @Resource
    private LitemallDeliverService litemallDeliverService;

    /**
     * 查询单个配送信息
     */
    @RequiresPermissions("deliver:view")
    @GetMapping("/{id}")
    public FebsResponse getDeliver(@PathVariable Long id) {
        LitemallDeliver deliver = litemallDeliverService.getById(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(deliver);
    }

    /**
     * 查询配送列表
     */
    @RequiresPermissions("deliver:view")
    @GetMapping
    public FebsResponse listDeliver(QueryRequest request, LitemallDeliverVO deliver) {
        Map<String, Object> dataTable = getDataTable(litemallDeliverService.listDeliver(deliver, request));
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(dataTable);
    }

    /**
     * 新增配送
     */
    @RequiresPermissions("deliver:manage")
    @PostMapping
    public FebsResponse addDeliver(@RequestBody LitemallDeliverVO deliverVO) throws FebsException {
        ValidatorUtils.validate(deliverVO, AddGroup.class);
        litemallDeliverService.addDeliver(deliverVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 修改配送
     */
    @RequiresPermissions("deliver:manage")
    @PutMapping("/{id}")
    public FebsResponse updateDeliver(@PathVariable Long id, @RequestBody LitemallDeliverVO deliverVO) throws FebsException {
        ValidatorUtils.validate(deliverVO, UpdateGroup.class);
        litemallDeliverService.updateDeliver(id, deliverVO);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 删除配送
     */
    @RequiresPermissions("deliver:manage")
    @DeleteMapping("/{id}")
    public FebsResponse deleteDeliver(@PathVariable Long id) {
        litemallDeliverService.deleteDeliver(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }

    /**
     * 店员禁用/启用
     */
    @PutMapping("/status")
    @RequiresPermissions("deliver:manage")
    public FebsResponse changeStatus(@RequestBody LitemallDeliverVO vo) {
        litemallDeliverService.changeStatus(vo.getId(), vo.getStatus());
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
    }
}

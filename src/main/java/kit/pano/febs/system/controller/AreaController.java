package kit.pano.febs.system.controller;


import cn.hutool.http.HttpStatus;
import kit.pano.febs.common.controller.BaseController;
import kit.pano.febs.common.domain.FebsResponse;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.system.domain.po.Area;
import kit.pano.febs.system.service.AreaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("area")
public class AreaController extends BaseController {

    @Resource
    private AreaService areaService;

    /**
     * 查询单个区域信息
     */
    @GetMapping("/{id}")
    public FebsResponse getArea(@PathVariable Long id) {
        Area area = areaService.getById(id);
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(area);
    }

    /**
     * 查询区域分页信息
     */
    @GetMapping
    public FebsResponse listArea(QueryRequest queryRequest, Area area) {
        Map<String, Object> dataTable = getDataTable(areaService.listArea(area, queryRequest));
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(dataTable);
    }

}

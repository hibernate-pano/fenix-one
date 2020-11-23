package kit.pano.febs.system.controller;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.wuwenze.poi.ExcelKit;
import kit.pano.febs.common.annotation.Log;
import kit.pano.febs.common.controller.BaseController;
import kit.pano.febs.common.domain.FebsResponse;
import kit.pano.febs.common.domain.QueryRequest;
import kit.pano.febs.common.exception.FebsException;
import kit.pano.febs.system.domain.po.SysLog;
import kit.pano.febs.system.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @author Pano
 */
@Slf4j
@Validated
@RestController
@RequestMapping("log")
public class LogController extends BaseController {

    private String message;

    @Resource
    private LogService logService;

    @GetMapping
    @RequiresPermissions("log:view")
    public FebsResponse logList(QueryRequest request, SysLog sysLog) {
        Map<String, Object> result = getDataTable(logService.findLogs(request, sysLog));
        return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功").data(result);
    }

    @Log("删除系统日志")
    @DeleteMapping("/{ids}")
    @RequiresPermissions("log:delete")
    public FebsResponse deleteLogs(@NotBlank(message = "{required}") @PathVariable String ids) throws FebsException {
        try {
            String[] logIds = ids.split(StringPool.COMMA);
            this.logService.deleteLogs(logIds);
            return new FebsResponse().code(HttpStatus.HTTP_OK).message("操作成功");
        } catch (Exception e) {
            message = "删除日志失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("excel")
    @RequiresPermissions("log:export")
    public void export(QueryRequest request, SysLog sysLog, HttpServletResponse response) throws FebsException {
        try {
            List<SysLog> sysLogs = this.logService.findLogs(request, sysLog).getRecords();
            ExcelKit.$Export(SysLog.class, response).downXlsx(sysLogs, false);
        } catch (Exception e) {
            message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}

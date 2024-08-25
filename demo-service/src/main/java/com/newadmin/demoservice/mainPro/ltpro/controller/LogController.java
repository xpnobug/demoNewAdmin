package com.newadmin.demoservice.mainPro.ltpro.controller;

import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.democore.kduck.web.json.JsonPageObject;
import com.newadmin.demoservice.mainPro.ltpro.query.LogQuery;
import com.newadmin.demoservice.mainPro.ltpro.service.LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.query.SortQuery;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志 API
 */
@Tag(name = "日志 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/log")
public class LogController {

    private final LogService logService;

    @Operation(summary = "分页查询列表", description = "分页查询列表")
//    @SaCheckPermission("monitor:log:list")
    @GetMapping
    public JsonPageObject page(LogQuery query, @Validated Page page) {
        return new JsonPageObject(page, logService.page(page, query));
    }

    @Operation(summary = "查询详情", description = "查询详情")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
//    @SaCheckPermission("monitor:log:list")
    @GetMapping("/{id}")
    public JsonObject get(@PathVariable String id) {
        return new JsonObject(logService.get(id));
    }

    @Operation(summary = "导出登录日志", description = "导出登录日志")
//    @SaCheckPermission("monitor:log:export")
    @GetMapping("/export/login")
    public void exportLoginLog(LogQuery query, SortQuery sortQuery, HttpServletResponse response) {
        logService.exportLoginLog(query, sortQuery, response);
    }

    @Operation(summary = "导出操作日志", description = "导出操作日志")
//    @SaCheckPermission("monitor:log:export")
    @GetMapping("/export/operation")
    public void exportOperationLog(LogQuery query, SortQuery sortQuery,
        HttpServletResponse response) {
        logService.exportOperationLog(query, sortQuery, response);
    }
}

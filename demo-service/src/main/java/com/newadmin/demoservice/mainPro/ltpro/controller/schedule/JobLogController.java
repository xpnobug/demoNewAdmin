package com.newadmin.demoservice.mainPro.ltpro.controller.schedule;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.democore.kduck.web.json.JsonPageObject;
import com.newadmin.demojob.config.PageResp;
import com.newadmin.demojob.model.JobInstanceLogPageResult;
import com.newadmin.demojob.model.query.JobInstanceLogQuery;
import com.newadmin.demojob.model.query.JobInstanceQuery;
import com.newadmin.demojob.model.query.JobLogQuery;
import com.newadmin.demojob.model.resp.JobInstanceResp;
import com.newadmin.demojob.model.resp.JobLogResp;
import com.newadmin.demojob.service.JobLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 任务日志 API
 *
 * @author KAI
 * @author couei
 * @author Charles7c
 * @since 2024/6/27 22:24
 */
@Tag(name = " 任务日志 API")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule/log")
public class JobLogController {

    private final JobLogService baseService;

    @Operation(summary = "分页查询任务日志列表", description = "分页查询任务日志列表")
//    @SaCheckPermission("schedule:log:list")
    @GetMapping
    public JsonPageObject page(Page page, JobLogQuery query) {
        return new JsonPageObject(page, baseService.page(query));
    }

    @Operation(summary = "停止任务", description = "停止任务")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
//    @SaCheckPermission("schedule:log:stop")
    @PostMapping("/stop/{id}")
    public void stop(@PathVariable Long id) {
        baseService.stop(id);
    }

    @Operation(summary = "重试任务", description = "重试任务")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
//    @SaCheckPermission("schedule:log:retry")
    @PostMapping("/retry/{id}")
    public void retry(@PathVariable Long id) {
        baseService.retry(id);
    }

    @Operation(summary = "查询任务实例列表", description = "查询任务实例列表")
//    @SaCheckPermission("schedule:log:list")
    @GetMapping("/instance")
    public List<JobInstanceResp> listInstance(JobInstanceQuery query) {
        return baseService.listInstance(query);
    }

    @Operation(summary = "分页查询任务实例日志列表", description = "分页查询任务实例日志列表")
//    @SaCheckPermission("schedule:log:list")
    @GetMapping("/instance/log")
    public JobInstanceLogPageResult pageInstanceLog(JobInstanceLogQuery query) {
        return baseService.pageInstanceLog(query);
    }
}

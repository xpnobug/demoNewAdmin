package com.newadmin.demoservice.mainPro.ltpro.controller.schedule;

import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.democore.kduck.web.json.JsonPageObject;
import com.newadmin.demojob.model.query.JobQuery;
import com.newadmin.demojob.model.req.JobReq;
import com.newadmin.demojob.model.req.JobStatusReq;
import com.newadmin.demojob.service.JobService;
import com.newadmin.demolog.log.core.annotation.Log;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 任务 API
 *
 * @author KAI
 * @author couei
 * @author Charles7c
 * @since 2024/6/25 22:24
 */
@Tag(name = " 任务 API")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule/job")
public class JobController {

    private final JobService baseService;

    @Operation(summary = "分页查询任务列表", description = "分页查询任务列表")
//    @SaCheckPermission("schedule:job:list")
    @GetMapping
    public JsonPageObject page(Page page, JobQuery query) {
        return new JsonPageObject(page, baseService.page(query));
    }

    @Operation(summary = "新增任务", description = "新增任务")
//    @SaCheckPermission("schedule:job:add")
    @PostMapping
    public void add(@RequestBody JobReq req) {
        baseService.add(req);
    }

    @Operation(summary = "修改任务", description = "修改任务")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
//    @SaCheckPermission("schedule:job:update")
    @PutMapping("/{id}")
    public void update(@RequestBody JobReq req, @PathVariable Long id) {
        baseService.update(req, id);
    }

    @Operation(summary = "修改任务状态", description = "修改任务状态")
//    @SaCheckPermission("schedule:job:update")
    @PatchMapping("/{id}/status")
    public void updateStatus(@Validated @RequestBody JobStatusReq req, @PathVariable Long id) {
        baseService.updateStatus(req, id);
    }

    @Operation(summary = "删除任务", description = "删除任务")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
//    @SaCheckPermission("schedule:job:delete")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        baseService.delete(id);
    }

    @Operation(summary = "执行任务", description = "执行任务")
    @Parameter(name = "id", description = "ID", example = "1", in = ParameterIn.PATH)
//    @SaCheckPermission("schedule:job:trigger")
    @PostMapping("/trigger/{id}")
    public void trigger(@PathVariable Long id) {
        baseService.trigger(id);
    }

    @Log(ignore = true)
    @Operation(summary = "查询任务分组列表", description = "查询任务分组列表")
//    @SaCheckPermission("schedule:job:list")
    @GetMapping("/group")
    public JsonObject listGroup() {
        return new JsonObject(baseService.listGroup());
    }
}

package com.newadmin.demojob.api;

import com.aizuda.snailjob.common.core.model.Result;
import com.newadmin.demojob.model.JobInstanceLogPageResult;
import com.newadmin.demojob.model.JobPageResult;
import com.newadmin.demojob.model.resp.JobInstanceResp;
import com.newadmin.demojob.model.resp.JobLogResp;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

/**
 * 任务批次 REST API
 *
 * @author KAI
 * @author couei
 * @author Charles7c
 * @since 2024/6/27 23:03
 */
@HttpExchange(value = "/job", accept = MediaType.APPLICATION_JSON_VALUE)
public interface JobBatchApi {

    /**
     * 分页查询列表
     *
     * @param jobId           任务 ID
     * @param jobName         任务名称
     * @param groupName       组名称
     * @param taskBatchStatus 任务批次状态
     * @param datetimeRange   时间范围
     * @param page            页码
     * @param size            每页条数
     * @return 响应信息
     */
    @GetExchange("/batch/list")
    ResponseEntity<JobPageResult<List<JobLogResp>>> page(
        @RequestParam(value = "jobId", required = false) Long jobId,
        @RequestParam(value = "jobName", required = false) String jobName,
        @RequestParam(value = "groupName", required = false) String groupName,
        @RequestParam(value = "taskBatchStatus", required = false) Integer taskBatchStatus,
        @RequestParam(value = "datetimeRange", required = false) String[] datetimeRange,
        @RequestParam(value = "page") Integer page,
        @RequestParam(value = "size") Integer size);

    /**
     * 停止
     *
     * @param id ID
     * @return 响应信息
     */
    @PostExchange("/batch/stop/{id}")
    ResponseEntity<Result<Boolean>> stop(@PathVariable("id") Long id);

    /**
     * 重试
     *
     * @param id ID
     * @return 响应信息
     */
    @PostExchange("/batch/retry/{id}")
    ResponseEntity<Result<Boolean>> retry(@PathVariable("id") Long id);

    /**
     * 分页查询任务实例列表
     *
     * @param jobId       任务 ID
     * @param taskBatchId 任务批次 ID
     * @param page        页码
     * @param size        每页条数
     * @return 响应信息
     */
    @GetExchange("/task/list")
    ResponseEntity<JobPageResult<List<JobInstanceResp>>> pageTask(
        @RequestParam(value = "jobId", required = false) Long jobId,
        @RequestParam(value = "taskBatchId") Long taskBatchId,
        @RequestParam(value = "page") Integer page,
        @RequestParam(value = "size") Integer size);

    /**
     * 分页查询任务实例日志列表
     *
     * @param jobId       任务 ID
     * @param taskBatchId 任务批次 ID
     * @param fromIndex   起始索引
     * @param size        每页条数
     * @return 响应信息
     */
    @GetExchange("/log/list")
    ResponseEntity<Result<JobInstanceLogPageResult>> pageLog(
        @RequestParam(value = "jobId", required = false) Long jobId,
        @RequestParam(value = "taskBatchId") Long taskBatchId,
        @RequestParam(value = "taskId") Long taskId,
        @RequestParam(value = "startId") Integer startId,
        @RequestParam(value = "fromIndex") Integer fromIndex,
        @RequestParam(value = "size") Integer size);
}

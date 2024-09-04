package com.newadmin.demojob.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.newadmin.demojob.api.JobBatchApi;
import com.newadmin.demojob.api.JobClient;
import com.newadmin.demojob.model.JobInstanceLogPageResult;
import com.newadmin.demojob.model.query.JobInstanceLogQuery;
import com.newadmin.demojob.model.query.JobInstanceQuery;
import com.newadmin.demojob.model.query.JobLogQuery;
import com.newadmin.demojob.model.resp.JobInstanceResp;
import com.newadmin.demojob.model.resp.JobLogResp;
import com.newadmin.demojob.service.JobLogService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 任务日志业务实现
 *
 * @author KAI
 * @author couei
 * @author Charles7c
 * @since 2024/6/27 22:54
 */
@Service
@RequiredArgsConstructor
public class JobLogServiceImpl implements JobLogService {

    private final JobClient jobClient;
    private final JobBatchApi jobBatchApi;

    @Override
    public List<JobLogResp> page(JobLogQuery query) {
        // 获取时间范围
        LocalDateTime[] datetimeRange = query.getDatetimeRange();
        // 调用分页接口
        return jobClient.requestPage(
            () -> jobBatchApi.page(query.getJobId(), query.getJobName(), query
                    .getGroupName(), query.getTaskBatchStatus() != null
                    ? query.getTaskBatchStatus().getValue()
                    : null,
                new String[]{DateUtil.format(datetimeRange[0], DatePattern.UTC_SIMPLE_PATTERN),
                    DateUtil
                        .format(datetimeRange[1], DatePattern.UTC_SIMPLE_PATTERN)}, query.getPage(),
                query.getSize()));
    }

    @Override
    public boolean stop(Long id) {
        return Boolean.TRUE.equals(jobClient.request(() -> jobBatchApi.stop(id)));
    }

    @Override
    public boolean retry(Long id) {
        return Boolean.TRUE.equals(jobClient.request(() -> jobBatchApi.retry(id)));
    }

    @Override
    public List<JobInstanceResp> listInstance(JobInstanceQuery query) {
        return jobClient.requestPage(
            () -> jobBatchApi.pageTask(query.getJobId(), query.getTaskBatchId(), 1, 100));
    }

    @Override
    public JobInstanceLogPageResult pageInstanceLog(JobInstanceLogQuery query) {
        return Objects.requireNonNull(
                jobBatchApi.pageLog(query.getJobId(), query.getTaskBatchId(), query
                    .getTaskId(), query.getStartId(), query.getFromIndex(), query.getSize()).getBody())
            .getData();
    }
}

package com.newadmin.demojob.service.impl;

import com.newadmin.demojob.api.JobApi;
import com.newadmin.demojob.api.JobClient;
import com.newadmin.demojob.config.PageResp;
import com.newadmin.demojob.model.query.JobQuery;
import com.newadmin.demojob.model.req.JobReq;
import com.newadmin.demojob.model.req.JobStatusReq;
import com.newadmin.demojob.model.resp.JobResp;
import com.newadmin.demojob.service.JobService;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 任务业务实现
 *
 * @author KAI
 * @author couei
 * @author Charles7c
 * @since 2024/6/25 17:25
 */
@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobClient jobClient;
    private final JobApi jobApi;

    @Override
    public List<JobResp> page(JobQuery query) {
        return jobClient.requestPage(() ->
            jobApi.page(query.getGroupName(), query.getJobName(), query.getJobStatus() != null
                ? query.getJobStatus().getValue() : null, query.getPage(), query.getSize()));
    }

    @Override
    public boolean add(JobReq req) {
        return Boolean.TRUE.equals(jobClient.request(() -> jobApi.add(req)));
    }

    @Override
    public boolean update(JobReq req, Long id) {
        req.setId(id);
        return Boolean.TRUE.equals(jobClient.request(() -> jobApi.update(req)));
    }

    @Override
    public boolean updateStatus(JobStatusReq req, Long id) {
        req.setId(id);
        return Boolean.TRUE.equals(jobClient.request(() -> jobApi.updateStatus(req)));
    }

    @Override
    public boolean delete(Long id) {
        return Boolean.TRUE.equals(
            jobClient.request(() -> jobApi.delete(Collections.singleton(id))));
    }

    @Override
    public boolean trigger(Long id) {
        return Boolean.TRUE.equals(jobClient.request(() -> jobApi.trigger(id)));
    }

    @Override
    public List<String> listGroup() {
        return jobClient.request(jobApi::listGroup);
    }
}
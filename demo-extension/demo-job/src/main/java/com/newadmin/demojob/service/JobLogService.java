package com.newadmin.demojob.service;

import com.newadmin.demojob.config.PageResp;
import com.newadmin.demojob.model.JobInstanceLogPageResult;
import com.newadmin.demojob.model.query.JobInstanceLogQuery;
import com.newadmin.demojob.model.query.JobInstanceQuery;
import com.newadmin.demojob.model.query.JobLogQuery;
import com.newadmin.demojob.model.resp.JobInstanceResp;
import com.newadmin.demojob.model.resp.JobLogResp;
import java.util.List;

/**
 * 任务日志业务接口
 *
 * @author KAI
 * @author couei
 * @author Charles7c
 * @since 2024/6/27 22:52
 */
public interface JobLogService {

    /**
     * 分页查询列表
     *
     * @param query 查询条件
     * @return 分页列表信息
     */
    List<JobLogResp> page(JobLogQuery query);

    /**
     * 停止
     *
     * @param id ID
     * @return 停止结果
     */
    boolean stop(Long id);

    /**
     * 重试
     *
     * @param id ID
     * @return 重试结果
     */
    boolean retry(Long id);

    /**
     * 查询任务实例列表
     *
     * @param query 查询条件
     * @return 列表信息
     */
    List<JobInstanceResp> listInstance(JobInstanceQuery query);

    /**
     * 分页查询任务实例日志列表
     *
     * @param query 查询条件
     * @return 分页列表信息
     */
    JobInstanceLogPageResult pageInstanceLog(JobInstanceLogQuery query);
}

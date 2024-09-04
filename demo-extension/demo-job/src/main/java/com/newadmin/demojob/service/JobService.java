package com.newadmin.demojob.service;

import com.newadmin.demojob.config.PageResp;
import com.newadmin.demojob.model.query.JobQuery;
import com.newadmin.demojob.model.req.JobReq;
import com.newadmin.demojob.model.req.JobStatusReq;
import com.newadmin.demojob.model.resp.JobResp;
import java.util.List;

/**
 * 任务业务接口
 *
 * @author KAI
 * @author couei
 * @author Charles7c
 * @since 2024/6/25 17:20
 */
public interface JobService {

    /**
     * 分页查询列表
     *
     * @param query 查询条件
     * @return 分页列表信息
     */
    List<JobResp> page(JobQuery query);

    /**
     * 新增
     *
     * @param req 创建信息
     * @return 新增结果
     */
    boolean add(JobReq req);

    /**
     * 修改
     *
     * @param req 修改信息
     * @param id  ID
     * @return 修改结果
     */
    boolean update(JobReq req, Long id);

    /**
     * 修改状态
     *
     * @param req 修改状态信息
     * @param id  ID
     * @return 修改状态结果
     */
    boolean updateStatus(JobStatusReq req, Long id);

    /**
     * 删除
     *
     * @param id ID
     * @return 删除结果
     */
    boolean delete(Long id);

    /**
     * 执行
     *
     * @param id ID
     * @return 执行结果
     */
    boolean trigger(Long id);

    /**
     * 查询分组列表
     *
     * @return 分组列表
     */
    List<String> listGroup();
}

package com.newadmin.demoservice.mainPro.ltpro.service;

import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.demoservice.mainPro.ltpro.entity.LogDO;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.resp.DashboardAccessTrendResp;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.resp.DashboardPopularModuleResp;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.resp.DashboardTotalResp;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.resp.log.LogDetailResp;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.resp.log.LogResp;
import com.newadmin.demoservice.mainPro.ltpro.query.LogQuery;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import org.springframework.data.redis.core.query.SortQuery;

/**
 * 系统日志业务接口
 */
public interface LogService {

    LogDO addLog(LogDO logDO);

    /**
     * 分页查询列表
     *
     * @param query 查询条件
     * @return 分页列表信息
     */
    List<LogResp> page(Page page, LogQuery query);

    /**
     * 查询详情
     *
     * @param id ID
     * @return 详情信息
     */
    LogDetailResp get(String id);

    /**
     * 导出登录日志
     *
     * @param query     查询条件
     * @param sortQuery 排序查询条件
     * @param response  响应对象
     */
    void exportLoginLog(LogQuery query, SortQuery sortQuery, HttpServletResponse response);

    /**
     * 导出操作日志
     *
     * @param query     查询条件
     * @param sortQuery 排序查询条件
     * @param response  响应对象
     */
    void exportOperationLog(LogQuery query, SortQuery sortQuery, HttpServletResponse response);

    /**
     * 查询仪表盘总计信息
     *
     * @return 仪表盘总计信息
     */
    DashboardTotalResp getDashboardTotal();

    /**
     * 查询仪表盘访问趋势信息
     *
     * @param days 日期数
     * @return 仪表盘访问趋势信息
     */
    List<DashboardAccessTrendResp> listDashboardAccessTrend(Integer days);

    /**
     * 查询仪表盘热门模块列表
     *
     * @return 仪表盘热门模块列表
     */
    List<DashboardPopularModuleResp> listDashboardPopularModule();

    /**
     * 查询仪表盘访客地域分布信息
     *
     * @return 仪表盘访客地域分布信息
     */
    List<Map<String, Object>> listDashboardGeoDistribution();
}

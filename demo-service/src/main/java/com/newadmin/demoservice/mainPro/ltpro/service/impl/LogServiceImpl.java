package com.newadmin.demoservice.mainPro.ltpro.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.democore.util.validate.CheckUtils;
import com.newadmin.demoservice.config.excel.util.ExcelUtils;
import com.newadmin.demoservice.mainPro.ltpro.entity.LogDO;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.resp.DashboardAccessTrendResp;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.resp.DashboardGeoDistributionResp;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.resp.DashboardPopularModuleResp;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.resp.DashboardTotalResp;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.resp.log.LogDetailResp;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.resp.log.LogResp;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.resp.log.LoginLogExportResp;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.resp.log.OperationLogExportResp;
import com.newadmin.demoservice.mainPro.ltpro.query.LogQuery;
import com.newadmin.demoservice.mainPro.ltpro.service.LogService;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.query.SortQuery;
import org.springframework.stereotype.Service;

/**
 * 日志业务实现
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LogServiceImpl extends DefaultService implements LogService {

    public static final String TABLE_NAME = "reai_log";

    @Override
    public LogDO addLog(LogDO logDO) {
        String logId = super.add(TABLE_NAME, logDO).toString();
        return logDO;
    }

    @Override
    public List<LogResp> page(LogQuery query) {
        ValueMap params = new ValueMap();
        params.put(LogResp.DESCRIPTION, query.getDescription());
        params.put(LogResp.MODULE, query.getModule());
        params.put(LogResp.IP, query.getIp());
        params.put(LogResp.CREATE_USER_STRING, query.getCreateUserString());
        params.put(LogResp.CREATE_TIME, query.getCreateTime());
        params.put(LogResp.STATUS, query.getStatus());
        SelectBuilder selectBuilder = new SelectBuilder(params);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
            .and("description", ConditionType.EQUALS, LogResp.DESCRIPTION)
            .and("module", ConditionType.EQUALS, LogResp.MODULE)
            .and("ip", ConditionType.EQUALS, LogResp.IP)
            .and("create_user_string", ConditionType.EQUALS, LogResp.CREATE_USER_STRING)
            .and("create_time", ConditionType.EQUALS, LogResp.CREATE_TIME)
            .and("status", ConditionType.EQUALS, LogResp.STATUS)
            .orderBy().desc("create_time");
        return super.listForBean(selectBuilder.build(), LogResp::new);
    }

    @Override
    public LogDetailResp get(String id) {
        ValueMap params = new ValueMap();
        params.put(LogResp.ID, id);
        SelectBuilder selectBuilder = new SelectBuilder(params);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
            .and("id", ConditionType.EQUALS, LogResp.ID);
        LogDO logDO = super.getForBean(selectBuilder.build(), LogDO::new);
        CheckUtils.throwIfNotExists(logDO, "LogDO", "ID", id);
        return BeanUtil.copyProperties(logDO, LogDetailResp.class);
    }

    @Override
    public void exportLoginLog(LogQuery query, SortQuery sortQuery, HttpServletResponse response) {
        List<LoginLogExportResp> list = BeanUtil.copyToList(this.list(query, sortQuery),
            LoginLogExportResp.class);
        ExcelUtils.export(list, "导出登录日志数据", LoginLogExportResp.class, response);
    }

    @Override
    public void exportOperationLog(LogQuery query, SortQuery sortQuery,
        HttpServletResponse response) {
        List<OperationLogExportResp> list = BeanUtil.copyToList(this
            .list(query, sortQuery), OperationLogExportResp.class);
        ExcelUtils.export(list, "导出操作日志数据", OperationLogExportResp.class, response);
    }

    @Override
    public DashboardTotalResp getDashboardTotal() {
        String sql = "SELECT\n"
            + "            (SELECT COUNT(*) FROM reai_log) AS pvCount,\n"
            + "            (SELECT COUNT(DISTINCT ip) FROM sys_log) AS ipCount,\n"
            + "            (SELECT COUNT(*) FROM sys_log WHERE DATE(create_time) = CURRENT_DATE) AS todayPvCount,\n"
            + "            (SELECT COUNT(*) FROM sys_log WHERE DATE(create_time) = CURRENT_DATE - 1) AS yesterdayPvCount";
        SelectBuilder selectBuilder = new SelectBuilder(sql);
        return super.getForBean(selectBuilder.build(), DashboardTotalResp::new);
    }

    @Override
    public List<DashboardAccessTrendResp> listDashboardAccessTrend(Integer days) {
        //SELECT
        //            DATE(create_time) AS date,
        //            COUNT(*) AS pvCount,
        //            COUNT(DISTINCT ip) AS ipCount
        //        FROM sys_log
        //        WHERE DATE(create_time) != CURRENT_DATE
        //        GROUP BY DATE(create_time)
        //        ORDER BY DATE(create_time) DESC
        //        LIMIT #{days}
        ValueMap params = new ValueMap();
        params.put("days", days);
        SelectBuilder selectBuilder = new SelectBuilder(params);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
            .and("create_time", ConditionType.NOT_EQUALS, "CURRENT_DATE")
            .groupBy("create_time")
            .orderBy().desc("create_time");
        return super.listForBean(selectBuilder.build(), DashboardAccessTrendResp::new);
    }

    @Override
    public List<DashboardPopularModuleResp> listDashboardPopularModule() {
        String sql = "SELECT\n"
            + "            module,\n"
            + "            COUNT(*) AS pvCount,\n"
            + "            SUM(CASE WHEN DATE(create_time) = CURRENT_DATE THEN 1 ELSE 0 END) AS todayPvCount,\n"
            + "            SUM(CASE WHEN DATE(create_time) = CURRENT_DATE - 1 THEN 1 ELSE 0 END) AS yesterdayPvCount\n"
            + "        FROM reai_log\n"
            + "        WHERE module != '验证码' AND module != '登录'\n"
            + "        GROUP BY module\n"
            + "        ORDER BY pvCount DESC";
        SelectBuilder selectBuilder = new SelectBuilder(sql);
        return super.listForBean(selectBuilder.build(), DashboardPopularModuleResp::new);
    }

    @Override
    public List<Map<String, Object>> listDashboardGeoDistribution() {
        String sql = "SELECT\n"
            + "            CASE\n"
            + "                WHEN POSITION(' ' IN address) > 0 THEN SUBSTRING(address FROM 1 FOR POSITION(' ' IN address) - 1)\n"
            + "                ELSE address\n"
            + "            END AS name,\n"
            + "            COUNT(DISTINCT ip) AS value\n"
            + "        FROM reai_log\n"
            + "        GROUP BY name\n"
            + "        ORDER BY value DESC";
        SelectBuilder selectBuilder = new SelectBuilder(sql);
        List<DashboardGeoDistributionResp> dashboardGeoDistributionResps = super.listForBean(
            selectBuilder.build(), DashboardGeoDistributionResp::new);
        return dashboardGeoDistributionResps.stream().map(DashboardGeoDistributionResp::new)
            .collect(
                Collectors.toList());
    }

    /**
     * 查询列表
     *
     * @param query     查询条件
     * @param sortQuery 排序查询条件
     * @return 列表信息
     */
    private List<LogResp> list(LogQuery query, SortQuery sortQuery) {
        QueryWrapper<LogDO> queryWrapper = this.buildQueryWrapper(query);

        String sql = " SELECT\n"
            + "            t1.id,\n"
            + "            t1.description,\n"
            + "            t1.module,\n"
            + "            t1.time_taken,\n"
            + "            t1.ip,\n"
            + "            t1.address,\n"
            + "            t1.browser,\n"
            + "            t1.os,\n"
            + "            t1.status,\n"
            + "            t1.error_msg,\n"
            + "            t1.create_user,\n"
            + "            t1.create_time,\n"
            + "            t2.nickname AS createUserString\n"
            + "        FROM reai_log AS t1\n"
            + "        LEFT JOIN reai_user AS t2 ON t2.user_id = t1.create_user";
        SelectBuilder selectBuilder = new SelectBuilder(sql);
        return super.listForBean(selectBuilder.build(), LogResp::new);
    }

    /**
     * 构建 QueryWrapper
     *
     * @param query 查询条件
     * @return QueryWrapper
     */
    private QueryWrapper<LogDO> buildQueryWrapper(LogQuery query) {
        String description = query.getDescription();
        String module = query.getModule();
        String ip = query.getIp();
        String createUserString = query.getCreateUserString();
        Integer status = query.getStatus();
        List<Date> createTimeList = query.getCreateTime();
        return new QueryWrapper<LogDO>().and(StrUtil.isNotBlank(description),
                q -> q.like("t1.description", description)
                    .or()
                    .like("t1.module", description))
            .eq(StrUtil.isNotBlank(module), "t1.module", module)
            .and(StrUtil.isNotBlank(ip), q -> q.like("t1.ip", ip).or().like("t1.address", ip))
            .and(StrUtil.isNotBlank(createUserString), q -> q.like("t2.username", createUserString)
                .or()
                .like("t2.nickname", createUserString))
            .eq(null != status, "t1.status", status)
            .between(CollUtil.isNotEmpty(createTimeList), "t1.create_time",
                CollUtil.getFirst(createTimeList), CollUtil
                    .getLast(createTimeList));
    }
}

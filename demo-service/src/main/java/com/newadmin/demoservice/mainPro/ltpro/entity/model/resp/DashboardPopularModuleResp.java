package com.newadmin.demoservice.mainPro.ltpro.entity.model.resp;

import com.newadmin.democore.kduck.service.ValueMap;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.Map;

/**
 * 仪表盘-热门模块信息
 */
@Schema(description = "仪表盘-热门模块信息")
public class DashboardPopularModuleResp extends ValueMap {

    /**
     * 模块
     */
    public static final String MODULE = "module";

    /**
     * 浏览量（PV）
     */
    public static final String PV_COUNT = "pvCount";

    /**
     * 较昨日新增 PV（百分比）
     */
    public static final String NEW_PV_FROM_YESTERDAY = "newPvFromYesterday";

    /**
     * 今日浏览量（PV）
     */
    public static final String TODAY_PV_COUNT = "todayPvCount";

    /**
     * 昨日浏览量（PV）
     */
    public static final String YESTERDAY_PV_COUNT = "yesterdayPvCount";

    public DashboardPopularModuleResp() {
    }

    public DashboardPopularModuleResp(Map<String, Object> map) {
        super(map);
    }

    /**
     * 设置 模块
     *
     * @param module 模块
     */
    public void setModule(String module) {
        super.setValue(MODULE, module);
    }

    /**
     * 获取 模块
     *
     * @return 模块
     */
    public String getModule() {
        return super.getValueAsString(MODULE);
    }

    /**
     * 设置 浏览量（PV）
     *
     * @param pvCount 浏览量（PV）
     */
    public void setPvCount(Long pvCount) {
        super.setValue(PV_COUNT, pvCount);
    }

    /**
     * 获取 浏览量（PV）
     *
     * @return 浏览量（PV）
     */
    public Long getPvCount() {
        return super.getValueAsLong(PV_COUNT);
    }

    /**
     * 设置 较昨日新增 PV（百分比）
     *
     * @param newPvFromYesterday 较昨日新增 PV（百分比）
     */
    public void setNewPvFromYesterday(BigDecimal newPvFromYesterday) {
        super.setValue(NEW_PV_FROM_YESTERDAY, newPvFromYesterday);
    }

    /**
     * 获取 较昨日新增 PV（百分比）
     *
     * @return 较昨日新增 PV（百分比）
     */
    public String getNewPvFromYesterday() {
        return super.getValueAsString(NEW_PV_FROM_YESTERDAY);
    }

    /**
     * 设置 今日浏览量（PV）
     *
     * @param todayPvCount 今日浏览量（PV）
     */
    public void setTodayPvCount(Long todayPvCount) {
        super.setValue(TODAY_PV_COUNT, todayPvCount);
    }

    /**
     * 获取 今日浏览量（PV）
     *
     * @return 今日浏览量（PV）
     */
    public Long getTodayPvCount() {
        return super.getValueAsLong(TODAY_PV_COUNT);
    }

    /**
     * 设置 昨日浏览量（PV）
     *
     * @param yesterdayPvCount 昨日浏览量（PV）
     */
    public void setYesterdayPvCount(Long yesterdayPvCount) {
        super.setValue(YESTERDAY_PV_COUNT, yesterdayPvCount);
    }

    /**
     * 获取 昨日浏览量（PV）
     *
     * @return 昨日浏览量（PV）
     */
    public Long getYesterdayPvCount() {
        return super.getValueAsLong(YESTERDAY_PV_COUNT);
    }
}

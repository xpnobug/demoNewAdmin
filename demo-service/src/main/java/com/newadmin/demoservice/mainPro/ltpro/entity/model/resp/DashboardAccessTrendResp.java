package com.newadmin.demoservice.mainPro.ltpro.entity.model.resp;

import com.newadmin.democore.kduck.service.ValueMap;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Map;

/**
 * 仪表盘-访问趋势信息
 */

@Schema(description = "仪表盘-访问趋势信息")
public class DashboardAccessTrendResp extends ValueMap {

    /**
     * 日期
     */
    public static final String DATE = "date";

    /**
     * 浏览量（PV）
     */
    public static final String PV_COUNT = "pvCount";

    /**
     * IP 数
     */
    public static final String IP_COUNT = "ipCount";

    public DashboardAccessTrendResp() {
    }

    public DashboardAccessTrendResp(Map<String, Object> map) {
        super(map);
    }

    /**
     * 设置 日期
     *
     * @param date 日期
     */
    public void setDate(String date) {
        super.setValue(DATE, date);
    }

    /**
     * 获取 日期
     *
     * @return 日期
     */
    public String getDate() {
        return super.getValueAsString(DATE);
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
     * 设置 IP 数
     *
     * @param ipCount IP 数
     */
    public void setIpCount(Long ipCount) {
        super.setValue(IP_COUNT, ipCount);
    }

    /**
     * 获取 IP 数
     *
     * @return IP 数
     */
    public Long getIpCount() {
        return super.getValueAsLong(IP_COUNT);
    }
}

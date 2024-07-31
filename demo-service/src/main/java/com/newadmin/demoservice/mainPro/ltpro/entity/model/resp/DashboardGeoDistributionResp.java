package com.newadmin.demoservice.mainPro.ltpro.entity.model.resp;

import com.newadmin.democore.kduck.service.ValueMap;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Map;

/**
 * 仪表盘-访客地域分布信息
 */
@Schema(description = "仪表盘-访客地域分布信息")
public class DashboardGeoDistributionResp extends ValueMap {

    /**
     * 地点列表
     */
    public static final String LOCATIONS = "locations";

    /**
     * 地点 IP 统计信息
     */
    public static final String LOCATION_IP_STATISTICS = "locationIpStatistics";

    public DashboardGeoDistributionResp() {
    }

    public DashboardGeoDistributionResp(Map<String, Object> map) {
        super(map);
    }

    /**
     * 设置 地点列表
     *
     * @param locations 地点列表
     */
    public void setLocations(List<String> locations) {
        super.setValue(LOCATIONS, locations);
    }

    /**
     * 获取 地点列表
     *
     * @return 地点列表
     */
    public List<String> getLocations() {
        return super.getValueAsList(LOCATIONS);
    }

    /**
     * 设置 地点 IP 统计信息
     *
     * @param locationIpStatistics 地点 IP 统计信息
     */
    public void setLocationIpStatistics(List<Map<String, Object>> locationIpStatistics) {
        super.setValue(LOCATION_IP_STATISTICS, locationIpStatistics);
    }

    /**
     * 获取 地点 IP 统计信息
     *
     * @return 地点 IP 统计信息
     */
    public List<Map<String, Object>> getLocationIpStatistics() {
        return super.getValueAsList(LOCATION_IP_STATISTICS);
    }
}

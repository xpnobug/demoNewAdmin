package com.newadmin.demoservice.mainPro.livepro.model.query;

import com.newadmin.democore.kduck.service.ValueMap;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.util.Map;

/**
 * 直播间查询条件
 *
 * @author couei
 * @since 2024/08/05 21:29
 */

@Schema(description = "直播间查询条件")
public class LiveRoomQuery extends ValueMap {

    @Serial
    private static final long serialVersionUID = 1L;

    /***/
    public static final String IS_SHOW = "isShow";

    public LiveRoomQuery() {
    }

    public LiveRoomQuery(Map<String, Object> map) {
        super(map);
    }

    /**
     * 设置
     *
     * @param isShow
     */
    public void setIsShow(Integer isShow) {
        super.setValue(IS_SHOW, isShow);
    }

    /**
     * 获取
     *
     * @return
     */
    public Integer getIsShow() {
        return super.getValueAsInteger(IS_SHOW);
    }
}
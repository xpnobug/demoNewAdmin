package com.newadmin.demoservice.mainPro.livepro.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.newadmin.democore.kduck.service.ValueMap;
import java.util.Date;
import java.util.Map;

/**
 * 用户与直播间的关联，存储用户和直播间的对应关系实体
 *
 * @author couei
 * @since 2024/08/11 15:04
 */

@TableName("user_live_room")
public class UserLiveRoomDO extends ValueMap {

    /**
     * 用户ID，关联到用户表的主键
     */
    public static final String USER_ID = "userId";

    /**
     * 直播间ID，关联到直播间表的主键
     */
    public static final String LIVE_ROOM_ID = "liveRoomId";

    /***/
    public static final String CREATE_TIME = "createTime";
    /***/
    public static final String UPDATE_TIME = "updateTime";

    public UserLiveRoomDO() {
    }

    public UserLiveRoomDO(Map<String, Object> map) {
        super(map);
    }

    /**
     * 设置 用户ID，关联到用户表的主键
     *
     * @param userId 用户ID，关联到用户表的主键
     */
    public void setUserId(String userId) {
        super.setValue(USER_ID, userId);
    }

    /**
     * 获取 用户ID，关联到用户表的主键
     *
     * @return 用户ID，关联到用户表的主键
     */
    public String getUserId() {
        return super.getValueAsString(USER_ID);
    }

    /**
     * 设置 直播间ID，关联到直播间表的主键
     *
     * @param liveRoomId 直播间ID，关联到直播间表的主键
     */
    public void setLiveRoomId(String liveRoomId) {
        super.setValue(LIVE_ROOM_ID, liveRoomId);
    }

    /**
     * 获取 直播间ID，关联到直播间表的主键
     *
     * @return 直播间ID，关联到直播间表的主键
     */
    public String getLiveRoomId() {
        return super.getValueAsString(LIVE_ROOM_ID);
    }

    /**
     * 设置
     *
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        super.setValue(CREATE_TIME, createTime);
    }

    /**
     * 获取
     *
     * @return
     */
    public Date getCreateTime() {
        return super.getValueAsDate(CREATE_TIME);
    }

    /**
     * 设置
     *
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        super.setValue(UPDATE_TIME, updateTime);
    }

    /**
     * 获取
     *
     * @return
     */
    public Date getUpdateTime() {
        return super.getValueAsDate(UPDATE_TIME);
    }
}
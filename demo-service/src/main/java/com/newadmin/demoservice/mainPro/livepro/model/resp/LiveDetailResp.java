package com.newadmin.demoservice.mainPro.livepro.model.resp;

import com.newadmin.democore.kduck.service.ValueMap;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import java.util.Map;

/**
 * 直播记录详情信息
 *
 * @author couei
 * @since 2024/08/05 22:02
 */

@Schema(description = "直播记录详情信息")
public class LiveDetailResp extends ValueMap {

    /***/
    public static final String ID = "id";
    /**
     * Socket连接的唯一标识
     */
    public static final String SOCKET_ID = "socketId";

    /**
     * 直播间ID
     */
    public static final String LIVE_ROOM_ID = "liveRoomId";

    /**
     * 用户ID
     */
    public static final String USER_ID = "userId";

    /**
     * 视频轨道标识（0：关闭，1：开启）
     */
    public static final String TRACK_VIDEO = "trackVideo";

    /**
     * 音频轨道标识（0：关闭，1：开启）
     */
    public static final String TRACK_AUDIO = "trackAudio";

    /**
     * SRS服务器ID
     */
    public static final String SRS_SERVER_ID = "srsServerId";

    /**
     * SRS服务ID
     */
    public static final String SRS_SERVICE_ID = "srsServiceId";

    /**
     * SRS动作类型
     */
    public static final String SRS_ACTION = "srsAction";

    /**
     * SRS客户端ID
     */
    public static final String SRS_CLIENT_ID = "srsClientId";

    /**
     * SRS客户端IP地址
     */
    public static final String SRS_IP = "srsIp";

    /**
     * SRS虚拟主机名
     */
    public static final String SRS_VHOST = "srsVhost";

    /**
     * SRS应用名
     */
    public static final String SRS_APP = "srsApp";

    /**
     * SRS tcUrl地址
     */
    public static final String SRS_TCURL = "srsTcurl";

    /**
     * SRS流名
     */
    public static final String SRS_STREAM = "srsStream";

    /**
     * SRS流参数
     */
    public static final String SRS_PARAM = "srsParam";

    /**
     * SRS流地址
     */
    public static final String SRS_STREAM_URL = "srsStreamUrl";

    /**
     * SRS流ID
     */
    public static final String SRS_STREAM_ID = "srsStreamId";

    /**
     * 是否为腾讯云CSS流标识（0：否，1：是）
     */
    public static final String IS_TENCENTCLOUD_CSS = "isTencentcloudCss";

    /**
     * 标识ID
     */
    public static final String FLAG_ID = "flagId";

    /**
     * 更新时间
     */
    public static final String UPDATED_TIME = "updatedTime";

    /**
     * 删除时间（软删除标识）
     */
    public static final String DELETED_TIME = "deletedTime";

    public LiveDetailResp() {
    }

    public LiveDetailResp(Map<String, Object> map) {
        super(map);
    }

    /**
     * 设置 Socket连接的唯一标识
     *
     * @param socketId Socket连接的唯一标识
     */
    public void setSocketId(String socketId) {
        super.setValue(SOCKET_ID, socketId);
    }

    /**
     * 获取 Socket连接的唯一标识
     *
     * @return Socket连接的唯一标识
     */
    public String getSocketId() {
        return super.getValueAsString(SOCKET_ID);
    }

    /**
     * 设置 直播间ID
     *
     * @param liveRoomId 直播间ID
     */
    public void setLiveRoomId(Integer liveRoomId) {
        super.setValue(LIVE_ROOM_ID, liveRoomId);
    }

    /**
     * 获取 直播间ID
     *
     * @return 直播间ID
     */
    public Integer getLiveRoomId() {
        return super.getValueAsInteger(LIVE_ROOM_ID);
    }

    /**
     * 设置 用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        super.setValue(USER_ID, userId);
    }

    /**
     * 获取 用户ID
     *
     * @return 用户ID
     */
    public String getUserId() {
        return super.getValueAsString(USER_ID);
    }

    /**
     * 设置 视频轨道标识（0：关闭，1：开启）
     *
     * @param trackVideo 视频轨道标识（0：关闭，1：开启）
     */
    public void setTrackVideo(Integer trackVideo) {
        super.setValue(TRACK_VIDEO, trackVideo);
    }

    /**
     * 获取 视频轨道标识（0：关闭，1：开启）
     *
     * @return 视频轨道标识（0：关闭，1：开启）
     */
    public Integer getTrackVideo() {
        return super.getValueAsInteger(TRACK_VIDEO);
    }

    /**
     * 设置 音频轨道标识（0：关闭，1：开启）
     *
     * @param trackAudio 音频轨道标识（0：关闭，1：开启）
     */
    public void setTrackAudio(Integer trackAudio) {
        super.setValue(TRACK_AUDIO, trackAudio);
    }

    /**
     * 获取 音频轨道标识（0：关闭，1：开启）
     *
     * @return 音频轨道标识（0：关闭，1：开启）
     */
    public Integer getTrackAudio() {
        return super.getValueAsInteger(TRACK_AUDIO);
    }

    /**
     * 设置 SRS服务器ID
     *
     * @param srsServerId SRS服务器ID
     */
    public void setSrsServerId(String srsServerId) {
        super.setValue(SRS_SERVER_ID, srsServerId);
    }

    /**
     * 获取 SRS服务器ID
     *
     * @return SRS服务器ID
     */
    public String getSrsServerId() {
        return super.getValueAsString(SRS_SERVER_ID);
    }

    /**
     * 设置 SRS服务ID
     *
     * @param srsServiceId SRS服务ID
     */
    public void setSrsServiceId(String srsServiceId) {
        super.setValue(SRS_SERVICE_ID, srsServiceId);
    }

    /**
     * 获取 SRS服务ID
     *
     * @return SRS服务ID
     */
    public String getSrsServiceId() {
        return super.getValueAsString(SRS_SERVICE_ID);
    }

    /**
     * 设置 SRS动作类型
     *
     * @param srsAction SRS动作类型
     */
    public void setSrsAction(String srsAction) {
        super.setValue(SRS_ACTION, srsAction);
    }

    /**
     * 获取 SRS动作类型
     *
     * @return SRS动作类型
     */
    public String getSrsAction() {
        return super.getValueAsString(SRS_ACTION);
    }

    /**
     * 设置 SRS客户端ID
     *
     * @param srsClientId SRS客户端ID
     */
    public void setSrsClientId(String srsClientId) {
        super.setValue(SRS_CLIENT_ID, srsClientId);
    }

    /**
     * 获取 SRS客户端ID
     *
     * @return SRS客户端ID
     */
    public String getSrsClientId() {
        return super.getValueAsString(SRS_CLIENT_ID);
    }

    /**
     * 设置 SRS客户端IP地址
     *
     * @param srsIp SRS客户端IP地址
     */
    public void setSrsIp(String srsIp) {
        super.setValue(SRS_IP, srsIp);
    }

    /**
     * 获取 SRS客户端IP地址
     *
     * @return SRS客户端IP地址
     */
    public String getSrsIp() {
        return super.getValueAsString(SRS_IP);
    }

    /**
     * 设置 SRS虚拟主机名
     *
     * @param srsVhost SRS虚拟主机名
     */
    public void setSrsVhost(String srsVhost) {
        super.setValue(SRS_VHOST, srsVhost);
    }

    /**
     * 获取 SRS虚拟主机名
     *
     * @return SRS虚拟主机名
     */
    public String getSrsVhost() {
        return super.getValueAsString(SRS_VHOST);
    }

    /**
     * 设置 SRS应用名
     *
     * @param srsApp SRS应用名
     */
    public void setSrsApp(String srsApp) {
        super.setValue(SRS_APP, srsApp);
    }

    /**
     * 获取 SRS应用名
     *
     * @return SRS应用名
     */
    public String getSrsApp() {
        return super.getValueAsString(SRS_APP);
    }

    /**
     * 设置 SRS tcUrl地址
     *
     * @param srsTcurl SRS tcUrl地址
     */
    public void setSrsTcurl(String srsTcurl) {
        super.setValue(SRS_TCURL, srsTcurl);
    }

    /**
     * 获取 SRS tcUrl地址
     *
     * @return SRS tcUrl地址
     */
    public String getSrsTcurl() {
        return super.getValueAsString(SRS_TCURL);
    }

    /**
     * 设置 SRS流名
     *
     * @param srsStream SRS流名
     */
    public void setSrsStream(String srsStream) {
        super.setValue(SRS_STREAM, srsStream);
    }

    /**
     * 获取 SRS流名
     *
     * @return SRS流名
     */
    public String getSrsStream() {
        return super.getValueAsString(SRS_STREAM);
    }

    /**
     * 设置 SRS流参数
     *
     * @param srsParam SRS流参数
     */
    public void setSrsParam(String srsParam) {
        super.setValue(SRS_PARAM, srsParam);
    }

    /**
     * 获取 SRS流参数
     *
     * @return SRS流参数
     */
    public String getSrsParam() {
        return super.getValueAsString(SRS_PARAM);
    }

    /**
     * 设置 SRS流地址
     *
     * @param srsStreamUrl SRS流地址
     */
    public void setSrsStreamUrl(String srsStreamUrl) {
        super.setValue(SRS_STREAM_URL, srsStreamUrl);
    }

    /**
     * 获取 SRS流地址
     *
     * @return SRS流地址
     */
    public String getSrsStreamUrl() {
        return super.getValueAsString(SRS_STREAM_URL);
    }

    /**
     * 设置 SRS流ID
     *
     * @param srsStreamId SRS流ID
     */
    public void setSrsStreamId(String srsStreamId) {
        super.setValue(SRS_STREAM_ID, srsStreamId);
    }

    /**
     * 获取 SRS流ID
     *
     * @return SRS流ID
     */
    public String getSrsStreamId() {
        return super.getValueAsString(SRS_STREAM_ID);
    }

    /**
     * 设置 是否为腾讯云CSS流标识（0：否，1：是）
     *
     * @param isTencentcloudCss 是否为腾讯云CSS流标识（0：否，1：是）
     */
    public void setIsTencentcloudCss(Integer isTencentcloudCss) {
        super.setValue(IS_TENCENTCLOUD_CSS, isTencentcloudCss);
    }

    /**
     * 获取 是否为腾讯云CSS流标识（0：否，1：是）
     *
     * @return 是否为腾讯云CSS流标识（0：否，1：是）
     */
    public Integer getIsTencentcloudCss() {
        return super.getValueAsInteger(IS_TENCENTCLOUD_CSS);
    }

    /**
     * 设置 标识ID
     *
     * @param flagId 标识ID
     */
    public void setFlagId(String flagId) {
        super.setValue(FLAG_ID, flagId);
    }

    /**
     * 获取 标识ID
     *
     * @return 标识ID
     */
    public String getFlagId() {
        return super.getValueAsString(FLAG_ID);
    }

    /**
     * 设置 更新时间
     *
     * @param updatedTime 更新时间
     */
    public void setUpdatedTime(Date updatedTime) {
        super.setValue(UPDATED_TIME, updatedTime);
    }

    /**
     * 获取 更新时间
     *
     * @return 更新时间
     */
    public Date getUpdatedTime() {
        return super.getValueAsDate(UPDATED_TIME);
    }

    /**
     * 设置 删除时间（软删除标识）
     *
     * @param deletedTime 删除时间（软删除标识）
     */
    public void setDeletedTime(Date deletedTime) {
        super.setValue(DELETED_TIME, deletedTime);
    }

    /**
     * 获取 删除时间（软删除标识）
     *
     * @return 删除时间（软删除标识）
     */
    public Date getDeletedTime() {
        return super.getValueAsDate(DELETED_TIME);
    }

    /**
     * 设置
     *
     * @param id
     */
    public void setId(String id) {
        super.setValue(ID, id);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getId() {
        return super.getValueAsString(ID);
    }
}
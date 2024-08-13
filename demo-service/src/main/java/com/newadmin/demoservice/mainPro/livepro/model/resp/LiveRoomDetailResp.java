package com.newadmin.demoservice.mainPro.livepro.model.resp;

import com.newadmin.democore.kduck.service.ValueMap;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * 直播间详情信息
 *
 * @author couei
 * @since 2024/08/05 21:29
 */

@Schema(description = "直播间详情信息")
public class LiveRoomDetailResp extends ValueMap {

    /**
     * id
     */
    public static final String ID = "id";
    /**房间名称*/
    public static final String NAME = "name";

    /**
     * 房间描述
     */
    public static final String DESCTION = "desction";

    /**
     * 房间状态（例如：0-关闭，1-开启）
     */
    public static final String STATUS = "status";

    /**
     * 是否显示（例如：0-不显示，1-显示）
     */
    public static final String IS_SHOW = "isShow";

    /**
     * 备注
     */
    public static final String REMARK = "remark";

    /**
     * 房间关键字
     */
    public static final String KEY_ROOM = "keyRoom";

    /**
     * 房间类型
     */
    public static final String TYPE = "type";

    /**
     * 拉流是否需要认证（例如：0-不需要，1-需要）
     */
    public static final String PULL_IS_SHOULD_AUTH = "pullIsShouldAuth";

    /**
     * 使用的CDN（内容分发网络）类型
     */
    public static final String CDN = "cdn";

    /**
     * 权重（用于排序）
     */
    public static final String WEIGHT = "weight";

    /**
     * 封面图片URL
     */
    public static final String COVER_IMG = "coverImg";

    /**
     * 背景图片URL
     */
    public static final String BG_IMG = "bgImg";

    /**
     * RTMP拉流URL
     */
    public static final String RTMP_URL = "rtmpUrl";

    /**
     * FLV拉流URL
     */
    public static final String FLV_URL = "flvUrl";

    /**
     * HLS拉流URL
     */
    public static final String HLS_URL = "hlsUrl";

    /**
     * WebRTC拉流URL
     */
    public static final String WEBRTC_URL = "webrtcUrl";

    /**
     * RTMP推流URL
     */
    public static final String PUSH_RTMP_URL = "pushRtmpUrl";

    /**
     * OBS推流服务器地址
     */
    public static final String PUSH_OBS_SERVER = "pushObsServer";

    /**
     * OBS推流密钥
     */
    public static final String PUSH_OBS_STREAM_KEY = "pushObsStreamKey";

    /**
     * WebRTC推流URL
     */
    public static final String PUSH_WEBRTC_URL = "pushWebrtcUrl";

    /**
     * SRT推流URL
     */
    public static final String PUSH_SRT_URL = "pushSrtUrl";

    /**
     * CDN RTMP拉流URL
     */
    public static final String CDN_RTMP_URL = "cdnRtmpUrl";

    /**
     * CDN FLV拉流URL
     */
    public static final String CDN_FLV_URL = "cdnFlvUrl";

    /**
     * CDN HLS拉流URL
     */
    public static final String CDN_HLS_URL = "cdnHlsUrl";

    /**
     * CDN WebRTC拉流URL
     */
    public static final String CDN_WEBRTC_URL = "cdnWebrtcUrl";

    /**
     * CDN RTMP推流URL
     */
    public static final String CDN_PUSH_RTMP_URL = "cdnPushRtmpUrl";

    /**
     * CDN OBS推流服务器地址
     */
    public static final String CDN_PUSH_OBS_SERVER = "cdnPushObsServer";

    /**
     * CDN OBS推流密钥
     */
    public static final String CDN_PUSH_OBS_STREAM_KEY = "cdnPushObsStreamKey";

    /**
     * CDN WebRTC推流URL
     */
    public static final String CDN_PUSH_WEBRTC_URL = "cdnPushWebrtcUrl";

    /**
     * CDN SRT推流URL
     */
    public static final String CDN_PUSH_SRT_URL = "cdnPushSrtUrl";

    /**
     * 转发到Bilibili的URL
     */
    public static final String FORWARD_BILIBILI_URL = "forwardBilibiliUrl";

    /**
     * 转发到虎牙的URL
     */
    public static final String FORWARD_HUYA_URL = "forwardHuyaUrl";

    /**
     * 转发到斗鱼的URL
     */
    public static final String FORWARD_DOUYU_URL = "forwardDouyuUrl";

    /**
     * 转发到抖音的URL
     */
    public static final String FORWARD_DOUYIN_URL = "forwardDouyinUrl";

    /**
     * 转发到快手的URL
     */
    public static final String FORWARD_KUAISHOU_URL = "forwardKuaishouUrl";

    /**
     * 转发到小红书的URL
     */
    public static final String FORWARD_XIAOHONGSHU_URL = "forwardXiaohongshuUrl";

    /**
     * 是否为虚拟房间（例如：0-否，1-是，2-未知）
     */
    public static final String IS_FAKE = "isFake";

    /**
     * 创建时间
     */
    public static final String CREATED_TIME = "createdTime";

    /**
     * 更新时间
     */
    public static final String UPDATED_TIME = "updatedTime";

    /**
     * 删除时间
     */
    public static final String DELETED_TIME = "deletedTime";

    /**
     * token
     */
    public static final String SECRET_KEY = "secretKey";

    public LiveRoomDetailResp() {
    }

    public LiveRoomDetailResp(Map<String, Object> map) {
        super(map);
    }



    /**
     * 设置 房间描述
     *
     * @param desc 房间描述
     */
    public void setDesc(String desc) {
        super.setValue(DESCTION, desc);
    }

    /**
     * 获取 房间描述
     *
     * @return 房间描述
     */
    public String getDesc() {
        return super.getValueAsString(DESCTION);
    }

    /**
     * 设置 房间状态（例如：0-关闭，1-开启）
     *
     * @param status 房间状态（例如：0-关闭，1-开启）
     */
    public void setStatus(Integer status) {
        super.setValue(STATUS, status);
    }

    /**
     * 获取 房间状态（例如：0-关闭，1-开启）
     *
     * @return 房间状态（例如：0-关闭，1-开启）
     */
    public Integer getStatus() {
        return super.getValueAsInteger(STATUS);
    }

    /**
     * 设置 是否显示（例如：0-不显示，1-显示）
     *
     * @param isShow 是否显示（例如：0-不显示，1-显示）
     */
    public void setIsShow(Integer isShow) {
        super.setValue(IS_SHOW, isShow);
    }

    /**
     * 获取 是否显示（例如：0-不显示，1-显示）
     *
     * @return 是否显示（例如：0-不显示，1-显示）
     */
    public Integer getIsShow() {
        return super.getValueAsInteger(IS_SHOW);
    }

    /**
     * 设置 备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        super.setValue(REMARK, remark);
    }

    /**
     * 获取 备注
     *
     * @return 备注
     */
    public String getRemark() {
        return super.getValueAsString(REMARK);
    }

    /**
     * 设置 房间关键字
     *
     * @param key 房间关键字
     */
    public void setKey(String key) {
        super.setValue(KEY_ROOM, key);
    }

    /**
     * 获取 房间关键字
     *
     * @return 房间关键字
     */
    public String getKey() {
        return super.getValueAsString(KEY_ROOM);
    }

    /**
     * 设置 房间类型
     *
     * @param type 房间类型
     */
    public void setType(Integer type) {
        super.setValue(TYPE, type);
    }

    /**
     * 获取 房间类型
     *
     * @return 房间类型
     */
    public Integer getType() {
        return super.getValueAsInteger(TYPE);
    }

    /**
     * 设置 拉流是否需要认证（例如：0-不需要，1-需要）
     *
     * @param pullIsShouldAuth 拉流是否需要认证（例如：0-不需要，1-需要）
     */
    public void setPullIsShouldAuth(Integer pullIsShouldAuth) {
        super.setValue(PULL_IS_SHOULD_AUTH, pullIsShouldAuth);
    }

    /**
     * 获取 拉流是否需要认证（例如：0-不需要，1-需要）
     *
     * @return 拉流是否需要认证（例如：0-不需要，1-需要）
     */
    public Integer getPullIsShouldAuth() {
        return super.getValueAsInteger(PULL_IS_SHOULD_AUTH);
    }

    /**
     * 设置 使用的CDN（内容分发网络）类型
     *
     * @param cdn 使用的CDN（内容分发网络）类型
     */
    public void setCdn(Integer cdn) {
        super.setValue(CDN, cdn);
    }

    /**
     * 获取 使用的CDN（内容分发网络）类型
     *
     * @return 使用的CDN（内容分发网络）类型
     */
    public Integer getCdn() {
        return super.getValueAsInteger(CDN);
    }

    /**
     * 设置 权重（用于排序）
     *
     * @param weight 权重（用于排序）
     */
    public void setWeight(Integer weight) {
        super.setValue(WEIGHT, weight);
    }

    /**
     * 获取 权重（用于排序）
     *
     * @return 权重（用于排序）
     */
    public Integer getWeight() {
        return super.getValueAsInteger(WEIGHT);
    }

    /**
     * 设置 封面图片URL
     *
     * @param coverImg 封面图片URL
     */
    public void setCoverImg(String coverImg) {
        super.setValue(COVER_IMG, coverImg);
    }

    /**
     * 获取 封面图片URL
     *
     * @return 封面图片URL
     */
    public String getCoverImg() {
        return super.getValueAsString(COVER_IMG);
    }

    /**
     * 设置 背景图片URL
     *
     * @param bgImg 背景图片URL
     */
    public void setBgImg(String bgImg) {
        super.setValue(BG_IMG, bgImg);
    }

    /**
     * 获取 背景图片URL
     *
     * @return 背景图片URL
     */
    public String getBgImg() {
        return super.getValueAsString(BG_IMG);
    }

    /**
     * 设置 RTMP拉流URL
     *
     * @param rtmpUrl RTMP拉流URL
     */
    public void setRtmpUrl(String rtmpUrl) {
        super.setValue(RTMP_URL, rtmpUrl);
    }

    /**
     * 获取 RTMP拉流URL
     *
     * @return RTMP拉流URL
     */
    public String getRtmpUrl() {
        return super.getValueAsString(RTMP_URL);
    }

    /**
     * 设置 FLV拉流URL
     *
     * @param flvUrl FLV拉流URL
     */
    public void setFlvUrl(String flvUrl) {
        super.setValue(FLV_URL, flvUrl);
    }

    /**
     * 获取 FLV拉流URL
     *
     * @return FLV拉流URL
     */
    public String getFlvUrl() {
        return super.getValueAsString(FLV_URL);
    }

    /**
     * 设置 HLS拉流URL
     *
     * @param hlsUrl HLS拉流URL
     */
    public void setHlsUrl(String hlsUrl) {
        super.setValue(HLS_URL, hlsUrl);
    }

    /**
     * 获取 HLS拉流URL
     *
     * @return HLS拉流URL
     */
    public String getHlsUrl() {
        return super.getValueAsString(HLS_URL);
    }

    /**
     * 设置 WebRTC拉流URL
     *
     * @param webrtcUrl WebRTC拉流URL
     */
    public void setWebrtcUrl(String webrtcUrl) {
        super.setValue(WEBRTC_URL, webrtcUrl);
    }

    /**
     * 获取 WebRTC拉流URL
     *
     * @return WebRTC拉流URL
     */
    public String getWebrtcUrl() {
        return super.getValueAsString(WEBRTC_URL);
    }

    /**
     * 设置 RTMP推流URL
     *
     * @param pushRtmpUrl RTMP推流URL
     */
    public void setPushRtmpUrl(String pushRtmpUrl) {
        super.setValue(PUSH_RTMP_URL, pushRtmpUrl);
    }

    /**
     * 获取 RTMP推流URL
     *
     * @return RTMP推流URL
     */
    public String getPushRtmpUrl() {
        return super.getValueAsString(PUSH_RTMP_URL);
    }

    /**
     * 设置 OBS推流服务器地址
     *
     * @param pushObsServer OBS推流服务器地址
     */
    public void setPushObsServer(String pushObsServer) {
        super.setValue(PUSH_OBS_SERVER, pushObsServer);
    }

    /**
     * 获取 OBS推流服务器地址
     *
     * @return OBS推流服务器地址
     */
    public String getPushObsServer() {
        return super.getValueAsString(PUSH_OBS_SERVER);
    }

    /**
     * 设置 OBS推流密钥
     *
     * @param pushObsStreamKey OBS推流密钥
     */
    public void setPushObsStreamKey(String pushObsStreamKey) {
        super.setValue(PUSH_OBS_STREAM_KEY, pushObsStreamKey);
    }

    /**
     * 获取 OBS推流密钥
     *
     * @return OBS推流密钥
     */
    public String getPushObsStreamKey() {
        return super.getValueAsString(PUSH_OBS_STREAM_KEY);
    }

    /**
     * 设置 WebRTC推流URL
     *
     * @param pushWebrtcUrl WebRTC推流URL
     */
    public void setPushWebrtcUrl(String pushWebrtcUrl) {
        super.setValue(PUSH_WEBRTC_URL, pushWebrtcUrl);
    }

    /**
     * 获取 WebRTC推流URL
     *
     * @return WebRTC推流URL
     */
    public String getPushWebrtcUrl() {
        return super.getValueAsString(PUSH_WEBRTC_URL);
    }

    /**
     * 设置 SRT推流URL
     *
     * @param pushSrtUrl SRT推流URL
     */
    public void setPushSrtUrl(String pushSrtUrl) {
        super.setValue(PUSH_SRT_URL, pushSrtUrl);
    }

    /**
     * 获取 SRT推流URL
     *
     * @return SRT推流URL
     */
    public String getPushSrtUrl() {
        return super.getValueAsString(PUSH_SRT_URL);
    }

    /**
     * 设置 CDN RTMP拉流URL
     *
     * @param cdnRtmpUrl CDN RTMP拉流URL
     */
    public void setCdnRtmpUrl(String cdnRtmpUrl) {
        super.setValue(CDN_RTMP_URL, cdnRtmpUrl);
    }

    /**
     * 获取 CDN RTMP拉流URL
     *
     * @return CDN RTMP拉流URL
     */
    public String getCdnRtmpUrl() {
        return super.getValueAsString(CDN_RTMP_URL);
    }

    /**
     * 设置 CDN FLV拉流URL
     *
     * @param cdnFlvUrl CDN FLV拉流URL
     */
    public void setCdnFlvUrl(String cdnFlvUrl) {
        super.setValue(CDN_FLV_URL, cdnFlvUrl);
    }

    /**
     * 获取 CDN FLV拉流URL
     *
     * @return CDN FLV拉流URL
     */
    public String getCdnFlvUrl() {
        return super.getValueAsString(CDN_FLV_URL);
    }

    /**
     * 设置 CDN HLS拉流URL
     *
     * @param cdnHlsUrl CDN HLS拉流URL
     */
    public void setCdnHlsUrl(String cdnHlsUrl) {
        super.setValue(CDN_HLS_URL, cdnHlsUrl);
    }

    /**
     * 获取 CDN HLS拉流URL
     *
     * @return CDN HLS拉流URL
     */
    public String getCdnHlsUrl() {
        return super.getValueAsString(CDN_HLS_URL);
    }

    /**
     * 设置 CDN WebRTC拉流URL
     *
     * @param cdnWebrtcUrl CDN WebRTC拉流URL
     */
    public void setCdnWebrtcUrl(String cdnWebrtcUrl) {
        super.setValue(CDN_WEBRTC_URL, cdnWebrtcUrl);
    }

    /**
     * 获取 CDN WebRTC拉流URL
     *
     * @return CDN WebRTC拉流URL
     */
    public String getCdnWebrtcUrl() {
        return super.getValueAsString(CDN_WEBRTC_URL);
    }

    /**
     * 设置 CDN RTMP推流URL
     *
     * @param cdnPushRtmpUrl CDN RTMP推流URL
     */
    public void setCdnPushRtmpUrl(String cdnPushRtmpUrl) {
        super.setValue(CDN_PUSH_RTMP_URL, cdnPushRtmpUrl);
    }

    /**
     * 获取 CDN RTMP推流URL
     *
     * @return CDN RTMP推流URL
     */
    public String getCdnPushRtmpUrl() {
        return super.getValueAsString(CDN_PUSH_RTMP_URL);
    }

    /**
     * 设置 CDN OBS推流服务器地址
     *
     * @param cdnPushObsServer CDN OBS推流服务器地址
     */
    public void setCdnPushObsServer(String cdnPushObsServer) {
        super.setValue(CDN_PUSH_OBS_SERVER, cdnPushObsServer);
    }

    /**
     * 获取 CDN OBS推流服务器地址
     *
     * @return CDN OBS推流服务器地址
     */
    public String getCdnPushObsServer() {
        return super.getValueAsString(CDN_PUSH_OBS_SERVER);
    }

    /**
     * 设置 CDN OBS推流密钥
     *
     * @param cdnPushObsStreamKey CDN OBS推流密钥
     */
    public void setCdnPushObsStreamKey(String cdnPushObsStreamKey) {
        super.setValue(CDN_PUSH_OBS_STREAM_KEY, cdnPushObsStreamKey);
    }

    /**
     * 获取 CDN OBS推流密钥
     *
     * @return CDN OBS推流密钥
     */
    public String getCdnPushObsStreamKey() {
        return super.getValueAsString(CDN_PUSH_OBS_STREAM_KEY);
    }

    /**
     * 设置 CDN WebRTC推流URL
     *
     * @param cdnPushWebrtcUrl CDN WebRTC推流URL
     */
    public void setCdnPushWebrtcUrl(String cdnPushWebrtcUrl) {
        super.setValue(CDN_PUSH_WEBRTC_URL, cdnPushWebrtcUrl);
    }

    /**
     * 获取 CDN WebRTC推流URL
     *
     * @return CDN WebRTC推流URL
     */
    public String getCdnPushWebrtcUrl() {
        return super.getValueAsString(CDN_PUSH_WEBRTC_URL);
    }

    /**
     * 设置 CDN SRT推流URL
     *
     * @param cdnPushSrtUrl CDN SRT推流URL
     */
    public void setCdnPushSrtUrl(String cdnPushSrtUrl) {
        super.setValue(CDN_PUSH_SRT_URL, cdnPushSrtUrl);
    }

    /**
     * 获取 CDN SRT推流URL
     *
     * @return CDN SRT推流URL
     */
    public String getCdnPushSrtUrl() {
        return super.getValueAsString(CDN_PUSH_SRT_URL);
    }

    /**
     * 设置 转发到Bilibili的URL
     *
     * @param forwardBilibiliUrl 转发到Bilibili的URL
     */
    public void setForwardBilibiliUrl(String forwardBilibiliUrl) {
        super.setValue(FORWARD_BILIBILI_URL, forwardBilibiliUrl);
    }

    /**
     * 获取 转发到Bilibili的URL
     *
     * @return 转发到Bilibili的URL
     */
    public String getForwardBilibiliUrl() {
        return super.getValueAsString(FORWARD_BILIBILI_URL);
    }

    /**
     * 设置 转发到虎牙的URL
     *
     * @param forwardHuyaUrl 转发到虎牙的URL
     */
    public void setForwardHuyaUrl(String forwardHuyaUrl) {
        super.setValue(FORWARD_HUYA_URL, forwardHuyaUrl);
    }

    /**
     * 获取 转发到虎牙的URL
     *
     * @return 转发到虎牙的URL
     */
    public String getForwardHuyaUrl() {
        return super.getValueAsString(FORWARD_HUYA_URL);
    }

    /**
     * 设置 转发到斗鱼的URL
     *
     * @param forwardDouyuUrl 转发到斗鱼的URL
     */
    public void setForwardDouyuUrl(String forwardDouyuUrl) {
        super.setValue(FORWARD_DOUYU_URL, forwardDouyuUrl);
    }

    /**
     * 获取 转发到斗鱼的URL
     *
     * @return 转发到斗鱼的URL
     */
    public String getForwardDouyuUrl() {
        return super.getValueAsString(FORWARD_DOUYU_URL);
    }

    /**
     * 设置 转发到抖音的URL
     *
     * @param forwardDouyinUrl 转发到抖音的URL
     */
    public void setForwardDouyinUrl(String forwardDouyinUrl) {
        super.setValue(FORWARD_DOUYIN_URL, forwardDouyinUrl);
    }

    /**
     * 获取 转发到抖音的URL
     *
     * @return 转发到抖音的URL
     */
    public String getForwardDouyinUrl() {
        return super.getValueAsString(FORWARD_DOUYIN_URL);
    }

    /**
     * 设置 转发到快手的URL
     *
     * @param forwardKuaishouUrl 转发到快手的URL
     */
    public void setForwardKuaishouUrl(String forwardKuaishouUrl) {
        super.setValue(FORWARD_KUAISHOU_URL, forwardKuaishouUrl);
    }

    /**
     * 获取 转发到快手的URL
     *
     * @return 转发到快手的URL
     */
    public String getForwardKuaishouUrl() {
        return super.getValueAsString(FORWARD_KUAISHOU_URL);
    }

    /**
     * 设置 转发到小红书的URL
     *
     * @param forwardXiaohongshuUrl 转发到小红书的URL
     */
    public void setForwardXiaohongshuUrl(String forwardXiaohongshuUrl) {
        super.setValue(FORWARD_XIAOHONGSHU_URL, forwardXiaohongshuUrl);
    }

    /**
     * 获取 转发到小红书的URL
     *
     * @return 转发到小红书的URL
     */
    public String getForwardXiaohongshuUrl() {
        return super.getValueAsString(FORWARD_XIAOHONGSHU_URL);
    }

    /**
     * 设置 是否为虚拟房间（例如：0-否，1-是，2-未知）
     *
     * @param isFake 是否为虚拟房间（例如：0-否，1-是，2-未知）
     */
    public void setIsFake(Integer isFake) {
        super.setValue(IS_FAKE, isFake);
    }

    /**
     * 获取 是否为虚拟房间（例如：0-否，1-是，2-未知）
     *
     * @return 是否为虚拟房间（例如：0-否，1-是，2-未知）
     */
    public Integer getIsFake() {
        return super.getValueAsInteger(IS_FAKE);
    }

    /**
     * 设置 创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(LocalDateTime createdTime) {
        super.setValue(CREATED_TIME, createdTime);
    }

    /**
     * 获取 创建时间
     *
     * @return 创建时间
     */
    public Date getCreatedTime() {
        return super.getValueAsDate(CREATED_TIME);
    }

    /**
     * 设置 更新时间
     *
     * @param updatedTime 更新时间
     */
    public void setUpdatedTime(LocalDateTime updatedTime) {
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
     * 设置 删除时间
     *
     * @param deletedTime 删除时间
     */
    public void setDeletedTime(LocalDateTime deletedTime) {
        super.setValue(DELETED_TIME, deletedTime);
    }

    /**
     * 获取 删除时间
     *
     * @return 删除时间
     */
    public Date getDeletedTime() {
        return super.getValueAsDate(DELETED_TIME);
    }

    /**
     * 设置 token
     *
     * @param secretKey token
     */
    public void setSecretKey(String secretKey) {
        super.setValue(SECRET_KEY, secretKey);
    }

    /**
     * 获取 token
     *
     * @return token
     */
    public String getSecretKey() {
        return super.getValueAsString(SECRET_KEY);
    }

    /**
     * 设置 房间名称
     *
     * @param name 房间名称
     */
    public void setName(String name) {
        super.setValue(NAME, name);
    }

    /**
     * 获取 房间名称
     *
     * @return 房间名称
     */
    public String getName() {
        return super.getValueAsString(NAME);
    }

    /**
     * 设置 id
     *
     * @param id id
     */
    public void setId(String id) {
        super.setValue(ID, id);
    }

    /**
     * 获取 id
     *
     * @return id
     */
    public String getId() {
        return super.getValueAsString(ID);
    }
}
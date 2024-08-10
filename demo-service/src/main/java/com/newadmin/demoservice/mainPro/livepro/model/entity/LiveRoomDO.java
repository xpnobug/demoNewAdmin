package com.newadmin.demoservice.mainPro.livepro.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serial;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 直播间实体
 *
 * @author couei
 * @since 2024/08/05 21:29
 */
@Data
@TableName("live_room")
public class LiveRoomDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 房间名称
     */
    private String name;

    /**
     * 房间描述
     */
    private String desc;

    /**
     * 房间状态（例如：0-关闭，1-开启）
     */
    private Integer status;

    /**
     * 是否显示（例如：0-不显示，1-显示）
     */
    private Integer isShow;

    /**
     * 备注
     */
    private String remark;

    /**
     * 房间关键字
     */
    private String key;

    /**
     * 房间类型
     */
    private Integer type;

    /**
     * 拉流是否需要认证（例如：0-不需要，1-需要）
     */
    private Integer pullIsShouldAuth;

    /**
     * 使用的CDN（内容分发网络）类型
     */
    private Integer cdn;

    /**
     * 权重（用于排序）
     */
    private Integer weight;

    /**
     * 封面图片URL
     */
    private String coverImg;

    /**
     * 背景图片URL
     */
    private String bgImg;

    /**
     * RTMP拉流URL
     */
    private String rtmpUrl;

    /**
     * FLV拉流URL
     */
    private String flvUrl;

    /**
     * HLS拉流URL
     */
    private String hlsUrl;

    /**
     * WebRTC拉流URL
     */
    private String webrtcUrl;

    /**
     * RTMP推流URL
     */
    private String pushRtmpUrl;

    /**
     * OBS推流服务器地址
     */
    private String pushObsServer;

    /**
     * OBS推流密钥
     */
    private String pushObsStreamKey;

    /**
     * WebRTC推流URL
     */
    private String pushWebrtcUrl;

    /**
     * SRT推流URL
     */
    private String pushSrtUrl;

    /**
     * CDN RTMP拉流URL
     */
    private String cdnRtmpUrl;

    /**
     * CDN FLV拉流URL
     */
    private String cdnFlvUrl;

    /**
     * CDN HLS拉流URL
     */
    private String cdnHlsUrl;

    /**
     * CDN WebRTC拉流URL
     */
    private String cdnWebrtcUrl;

    /**
     * CDN RTMP推流URL
     */
    private String cdnPushRtmpUrl;

    /**
     * CDN OBS推流服务器地址
     */
    private String cdnPushObsServer;

    /**
     * CDN OBS推流密钥
     */
    private String cdnPushObsStreamKey;

    /**
     * CDN WebRTC推流URL
     */
    private String cdnPushWebrtcUrl;

    /**
     * CDN SRT推流URL
     */
    private String cdnPushSrtUrl;

    /**
     * 转发到Bilibili的URL
     */
    private String forwardBilibiliUrl;

    /**
     * 转发到虎牙的URL
     */
    private String forwardHuyaUrl;

    /**
     * 转发到斗鱼的URL
     */
    private String forwardDouyuUrl;

    /**
     * 转发到抖音的URL
     */
    private String forwardDouyinUrl;

    /**
     * 转发到快手的URL
     */
    private String forwardKuaishouUrl;

    /**
     * 转发到小红书的URL
     */
    private String forwardXiaohongshuUrl;

    /**
     * 是否为虚拟房间（例如：0-否，1-是，2-未知）
     */
    private Integer isFake;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;

    /**
     * 删除时间
     */
    private LocalDateTime deletedTime;
}
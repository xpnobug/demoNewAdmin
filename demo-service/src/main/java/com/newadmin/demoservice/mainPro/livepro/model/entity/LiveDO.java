package com.newadmin.demoservice.mainPro.livepro.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serial;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 直播记录实体
 *
 * @author couei
 * @since 2024/08/05 22:02
 */
@Data
@TableName("live")
public class LiveDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Socket连接的唯一标识
     */
    private String socketId;

    /**
     * 直播间ID
     */
    private Integer liveRoomId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 视频轨道标识（0：关闭，1：开启）
     */
    private Integer trackVideo;

    /**
     * 音频轨道标识（0：关闭，1：开启）
     */
    private Integer trackAudio;

    /**
     * SRS服务器ID
     */
    private String srsServerId;

    /**
     * SRS服务ID
     */
    private String srsServiceId;

    /**
     * SRS动作类型
     */
    private String srsAction;

    /**
     * SRS客户端ID
     */
    private String srsClientId;

    /**
     * SRS客户端IP地址
     */
    private String srsIp;

    /**
     * SRS虚拟主机名
     */
    private String srsVhost;

    /**
     * SRS应用名
     */
    private String srsApp;

    /**
     * SRS tcUrl地址
     */
    private String srsTcurl;

    /**
     * SRS流名
     */
    private String srsStream;

    /**
     * SRS流参数
     */
    private String srsParam;

    /**
     * SRS流地址
     */
    private String srsStreamUrl;

    /**
     * SRS流ID
     */
    private String srsStreamId;

    /**
     * 是否为腾讯云CSS流标识（0：否，1：是）
     */
    private Integer isTencentcloudCss;

    /**
     * 标识ID
     */
    private String flagId;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;

    /**
     * 删除时间（软删除标识）
     */
    private LocalDateTime deletedTime;
}
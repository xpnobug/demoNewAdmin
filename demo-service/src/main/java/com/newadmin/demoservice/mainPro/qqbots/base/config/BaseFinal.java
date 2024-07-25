package com.newadmin.demoservice.mainPro.qqbots.base.config;

import java.math.BigInteger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置类，用于存储QQ机器人基础配置
 * <p>
 * 统一地址：https://api.sgroup.qq.com 获取带分片 WSS 接入点API 发送消息API
 * </p>
 */
@Data
@Component
@ConfigurationProperties(prefix = "qqbot.base")
public class BaseFinal {

    /**
     * 统一地址 https://api.sgroup.qq.com
     */
    private String baseUrl;

    /**
     * 获取带分片 WSS 接入点API
     */
    private String gatewayBotUrl;

    /**
     * 是否启用
     */
    private boolean enabled;

    /**
     * token
     */
    public String botToken;
    ;

    /**
     * 发送消息的URL模板
     */
    public static String SEND_MSG;
    public static String Bot_Token;
    public static String GATEWAY_BOT_URL;
    ;

    /**
     * 连接超时时间（毫秒）
     */
    public static final int CONNECTION_TIMEOUT = 20000;

    /**
     * 请求超时时间（毫秒）
     */
    public static final int REQUEST_TIMEOUT = 60000;

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        BaseFinal.SEND_MSG = baseUrl + "/channels/%s/messages";
    }

    /**
     * 设置token
     *
     * @param token
     */
    @Value("${qqbot.base.bot-token}")
    public void setBotToken(String token) {
        this.botToken = token;
        BaseFinal.Bot_Token = token;
    }

    /**
     * 设置gatewayBotUrl
     */
    @Value("${qqbot.base.gateway-bot-url}")
    public void setGetWayBotUrl(String gatewayBotUrl) {
        this.gatewayBotUrl = gatewayBotUrl;
        BaseFinal.GATEWAY_BOT_URL = gatewayBotUrl;
    }

    /**
     * 初始化方法，在Bean创建后调用
     */
    @PostConstruct
    public void init() {
        if (isEnabled()) {
            startBot();
        } else {
            stopBot();
        }
    }

    /**
     * 销毁方法，在Bean销毁前调用
     */
    @PreDestroy
    public void destroy() {
        stopBot();
    }

    /**
     * 启动机器人
     */
    public void startBot() {
        // 实现启动机器人的逻辑
        System.out.println("QQ机器人已启动");
        // 这里可以放置启动机器人所需的实际逻辑
    }

    /**
     * 关闭机器人
     */
    public void stopBot() {
        // 实现关闭机器人的逻辑
        System.out.println("QQ机器人已关闭");
        // 这里可以放置关闭机器人所需的实际逻辑
    }

    /**
     * 检查配置是否启用
     *
     * @return 配置是否启用
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * 根据频道ID和消息ID获取发送消息的URL
     *
     * @param channelId 频道ID
     * @return 发送消息的URL
     */
    public static String getSendMsgUrl(String channelId) {
        return String.format(SEND_MSG, channelId);
    }

    /**
     * 按照频道ID进行哈希计算，同一个频道的信息会固定从同一个链接推送。 具体哈希计算规则如下：shard_id = (guild_id >> 22) % num_shards
     *
     * @param guildId   频道ID
     * @param numShards 分片数量
     * @return 分片ID
     */
    public static int calculateShardId(BigInteger guildId, int numShards) {
        return guildId.shiftRight(22).mod(BigInteger.valueOf(numShards)).intValue();
    }
}

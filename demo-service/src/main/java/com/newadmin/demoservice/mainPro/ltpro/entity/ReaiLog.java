package com.newadmin.demoservice.mainPro.ltpro.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author couei
 * @since 2024-05-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("reai_log")
public class ReaiLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private String id;

    /**
     * 姓名
     */
    private String username;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;

    /**
     * ip地址
     */
    private String ip;

    /**
     * IP地址归属地
     */
    private String address;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 描述
     */
    private String description;

    /**
     * 终端系统
     */
    private String os;

    /**
     * 状态
     */
    private String status;

    /**
     * 错误日志
     */
    private String errorMsg;

    /**
     * 耗时
     */
    private String timeTaken;

    /**
     * 用户id
     */
    private String userId;

}

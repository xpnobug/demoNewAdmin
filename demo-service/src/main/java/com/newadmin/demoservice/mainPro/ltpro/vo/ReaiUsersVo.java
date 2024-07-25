package com.newadmin.demoservice.mainPro.ltpro.vo;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReaiUsersVo {

    /**
     * 用户ID，主键，唯一标识用户
     */

    private String userId;

    /**
     * 用户名，用户的用户名
     */
    private String username;

    /**
     * 真实姓名，用户真实姓名
     */
    private String fullName;

    /**
     * 注册时间，用户注册时间
     */
    private Date registrationTime;

    /**
     * 最后登录时间，用户最后一次登录时间
     */
    private Date lastLoginTime;

    /**
     * 用户类型，用户类型（例如：普通用户、管理员）
     */
    private String userType;

    /**
     * 状态，用户状态（例如：激活、禁用）
     */
    private String status;

    /**
     * 用户头像
     */
    private String userCover;

    /**
     * 用户等级
     */
    private Integer level;

    /**
     * 经验值
     */
    private String exp;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 是否关注
     */
    private Boolean isFollow;

    /**
     * 粉丝数
     */
    private Integer fansCount;

    /**
     * 关注数
     */
    private Integer followCount;

}

package com.newadmin.demoservice.mainPro.mai.entity;

import lombok.Data;

/**
 * 套图基本信息实体类
 *
 * @author 86136
 */
@Data
public class MaiInfo {

    private String avatar;
    private String card;
    private String cateName;
    private int cid;
    private String sid;
    private int collectionCount;
    private boolean collectionStatus;
    private int commentCount;
    private String createTime;
    private int followCount;
    private boolean followStatus;
    private int followerCount;
    private boolean followerStatus;
    private String label;
    private int likeCount;
    private boolean likeStatus;
    private String memo;
    private String name;
}

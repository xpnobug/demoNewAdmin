package com.newadmin.demoservice.mainPro.nas.dto;

import lombok.Data;

@Data
public class UserPwd {

    private String id;
    private String newPassword;
    private String oldPassword;

    // 省略getter和setter方法
}

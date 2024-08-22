package com.newadmin.demoservice.mainPro.filepro.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 存储类型枚举
 */
@Getter
@RequiredArgsConstructor
public enum StorageTypeEnum {

    /**
     * 兼容S3协议存储
     */
    S3(1, "兼容S3协议存储"),

    /**
     * 本地存储
     */
    LOCAL(2, "本地存储"),
    ;

    private final Integer value;
    private final String description;
}

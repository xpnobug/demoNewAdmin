package com.newadmin.democonfig.oss;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 86136
 */

@Getter
@AllArgsConstructor
public enum OssType {
    /**
     * Minio 对象存储1
     */
    MINIO("minio", 1),

    /**
     * 华为 OBS
     */
    OBS("obs", 2),

    /**
     * 腾讯 COS
     */
    COS("tencent", 3),

    /**
     * 阿里巴巴 SSO
     */
    ALIBABA("alibaba", 4),
    ;

    /**
     * 名称
     */
    final String name;
    /**
     * 类型
     */
    final int type;

}

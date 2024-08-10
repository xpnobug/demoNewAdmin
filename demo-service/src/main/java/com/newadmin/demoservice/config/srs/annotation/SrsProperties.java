package com.newadmin.demoservice.config.srs.annotation;

import com.newadmin.democore.constant.PropertiesConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(PropertiesConstants.SRS)
public class SrsProperties {

    // 推流地址
    private String pushUrl;

    // 拉流地址
    private String pullUrl;

    // 推流密钥
    private String pushKey;

    // 拉流密钥
    private String pullKey;

    // 统一路径
    private String pushPath;

    private Integer post;
}

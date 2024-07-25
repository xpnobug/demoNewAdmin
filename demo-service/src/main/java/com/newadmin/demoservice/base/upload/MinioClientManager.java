package com.newadmin.demoservice.base.upload;

import com.newadmin.demoservice.config.ConfigurationManager;
import io.minio.MinioClient;

public class MinioClientManager {

    private String endpoint;
    private String accessKey;
    private String secretKey;

    public MinioClient createMinioClient() {
        return MinioClient.builder()
            .endpoint(endpoint)
            .credentials(accessKey, secretKey)
            .build();
    }

    // 添加方法来动态更新配置
    public void updateConfig(String endpoint, String accessKey, String secretKey) {
        this.endpoint = endpoint;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    // 可以从配置文件、数据库或其他配置源加载初始配置
    public void loadConfigFromExternalSource() {
        // 示例：从配置文件加载
        this.endpoint = ConfigurationManager.getInstance().getProperty("minio.endpoint");
        this.accessKey = ConfigurationManager.getInstance().getProperty("minio.accessKey");
        this.secretKey = ConfigurationManager.getInstance().getProperty("minio.secretKey");
    }
}

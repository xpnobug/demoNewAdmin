package com.newadmin.demoservice.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 配置文件通常只需要加载一次。 单例模式可以确保配置管理器在应用程序的整个生命周期中只存在一个实例。
 * <p>
 * 集中管理：配置集中管理，便于维护和更新。 环境独立：不同环境使用不同配置，提升部署灵活性。 动态调整：通过修改配置文件动态调整应用程序行为，无需重新编译或重启应用程序。
 * 安全性：敏感信息（如API密钥、密码等）集中管理，并可通过加密保护。 易于扩展：新增配置项方便，易于扩展和维护。
 */
public class ConfigurationManager {

    private static ConfigurationManager instance;
    private Map<String, Object> yml;
    private Properties properties;

    private ConfigurationManager() {
//        Yaml yaml = new Yaml();
        properties = new Properties();
        // 加载配置文件
        try {
//            yml = yaml.load(getClass().getClassLoader().getResourceAsStream(
//                "config/application.yml"));
            properties.load(
                getClass().getClassLoader().getResourceAsStream("config/application.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public Object getYml(String key) {
        // 递归获取嵌套属性
        String[] keys = key.split("\\.");
        Map<String, Object> current = yml;
        for (int i = 0; i < keys.length - 1; i++) {
            current = (Map<String, Object>) current.get(keys[i]);
        }
        return current.get(keys[keys.length - 1]);
    }

    public String getStringProperty(String key) {
        Object value = getYml(key);
        return value != null ? value.toString() : null;
    }

    public boolean getBooleanProperty(String key) {
        Object value = getYml(key);
        return value != null && Boolean.parseBoolean(value.toString());
    }

    public int getIntProperty(String key) {
        Object value = getYml(key);
        return value != null ? Integer.parseInt(value.toString()) : 0;
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    public void setYml(String key, Object value) {
        // 递归设置嵌套属性
        String[] keys = key.split("\\.");
        Map<String, Object> current = yml;
        for (int i = 0; i < keys.length - 1; i++) {
            if (current.get(keys[i]) == null) {
                current.put(keys[i], new HashMap<>());
            }
            current = (Map<String, Object>) current.get(keys[i]);
        }
        current.put(keys[keys.length - 1], value);
    }

    public static void main(String[] args) {
        ConfigurationManager configManager = ConfigurationManager.getInstance();

        System.out.println(configManager.getProperty("minio.endpoint"));
//        System.out.println(configManager.getYml("server.port"));

        // 读取数据库配置
//        String dbUrl = configManager.getStringProperty("spring.datasource.url");
//        String dbUsername = configManager.getStringProperty("spring.datasource.username");
//        System.out.println("Database URL: " + dbUrl);
//        System.out.println("Database Username: " + dbUsername);
    }
}

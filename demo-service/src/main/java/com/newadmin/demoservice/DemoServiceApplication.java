package com.newadmin.demoservice;

import cn.dev33.satoken.SaManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * 初始化版本111
 *
 * @author 86136
 */

@ComponentScan(basePackages = {"com.newadmin.*"})
@SpringBootApplication
@RequiredArgsConstructor
public class DemoServiceApplication {

    @Autowired
    private Environment env; // 注入 Environment 对象

    public static void main(String[] args) {
        SpringApplication.run(DemoServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println("启动成功，Sa-Token 配置如下：" + SaManager.getConfig());
            // 输出更多的启动信息
            System.out.println(
                "WebSocket 服务器正在运行，地址: ws://localhost:" + env.getProperty("server.port")
                    + "/websocket");
        };
    }
}

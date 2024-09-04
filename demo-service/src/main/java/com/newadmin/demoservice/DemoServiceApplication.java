package com.newadmin.demoservice;

import cn.dev33.satoken.annotation.SaIgnore;
import com.newadmin.democonfig.project.ProjectProperties;
import com.newadmin.democore.kduck.web.json.JsonObject;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.x.file.storage.spring.EnableFileStorage;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 初始化版本111
 *
 * @author 86136
 */
@Slf4j
@EnableFileStorage
@ComponentScan(basePackages = {"com.newadmin.*"})
@MapperScan("com.newadmin.demogenerator.mapper")
@SpringBootApplication
@RequiredArgsConstructor
public class DemoServiceApplication {

    private final ProjectProperties projectProperties;

    public static void main(String[] args) {
        SpringApplication.run(DemoServiceApplication.class, args);
    }

    @Hidden
    @SaIgnore
    @GetMapping("/")
    public JsonObject index() {
        return new JsonObject(
            "%s service started successfully.".formatted(projectProperties.getName()));
    }

}


package com.newadmin.democonfig.project;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * 项目自动配置
 *
 * @author couei
 * @since 1.0.0
 */
@AutoConfiguration
@ComponentScan("cn.hutool.extra.spring")
@Import(cn.hutool.extra.spring.SpringUtil.class)
@EnableConfigurationProperties(ProjectProperties.class)
public class ProjectAutoConfiguration {

}

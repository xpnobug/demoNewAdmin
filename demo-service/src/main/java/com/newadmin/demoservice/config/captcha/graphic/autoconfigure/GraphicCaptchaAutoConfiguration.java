
package com.newadmin.demoservice.config.captcha.graphic.autoconfigure;

import com.newadmin.demoservice.config.captcha.graphic.core.GraphicCaptchaService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties({GraphicCaptchaProperties.class})
@ConditionalOnProperty(
    prefix = "new-admin.captcha.graphic",
    name = {"enabled"},
    matchIfMissing = true
)
public class GraphicCaptchaAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(
        GraphicCaptchaAutoConfiguration.class);

    public GraphicCaptchaAutoConfiguration() {
    }

    @Bean
    @ConditionalOnMissingBean
    public GraphicCaptchaService graphicCaptchaService(GraphicCaptchaProperties properties) {
        return new GraphicCaptchaService(properties);
    }

    @PostConstruct
    public void postConstruct() {
        log.debug(
            "[New Admin] - Auto Configuration 'Captcha-Graphic' completed initialization.");
    }
}

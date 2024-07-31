
package com.newadmin.demoservice.config.messaging.mail.autoconfigure;

import com.newadmin.democore.util.GeneralPropertySourceFactory;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.PropertySource;

/**
 * 邮件自动配置
 *
 * @author couei
 * @since 1.0.0
 */
@AutoConfiguration
@PropertySource(value = "classpath:default-messaging-mail.yml", factory = GeneralPropertySourceFactory.class)
public class MailAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MailAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[New Admin] - Auto Configuration 'Mail' completed initialization.");
    }
}

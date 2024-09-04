package com.newadmin.demolog.log.httptrace.autoconfigure;

import com.newadmin.democonfig.constant.PropertiesConstants;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * 是否启用日志记录注解
 *
 * @author couei
 * @author Charles7c
 * @since 1.1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ConditionalOnProperty(prefix = PropertiesConstants.LOG, name = PropertiesConstants.ENABLED, matchIfMissing = true)
public @interface ConditionalOnEnabledLog {

}
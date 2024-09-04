package com.newadmin.demoservice.config.prop;

import com.newadmin.democonfig.security.crypto.encryptor.RsaEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class baseConfigBean {

    /**
     * BCrypt 加/解密处理器
     */
//    @Bean
//    public BCryptEncryptor bCryptEncryptor(PasswordEncoder passwordEncoder) {
//        return new BCryptEncryptor(passwordEncoder);
//    }
    @Bean
    public RsaEncryptor rsaEncryptor() {
        return new RsaEncryptor();
    }

}

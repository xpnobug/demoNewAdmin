package com.newadmin.demoservice.config.prop;

import com.newadmin.demoservice.config.security.crypto.encryptor.RsaEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

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

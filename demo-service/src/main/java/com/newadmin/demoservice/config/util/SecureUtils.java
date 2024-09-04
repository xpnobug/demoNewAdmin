
package com.newadmin.demoservice.config.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.extra.spring.SpringUtil;
import com.newadmin.democonfig.util.validate.ValidationUtils;
import com.newadmin.demoservice.config.properties.RsaProperties;
import com.newadmin.democonfig.security.crypto.autoconfigure.CryptoProperties;
import com.newadmin.democonfig.security.crypto.encryptor.AesEncryptor;
import com.newadmin.democonfig.security.crypto.encryptor.IEncryptor;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 加密/解密工具类
 *
 * @author couei
 * @since 2022/12/21 21:41
 */
public class SecureUtils {

    private SecureUtils() {
    }

    /**
     * 私钥解密
     *
     * @param data 要解密的内容（Base64 加密过）
     * @return 解密后的内容
     */
    public static String decryptByRsaPrivateKey(String data) {
        String privateKey = RsaProperties.PRIVATE_KEY;
        ValidationUtils.throwIfBlank(privateKey, "请配置 RSA 私钥");
        return decryptByRsaPrivateKey(data, privateKey);
    }

    /**
     * 私钥解密
     *
     * @param data       要解密的内容（Base64 加密过）
     * @param privateKey 私钥
     * @return 解密后的内容
     */
    public static String decryptByRsaPrivateKey(String data, String privateKey) {
        return new String(
            SecureUtil.rsa(privateKey, null).decrypt(Base64.decode(data), KeyType.PrivateKey));
    }

    /**
     * 对普通加密字段列表进行AES加密，优化starter加密模块后优化这个方法
     *
     * @param values 待加密内容
     * @return 加密后内容
     */
    public static List<String> encryptFieldByAes(List<String> values) {
        IEncryptor encryptor = new AesEncryptor();
        CryptoProperties properties = SpringUtil.getBean(CryptoProperties.class);
        return values.stream().map(value -> {
            try {
                return encryptor.encrypt(value, properties.getPassword(),
                    properties.getPublicKey());
            } catch (Exception e) {
                throw new RuntimeException("字段加密异常");
            }
        }).collect(Collectors.toList());
    }
}

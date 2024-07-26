package com.newadmin.demoservice.config.security.crypto.core;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.newadmin.demoservice.config.core.exception.BusinessException;
import com.newadmin.demoservice.config.security.crypto.annotation.FieldEncrypt;
import com.newadmin.demoservice.config.security.crypto.encryptor.IEncryptor;
import com.newadmin.demoservice.config.security.crypto.enums.Algorithm;
import com.newadmin.demoservice.mainPro.ltpro.common.constant.StringConstants;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.plugin.Interceptor;

/**
 * 字段解密拦截器
 *
 * @author couei
 * @since 1.4.0
 */
public abstract class AbstractMyBatisInterceptor implements Interceptor {

    private static final Map<String, Map<String, FieldEncrypt>> ENCRYPT_PARAM_CACHE = new ConcurrentHashMap<>();

    /**
     * 获取加密参数
     *
     * @param mappedStatementId 映射语句 ID
     * @return 加密参数
     */
    public Map<String, FieldEncrypt> getEncryptParams(String mappedStatementId) {
        return getEncryptParams(mappedStatementId, null);
    }

    /**
     * 获取加密参数
     *
     * @param mappedStatementId 映射语句 ID
     * @param parameterIndex    参数索引
     * @return 加密参数
     */
    public Map<String, FieldEncrypt> getEncryptParams(String mappedStatementId,
        Integer parameterIndex) {
        return ENCRYPT_PARAM_CACHE
            .computeIfAbsent(mappedStatementId,
                key -> getEncryptParamsNoCached(mappedStatementId, parameterIndex));
    }

    /**
     * 获取参数名称
     *
     * @param parameter 参数
     * @return 参数名称
     */
    public String getParameterName(Parameter parameter) {
        Param param = parameter.getAnnotation(Param.class);
        return null != param ? param.value() : parameter.getName();
    }

    /**
     * 获取所有字符串类型、需要加/解密的、有值字段
     *
     * @param obj 对象
     * @return 字段列表
     */
    public List<Field> getEncryptFields(Object obj) {
        if (null == obj) {
            return Collections.emptyList();
        }
        return Arrays.stream(ReflectUtil.getFields(obj.getClass()))
            .filter(field -> String.class.equals(field.getType()))
            .filter(field -> null != field.getAnnotation(FieldEncrypt.class))
            .filter(field -> null != ReflectUtil.getFieldValue(obj, field))
            .toList();
    }

    /**
     * 获取字段加/解密处理器
     *
     * @param fieldEncrypt 字段加密注解
     * @return 加/解密处理器
     */
    public IEncryptor getEncryptor(FieldEncrypt fieldEncrypt) {
        Class<? extends IEncryptor> encryptorClass = fieldEncrypt.encryptor();
        // 使用预定义加/解密处理器
        if (encryptorClass == IEncryptor.class) {
            Algorithm algorithm = fieldEncrypt.value();
            return ReflectUtil.newInstance(algorithm.getEncryptor());
        }
        // 使用自定义加/解密处理器
        return SpringUtil.getBean(encryptorClass);
    }

    /**
     * 获取参数列表（无缓存）
     *
     * @param mappedStatementId 映射语句 ID
     * @param parameterIndex    参数数量
     * @return 参数列表
     */
    private Map<String, FieldEncrypt> getEncryptParamsNoCached(String mappedStatementId,
        Integer parameterIndex) {
        try {
            String className = CharSequenceUtil.subBefore(mappedStatementId, StringConstants.DOT,
                true);
            String wrapperMethodName = CharSequenceUtil.subAfter(mappedStatementId,
                StringConstants.DOT, true);
            String methodName = Stream.of("_mpCount", "_COUNT")
                .filter(wrapperMethodName::endsWith)
                .findFirst()
                .map(suffix -> wrapperMethodName.substring(0,
                    wrapperMethodName.length() - suffix.length()))
                .orElse(wrapperMethodName);
            // 获取真实方法
            Optional<Method> methodOptional = Arrays.stream(
                ReflectUtil.getMethods(Class.forName(className), m -> {
                    if (Objects.nonNull(parameterIndex)) {
                        return Objects.equals(m.getName(), methodName)
                            && m.getParameterCount() == parameterIndex;
                    }
                    return Objects.equals(m.getName(), methodName);
                })).findFirst();
            if (methodOptional.isEmpty()) {
                return Collections.emptyMap();
            }
            // 获取方法中的加密参数
            Map<String, FieldEncrypt> map = MapUtil.newHashMap();
            Parameter[] parameterArr = methodOptional.get().getParameters();
            for (int i = 0; i < parameterArr.length; i++) {
                Parameter parameter = parameterArr[i];
                String parameterName = this.getParameterName(parameter);
                FieldEncrypt fieldEncrypt = parameter.getAnnotation(FieldEncrypt.class);
                if (null != fieldEncrypt) {
                    map.put(parameterName, fieldEncrypt);
                    if (String.class.equals(parameter.getType())) {
                        map.put("param" + (i + 1), fieldEncrypt);
                    }
                } else if (parameterName.startsWith(Constants.ENTITY)) {
                    map.put(parameterName, null);
                } else if (parameterName.startsWith(Constants.WRAPPER)) {
                    map.put(parameterName, null);
                }
            }
            return map;
        } catch (ClassNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
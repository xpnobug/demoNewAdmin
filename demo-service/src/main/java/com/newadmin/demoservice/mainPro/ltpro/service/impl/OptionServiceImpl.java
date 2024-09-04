

package com.newadmin.demoservice.mainPro.ltpro.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.newadmin.democonfig.redisCommon.util.RedisUtils;
import com.newadmin.democonfig.constant.StringConstants;
import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.democonfig.util.validate.CheckUtils;
import com.newadmin.democonfig.util.validate.ValidationUtils;
import com.newadmin.demogenerator.constant.CacheConstants;
import com.newadmin.demoservice.mainPro.ltpro.common.enums.PasswordPolicyEnum;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiOption;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.query.OptionQuery;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.req.OptionReq;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.req.OptionResetValueReq;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.resp.OptionResp;
import com.newadmin.demoservice.mainPro.ltpro.service.OptionService;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 参数业务实现
 */
@Service
@RequiredArgsConstructor
public class OptionServiceImpl extends DefaultService implements OptionService {

    public static final String TABLE_NAME = "reai_option";

    @Override
    public List<OptionResp> list(OptionQuery query) {
        ValueMap params = new ValueMap();
        params.put("category", query.getCategory());
        params.put("code", query.getCode());
        // 创建SelectBuilder对象
        SelectBuilder selectBuilder = new SelectBuilder();
        // 设置查询的表
        selectBuilder.from("", super.getEntityDef(TABLE_NAME));

        // 根据OptionQuery中的条件设置WHERE语句
        if (query.getCategory() != null) {
            selectBuilder.where().and("category", ConditionType.EQUALS, ReaiOption.CATEGORY);
        }
        if (query.getCode() != null && !query.getCode().isEmpty()) {
            selectBuilder.where().and("code", ConditionType.IN, ReaiOption.CODE);
        }
        // 其他条件可以根据OptionQuery的属性添加
        // 执行查询并获取结果
        List<ReaiOption> options = super.listForBean(selectBuilder.build(), ReaiOption::new);

        // 将查询结果转换为OptionResp对象列表
        return BeanUtil.copyToList(options, OptionResp.class);
    }


    @Override
    public Map<String, String> getByCategory(String category) {
        ValueMap params = new ValueMap();
        params.put("category", category);
        // 构建SelectBuilder对象
        SelectBuilder selectBuilder = new SelectBuilder(params);
        // 设置查询的表和条件
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
            .and("category", ConditionType.EQUALS, ReaiOption.CATEGORY); // 根据分类进行过滤

        // 执行查询，获取OptionDO对象的列表
        List<ReaiOption> options = super.listForBean(selectBuilder.build(), ReaiOption::new);

        // 处理查询结果，将其转换为Map
        return options.stream()
            .collect(Collectors.toMap(
                ReaiOption::getCode, // key: code
                o -> StrUtil.emptyIfNull(
                    ObjectUtil.defaultIfNull(o.getValue(), o.getDefaultValue())),
                // value: value or defaultValue
                (oldVal, newVal) -> oldVal // 合并策略: 保留旧值
            ));
    }

    @Override
    public void update(List<OptionReq> options) {
        Map<String, String> passwordPolicyOptionMap = options.stream()
            .filter(option -> StrUtil.startWith(option
                .getCode(), PasswordPolicyEnum.CATEGORY + StringConstants.UNDERLINE))
            .collect(Collectors.toMap(OptionReq::getCode, OptionReq::getValue,
                (oldVal, newVal) -> oldVal));
        // 校验密码策略参数取值范围
        for (Map.Entry<String, String> passwordPolicyOptionEntry : passwordPolicyOptionMap.entrySet()) {
            String code = passwordPolicyOptionEntry.getKey();
            String value = passwordPolicyOptionEntry.getValue();
            ValidationUtils.throwIf(!NumberUtil.isNumber(value), "参数 [%s] 的值必须为数字", code);
            PasswordPolicyEnum passwordPolicy = PasswordPolicyEnum.valueOf(code);
            passwordPolicy.validateRange(Integer.parseInt(value), passwordPolicyOptionMap);
        }
        RedisUtils.deleteByPattern(CacheConstants.OPTION_KEY_PREFIX + StringConstants.ASTERISK);

        // 批量更新
        options.forEach(o -> {
            // 复制属性
            ReaiOption option = BeanUtil.copyProperties(o, ReaiOption.class);
            // 更新操作
            super.update(TABLE_NAME, option);
        });

    }

    @Override
    public void resetValue(OptionResetValueReq req) {
        // 删除Redis缓存中所有匹配指定模式的键
        RedisUtils.deleteByPattern(CacheConstants.OPTION_KEY_PREFIX + StringConstants.ASTERISK);

        // 获取请求中的分类和代码列表
        String category = req.getCategory();
        List<String> codeList = req.getCode();

        // 检查分类和代码列表是否同时为空
        ValidationUtils.throwIf(StrUtil.isBlank(category) && CollUtil.isEmpty(codeList),
            "键列表不能为空");

        // 构建更新链，用于将匹配条件的OptionDO的值重置为空
        // 设置要更新的字段和值
        ReaiOption option = new ReaiOption();
        option.setValue(null);

        ValueMap map = new ValueMap();
        map.put(ReaiOption.CODE, req.getCode());
        map.put(ReaiOption.CATEGORY, category);
        // 构建SQL查询语句
        SelectBuilder selectBuilder = new SelectBuilder();
        selectBuilder.from("", super.getEntityDef(TABLE_NAME)).where()
            .and("category", ConditionType.EQUALS, ReaiOption.CATEGORY)
            .and("code", ConditionType.IN, ReaiOption.CATEGORY);
        // 根据请求中的分类或代码列表设置更新条件
//        if (StrUtil.isNotBlank(category)) {
//            chainWrapper.eq(option::getCategory, category); // 如果分类不为空，按分类更新
//
//            super.update(TABLE_NAME,option);
//
//        } else {
//            chainWrapper.in(OptionDO::getCode, req.getCode()); // 如果分类为空，按代码列表更新
//        }
    }

    @Override
    public int getValueByCode2Int(String code) {
        return this.getValueByCode(code, Integer::parseInt);
    }

    @Override
    public <T> T getValueByCode(String code, Function<String, T> mapper) {
        // 从Redis缓存中获取值
        String value = RedisUtils.get(CacheConstants.OPTION_KEY_PREFIX + code);
        if (StrUtil.isNotBlank(value)) {
            // 如果缓存中存在值，则使用mapper转换并返回
            return mapper.apply(value);
        }

        // 如果缓存中不存在值，通过code查询OptionDO实体类
        ValueMap params = new ValueMap();
        params.put(ReaiOption.CODE, code); // 添加code参数
        params.put(ReaiOption.VALUE, value); // 添加value参数（初始为空）

        // 构建SQL查询语句
        SelectBuilder selectBuilder = new SelectBuilder(params);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME)).where()
            .and("code", ConditionType.EQUALS, ReaiOption.CODE) // 添加code条件
            .and("value", ConditionType.EQUALS, ReaiOption.VALUE) // 添加value条件
            .orderBy();

        // 执行查询，获取OptionDO对象
        ReaiOption option = super.getForBean(selectBuilder.build(), ReaiOption::new);

        // 检查查询结果是否为空
        CheckUtils.throwIfNull(option, "参数 [{}] 不存在", code);

        // 获取查询结果的值或默认值
        value = StrUtil.nullToDefault(option.getValue(), option.getDefaultValue());

        // 检查值是否为空
        CheckUtils.throwIfBlank(value, "参数 [{}] 数据错误", code);

        // 将值存入Redis缓存
        RedisUtils.set(CacheConstants.OPTION_KEY_PREFIX + code, value);

        // 使用mapper转换并返回
        return mapper.apply(value);
    }

}
package com.newadmin.demoservice.mainPro.ltpro.auth.service.impl;

import cn.hutool.json.JSONUtil;
import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.democonfig.util.validate.CheckUtils;
import com.newadmin.demoservice.mainPro.ltpro.auth.entity.UserSocialDO;
import com.newadmin.demoservice.mainPro.ltpro.auth.service.UserSocialService;
import com.newadmin.demoservice.mainPro.ltpro.common.enums.SocialSourceEnum;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import me.zhyd.oauth.model.AuthUser;
import org.springframework.stereotype.Service;

/**
 * 第三方业务平台实现
 * @since 2023/10/11 22:10
 */
@Service
@RequiredArgsConstructor
public class UserSocialServiceImpl extends DefaultService implements UserSocialService {

    // 表名常量
    public static final String TABLE_NAME = "reai_user_social";

    // 根据来源和openId查询用户社交信息
    public UserSocialDO selectBySourceAndOpenId(String source, String openId) {
        // 创建参数映射
        ValueMap params = new ValueMap();
        params.put("source", source); // 添加source参数
        params.put("openId", openId); // 添加openId参数

        // SQL查询语句，包含联表查询
        String sql = "SELECT t1.*"
            + "FROM reai_user_social AS t1 "
            + "LEFT JOIN reai_users AS t2 ON t2.user_id = t1.user_id "
            + "WHERE t1.source ='" + source + "'"
            + "AND t1.open_id ='" + openId + "'";

        // 构建SelectBuilder对象
        SelectBuilder selectBuilder = new SelectBuilder(sql, params);
        // 执行查询，返回结果
        return super.getForBean(selectBuilder.build(), UserSocialDO::new);
    }

    @Override
    public UserSocialDO getBySourceAndOpenId(String source, String openId) {
        // 调用私有方法执行查询
        return this.selectBySourceAndOpenId(source, openId);
    }

    @Override
    public void saveOrUpdate(UserSocialDO userSocial) {
        // 如果创建时间为空，则新增记录，否则更新记录
        if (null == userSocial.getCreateTime()) {
            super.add(TABLE_NAME, userSocial, false);
        } else {
            super.update(TABLE_NAME, userSocial);
        }
    }

    @Override
    public List<UserSocialDO> listByUserId(String userId) {
        // 创建参数映射
        ValueMap params = new ValueMap();
        params.put("userId", userId); // 添加userId参数

        // 查询语句 todo
        String sql = "SELECT * FROM reai_user_social WHERE user_id =" + userId;

        // 构建SelectBuilder对象
        SelectBuilder selectBuilder = new SelectBuilder(sql, params);
        // 执行查询，返回结果列表
        return super.listForBean(selectBuilder.build(), UserSocialDO::new);
    }

    @Override
    public void bind(AuthUser authUser, String userId) {
        // 获取来源和唯一标识符
        String source = authUser.getSource();
        String openId = authUser.getUuid();

        // 获取用户绑定的社交账号列表
        List<UserSocialDO> userSocialList = this.listByUserId(userId);

        // 获取已绑定的社交平台来源集合
        Set<String> boundSocialSet = userSocialList.stream().map(UserSocialDO::getSource)
            .collect(Collectors.toSet());

        // 获取社交平台描述
        String description = SocialSourceEnum.valueOf(source).getDescription();

        // 检查是否已绑定当前平台
        CheckUtils.throwIf(boundSocialSet.contains(source), "您已经绑定过了 [{}] 平台，请先解绑",
            description);

        // 根据来源和openId查询绑定信息
        UserSocialDO userSocial = this.getBySourceAndOpenId(source, openId);
        // 检查账号是否被其他用户绑定
        CheckUtils.throwIfNotNull(userSocial, "[{}] 平台账号 [{}] 已被其他用户绑定", description,
            authUser.getUsername());

        // 创建新绑定对象
        userSocial = new UserSocialDO();
        userSocial.setUserId(userId); // 设置用户ID
        userSocial.setSource(source); // 设置来源
        userSocial.setOpenId(openId); // 设置唯一标识符
        userSocial.setMetaJson(JSONUtil.toJsonStr(authUser)); // 设置元数据
        userSocial.setLastLoginTime(new Date()); // 设置最后登录时间

        // 保存绑定信息
        super.add(TABLE_NAME, userSocial);
    }

    @Override
    public void deleteBySourceAndUserId(String source, String userId) {
        // 根据用户ID删除绑定信息
        super.delete(TABLE_NAME, new String[]{userId});
    }
}


package com.newadmin.demoservice.mainPro.ltpro.auth.service;

import com.newadmin.demoservice.mainPro.ltpro.auth.entity.UserSocialDO;
import java.util.List;
import me.zhyd.oauth.model.AuthUser;

/**
 * 用户社会化关联业务接口
 *
 * @author couei
 * @since 2023/10/11 22:10
 */
public interface UserSocialService {

    /**
     * 根据来源和开放 ID 查询
     *
     * @param source 来源
     * @param openId 开放 ID
     * @return 用户社会化关联信息
     */
    UserSocialDO getBySourceAndOpenId(String source, String openId);

    /**
     * 保存
     *
     * @param userSocial 用户社会化关联信息
     */
    void saveOrUpdate(UserSocialDO userSocial);

    /**
     * 根据用户 ID 查询
     *
     * @param userId 用户 ID
     * @return 用户社会化关联信息
     */
    List<UserSocialDO> listByUserId(String userId);

    /**
     * 绑定
     *
     * @param authUser 三方账号信息
     * @param userId   用户 ID
     */
    void bind(AuthUser authUser, String userId);

    /**
     * 根据来源和用户 ID 删除
     *
     * @param source 来源
     * @param userId 用户 ID
     */
    void deleteBySourceAndUserId(String source, String userId);
}
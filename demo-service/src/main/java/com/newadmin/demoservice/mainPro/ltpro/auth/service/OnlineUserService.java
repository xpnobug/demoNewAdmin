package com.newadmin.demoservice.mainPro.ltpro.auth.service;

import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.demoservice.mainPro.ltpro.auth.model.dto.LoginUser;
import com.newadmin.demoservice.mainPro.ltpro.auth.model.query.OnlineUserQuery;
import com.newadmin.demoservice.mainPro.ltpro.auth.model.resp.OnlineUserResp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 在线用户业务接口
 *
 * @author couei
 * @since 2023/3/25 22:48
 */
public interface OnlineUserService {

    /**
     * 分页查询列表
     *
     * @param query 查询条件
     * @param page  分页查询条件
     * @return 分页列表信息
     */
    List<OnlineUserResp> page(OnlineUserQuery query, Page page);

    /**
     * 查询列表
     *
     * @param query 查询条件
     * @return 列表信息
     */
    List<LoginUser> list(OnlineUserQuery query);

    /**
     * 查询 Token 最后活跃时间
     *
     * @param token Token
     * @return 最后活跃时间
     */
    LocalDateTime getLastActiveTime(String token);

    /**
     * 根据角色 ID 清除
     *
     * @param roleId 角色 ID
     */
    void cleanByRoleId(Long roleId);

    /**
     * 根据用户 ID 清除登录
     *
     * @param userId 用户 ID
     */
    void cleanByUserId(Long userId);
}

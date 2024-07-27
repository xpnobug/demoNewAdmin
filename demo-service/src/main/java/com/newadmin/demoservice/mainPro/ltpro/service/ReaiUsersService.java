package com.newadmin.demoservice.mainPro.ltpro.service;

import com.newadmin.democommon.utils.Page;
import com.newadmin.demoservice.mainPro.ltpro.auth.model.resp.UserInfoResp;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiUsers;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author couei
 * @since 2024-05-12
 */
public interface ReaiUsersService {

    ReaiUsers usersById(String id);

    List<ReaiUsers> usersListById(List<String> ids);

    List<ReaiUsers> usersList(Page page);

    /**
     * 注册新用户
     *
     * @param user
     * @return
     */
    ReaiUsers register(ReaiUsers user);

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    ReaiUsers updateUserInfo(ReaiUsers user);

    /**
     * 获取用户信息
     *
     * @param page
     * @return
     */
    List<ReaiUsers> pageList(Page page);

    List<ReaiUsers> getFollowListById(String id);

    ReaiUsers getUserById(Serializable id);

    UserInfoResp getUser(Serializable id);
    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return 用户信息
     */
    ReaiUsers getByUsername(String username);

    /**
     * 根据手机号查询
     *
     * @param phone 手机号
     * @return 用户信息
     */
    ReaiUsers getByPhone(String phone);

    /**
     * 根据邮箱查询
     *
     * @param email 邮箱
     * @return 用户信息
     */
    ReaiUsers getByEmail(String email);
}

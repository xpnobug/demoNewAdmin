
package com.newadmin.demoservice.mainPro.ltpro.auth.service;

import java.util.Set;

/**
 * 权限业务接口
 *
 * @author couei
 * @since 2023/3/2 20:40
 */
public interface PermissionService {

    /**
     * 根据用户 ID 查询权限码
     *
     * @param userId 用户 ID
     * @return 权限码集合
     */
    Set<String> listPermissionByUserId(String userId);

    /**
     * 根据用户 ID 查询角色编码
     *
     * @param userId 用户 ID
     * @return 角色编码集合
     */
    Set<String> listRoleCodeByUserId(String userId);
}

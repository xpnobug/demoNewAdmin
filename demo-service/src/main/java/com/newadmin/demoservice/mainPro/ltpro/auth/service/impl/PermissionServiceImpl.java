
package com.newadmin.demoservice.mainPro.ltpro.auth.service.impl;

import com.newadmin.demoservice.mainPro.ltpro.auth.service.PermissionService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 权限业务实现
 *
 * @author couei
 * @since 2023/3/2 20:40
 */
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    @Override
    public Set<String> listPermissionByUserId(String userId) {
        return Set.of();
    }

    @Override
    public Set<String> listRoleCodeByUserId(String userId) {
        return Set.of();
    }
}

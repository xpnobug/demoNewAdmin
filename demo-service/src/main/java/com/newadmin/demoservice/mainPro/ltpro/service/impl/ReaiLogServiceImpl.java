package com.newadmin.demoservice.mainPro.ltpro.service.impl;

import com.newadmin.democommon.id.IdGenerator;
import com.newadmin.demoservice.mainPro.ltpro.auth.model.dto.LoginUser;
import com.newadmin.demoservice.mainPro.ltpro.helper.LoginHelper;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiLogService;
import java.io.Serializable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author couei
 * @since 2024-05-23
 */
@Service
@RequiredArgsConstructor
public class ReaiLogServiceImpl implements
    ReaiLogService {

    private final IdGenerator idGenerator;

    @Override
    public void saveLog() {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser != null) {
            Serializable serializable = idGenerator.nextId();
            loginUser.setId(serializable.toString());
//            baseMapper.insert(loginUser);
        }
    }
}

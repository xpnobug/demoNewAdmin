package com.newadmin.demoservice.mainPro.nas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newadmin.demoservice.mainPro.nas.entity.User;
import jakarta.servlet.http.HttpServletRequest;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author couei
 * @since 2024-03-31
 */
public interface UserService extends IService<User> {

    User login(User user);

    User getCurrentUserByToken(HttpServletRequest httpRequest);
}

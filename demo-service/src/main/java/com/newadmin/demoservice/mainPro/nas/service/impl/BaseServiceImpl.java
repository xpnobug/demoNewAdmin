package com.newadmin.demoservice.mainPro.nas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.newadmin.demoservice.mainPro.nas.entity.User;
import com.newadmin.demoservice.mainPro.nas.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl {

    @Autowired
    private UserService userService;

    /**
     * 获取当前登录用户
     *
     * @param httpRequest
     * @return
     */
    public User getCurrentUserByToken(HttpServletRequest httpRequest) {
        //获取浏览器标头数据
        //获取token
        String token = httpRequest.getHeader("Token");
        if (token == null || token.isEmpty()) {
            return null;
        }
        //根据token获取用户信息
        User userInfo = userService.getOne(new QueryWrapper<User>().eq("token", token));
        //返回用户信息
        return userInfo;
    }
}

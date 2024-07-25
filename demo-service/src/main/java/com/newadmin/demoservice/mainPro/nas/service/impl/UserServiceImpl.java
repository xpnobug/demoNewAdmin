package com.newadmin.demoservice.mainPro.nas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newadmin.demoservice.mainPro.nas.dao.UserMapper;
import com.newadmin.demoservice.mainPro.nas.entity.User;
import com.newadmin.demoservice.mainPro.nas.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author couei
 * @since 2024-03-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public User login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        queryWrapper.eq("password", user.getPassword());
        User usInfo = this.getOne(queryWrapper);
        if (user.getUsername().equals(usInfo.getUsername()) && user.getPassword()
            .equals(usInfo.getPassword())) {
            return usInfo;
        }
        return null;
    }

    /**
     * 获取当前登录用户
     *
     * @param httpRequest
     * @return
     */
    @Override
    public User getCurrentUserByToken(HttpServletRequest httpRequest) {
        //获取浏览器标头数据
        //获取token
        String token = httpRequest.getHeader("Token");
        if (token == null || token.isEmpty()) {
            return null;
        }
        //根据token获取用户信息
        User userInfo = this.getOne(new QueryWrapper<User>().eq("token", token));
        //返回用户信息
        return userInfo;
    }

}

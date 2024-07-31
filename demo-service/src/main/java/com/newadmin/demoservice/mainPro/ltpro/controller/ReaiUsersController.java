package com.newadmin.demoservice.mainPro.ltpro.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.democore.kduck.web.json.JsonPageObject;
import com.newadmin.demolog.log.core.annotation.Log;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiUsers;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiUsersService;
import java.io.Serializable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author couei
 * @since 2024-05-12
 */
@Log(ignore = true)
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class ReaiUsersController {

    private final ReaiUsersService reaiUsersService;


    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @return 所有数据
     */
    @GetMapping("/pageList")
    public JsonPageObject selectPageAll(Page page) {
        return new JsonPageObject(page, reaiUsersService.pageList(page));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public JsonObject selectOne(@PathVariable Serializable id) {
        return new JsonObject(reaiUsersService.getUser(id));
    }

    /**
     * 修改数据
     *
     * @param reaiUsers 实体对象
     * @return 修改结果
     */
    @PutMapping("/upd")
    public JsonObject update(@RequestBody ReaiUsers reaiUsers) {
        return new JsonObject(reaiUsersService.updateUserInfo(reaiUsers));
    }



    /**
     * 注册
     */
    @PostMapping("/register")
    public JsonObject register(@RequestBody ReaiUsers reaiUsers) {
        ReaiUsers register = reaiUsersService.register(reaiUsers);
        if (register == null) {
            return new JsonObject("用户名已存在");
        }
        return new JsonObject("注册成功! 用户名：" + register.getUsername());
    }


    /**
     * 关注
     *
     * @param id
     * @return
     */
    @GetMapping("getFollowListById")
    public JsonObject getFollowListById(String id) {
        return new JsonObject(reaiUsersService.getFollowListById(id));
    }

    // 查询登录状态  ---- http://localhost:8081/acc/isLogin
    @GetMapping("isLogin")
    public JsonObject isLogin() {
        return new JsonObject(StpUtil.isLogin());
    }

    // 查询 Token 信息  ---- http://localhost:8081/acc/tokenInfo
    @GetMapping("tokenInfo")
    public JsonObject tokenInfo() {
        return new JsonObject(StpUtil.getTokenInfo());
    }

    // 测试注销  ---- http://localhost:8081/acc/logout
    @GetMapping("logout")
    public JsonObject logout() {
        StpUtil.logout();
        return JsonObject.SUCCESS;
    }
}

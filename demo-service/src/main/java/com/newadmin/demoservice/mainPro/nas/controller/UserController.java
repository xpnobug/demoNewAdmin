package com.newadmin.demoservice.mainPro.nas.controller;

import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.democore.kduck.web.json.JsonPageObject;
import com.newadmin.demoservice.mainPro.nas.dto.UserPwd;
import com.newadmin.demoservice.mainPro.nas.entity.User;
import com.newadmin.demoservice.mainPro.nas.service.UserService;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
 * @since 2024-03-31
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @return 所有数据
     */
    @GetMapping("/pageList")
    public JsonPageObject selectPageAll(Page page) {
        return new JsonPageObject(page, userService.list());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public JsonObject selectOne(@PathVariable Serializable id) {
        return new JsonObject(userService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param user 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    public JsonObject insert(@RequestBody User user) {
        return new JsonObject(userService.save(user));
    }

    /**
     * 修改数据
     *
     * @param user 实体对象
     * @return 修改结果
     */
    @PutMapping
    public JsonObject update(@RequestBody User user) {
        return new JsonObject(userService.updateById(user));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public JsonObject delete(@RequestBody List<Long> idList) {
        return new JsonObject(userService.removeByIds(idList));
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public JsonObject login(@RequestBody User user) {
        return new JsonObject(userService.login(user));
    }

    /**
     * 用户退出登录
     *
     * @param user
     * @return
     */
    @PostMapping("/logout")
    public JsonObject logout(@RequestBody User user) {
        return new JsonObject("退出登录");
    }

    /**
     * 获取用户信息
     *
     * @param user
     * @return
     */
    @PostMapping("/getAuthInfo")
    public JsonObject getAuthInfo(@RequestBody User user) {
        return new JsonObject(userService.getById(user.getId()));
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @PostMapping("/updateInfo")
    public JsonObject updateInfo(@RequestBody User user) {
        return new JsonObject(userService.updateById(user));
    }

    /**
     * 修改密码
     *
     * @param userPwd
     * @return
     */
    @PostMapping("/updatePassword")
    public JsonObject updatePassword(@RequestBody UserPwd userPwd) {
        try {
            //先查当前数据库中的密码
            User user = userService.getById(userPwd.getId());
            //验证原密码是否正确
            if (!user.getPassword().equals(userPwd.getOldPassword())) {
                throw new RuntimeException("原密码错误");
            }
            //修改密码
            user.setPassword(userPwd.getNewPassword());
            return new JsonObject(userService.updateById(user));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/getList")
    public JsonPageObject getList(@RequestBody Page page) {
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("page",page.getCurrentPage());
        List<User> list = userService.list();
        return new JsonPageObject(page, list);
    }

    @PostMapping("/create")
    public JsonObject create(@RequestBody User user) {
        //自动生成token密钥
//        user.setToken(JwtUtil.createToken(user.getId(), user.getPassword()));
//        userService.updateById(user);
        return new JsonObject(userService.save(user));
    }
}

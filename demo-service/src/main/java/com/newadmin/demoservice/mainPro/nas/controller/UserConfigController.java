package com.newadmin.demoservice.mainPro.nas.controller;

import cn.hutool.json.JSONObject;
import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.democore.kduck.web.json.JsonPageObject;
import com.newadmin.demoservice.mainPro.nas.entity.User;
import com.newadmin.demoservice.mainPro.nas.entity.UserConfig;
import com.newadmin.demoservice.mainPro.nas.service.UserConfigService;
import com.newadmin.demoservice.mainPro.nas.service.UserService;
import com.newadmin.demoservice.mainPro.nas.service.impl.BaseServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("/panel/userConfig")
public class UserConfigController {

    @Autowired
    private UserConfigService userConfigService;
    @Autowired
    private UserService userService;
    @Autowired
    private BaseServiceImpl baseService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @return 所有数据
     */
    @GetMapping("/pageList")
    public JsonPageObject selectPageAll(Page page) {
        return new JsonPageObject(page, userConfigService.list());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public JsonObject selectOne(@PathVariable Serializable id) {
        return new JsonObject(userConfigService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param userConfig 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    public JsonObject insert(@RequestBody UserConfig userConfig) {
        return new JsonObject(userConfigService.save(userConfig));
    }

    /**
     * 修改数据
     *
     * @param userConfig 实体对象
     * @return 修改结果
     */
    @PutMapping
    public JsonObject update(@RequestBody UserConfig userConfig) {
        return new JsonObject(userConfigService.updateById(userConfig));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public JsonObject delete(@RequestBody List<Long> idList) {
        return new JsonObject(userConfigService.removeByIds(idList));
    }

    @PostMapping("/get")
    public JsonObject get(@RequestBody UserConfig userConfig, HttpServletRequest request) {
        User user = baseService.getCurrentUserByToken(request);
        if (user == null) {
            return new JsonObject(userConfigService.getById(1));
        } else {
            return new JsonObject(userConfigService.getById(user.getId()));
        }
    }

    @PostMapping("/set")
    public JsonObject set(@RequestBody String panel, HttpServletRequest request) {
        User user = baseService.getCurrentUserByToken(request);
        UserConfig userConfigId = userConfigService.getById(user.getId());
        //判断配置是否存在
        JSONObject jsonObject = new JSONObject(panel);
        JSONObject ids = jsonObject.getJSONObject("panel");
        UserConfig userConfig = new UserConfig();
        userConfig.setUserId(user.getId());
        userConfig.setPanelJson(String.valueOf(ids));
        if (userConfigId == null) {
            return new JsonObject(userConfigService.save(userConfig));
        } else {
            return new JsonObject(userConfigService.updateById(userConfig));
        }
    }
}

package com.newadmin.demoservice.mainPro.nas.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.newadmin.democommon.utils.Page;
import com.newadmin.democommon.web.json.JsonObject;
import com.newadmin.democommon.web.json.JsonPageObject;
import com.newadmin.demoservice.mainPro.nas.entity.SystemSetting;
import com.newadmin.demoservice.mainPro.nas.service.SystemSettingService;
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
@RequestMapping("/system")
public class SystemSettingController {

    @Autowired
    private SystemSettingService systemSettingService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @return 所有数据
     */
    @GetMapping("/pageList")
    public JsonPageObject selectPageAll(Page page) {
        return new JsonPageObject(page, systemSettingService.list());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public JsonObject selectOne(@PathVariable Serializable id) {
        return new JsonObject(systemSettingService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param systemSetting 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    public JsonObject insert(@RequestBody SystemSetting systemSetting) {
        return new JsonObject(systemSettingService.save(systemSetting));
    }

    /**
     * 修改数据
     *
     * @param systemSetting 实体对象
     * @return 修改结果
     */
    @PutMapping
    public JsonObject update(@RequestBody SystemSetting systemSetting) {
        return new JsonObject(systemSettingService.updateById(systemSetting));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public JsonObject delete(@RequestBody List<Long> idList) {
        return new JsonObject(systemSettingService.removeByIds(idList));
    }

    /**
     * 通过名称查询单条数据
     *
     * @param name
     * @return
     */
    @PostMapping("/moduleConfig/getByName")
    public JsonObject getByName(@RequestBody String name) {
        return new JsonObject(
            systemSettingService.getOne(new QueryWrapper<SystemSetting>().eq("config_name", name)));
    }

    @PostMapping("/moduleConfig/save")
    public JsonObject save(@RequestBody SystemSetting systemSetting) {
        return new JsonObject(systemSettingService.save(systemSetting));
    }
}

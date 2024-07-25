package com.newadmin.demoservice.mainPro.nas.controller;

import com.newadmin.democommon.utils.Page;
import com.newadmin.democommon.web.json.JsonObject;
import com.newadmin.democommon.web.json.JsonPageObject;
import com.newadmin.demoservice.mainPro.nas.entity.ModuleConfig;
import com.newadmin.demoservice.mainPro.nas.service.ModuleConfigService;
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
@RequestMapping("/moduleConfig")
public class ModuleConfigController {

    @Autowired
    private ModuleConfigService moduleConfigService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @return 所有数据
     */
    @GetMapping("/pageList")
    public JsonPageObject selectPageAll(Page page) {
        return new JsonPageObject(page, moduleConfigService.list());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public JsonObject selectOne(@PathVariable Serializable id) {
        return new JsonObject(moduleConfigService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param moduleConfig 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    public JsonObject insert(@RequestBody ModuleConfig moduleConfig) {
        return new JsonObject(moduleConfigService.save(moduleConfig));
    }

    /**
     * 修改数据
     *
     * @param moduleConfig 实体对象
     * @return 修改结果
     */
    @PutMapping
    public JsonObject update(@RequestBody ModuleConfig moduleConfig) {
        return new JsonObject(moduleConfigService.updateById(moduleConfig));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public JsonObject delete(@RequestBody List<Long> idList) {
        return new JsonObject(moduleConfigService.removeByIds(idList));
    }
}

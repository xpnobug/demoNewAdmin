package com.newadmin.demoservice.mainPro.ltpro.controller;

import com.newadmin.democommon.utils.Page;
import com.newadmin.democommon.web.json.JsonObject;
import com.newadmin.democommon.web.json.JsonPageObject;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiChannel;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author couei
 * @since 2024-07-27
 */
@RestController
@RequestMapping("/channel")
public class ReaiChannelController {

    @Autowired
    private ReaiChannelService reaiChannelService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @return 所有数据
     */
    @GetMapping("/pageList")
    public JsonPageObject selectPageAll(Page page) {
        return new JsonPageObject(page, reaiChannelService.list(page));
    }

    @GetMapping("/channelList")
    public JsonPageObject listQuery(Page page, Integer isOfficial) {
        return new JsonPageObject(page, reaiChannelService.listQuery(isOfficial));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public JsonObject selectOne(@PathVariable String id) {
        return new JsonObject(reaiChannelService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param reaiChannel 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    public JsonObject insert(@RequestBody ReaiChannel reaiChannel) {
        return new JsonObject(reaiChannelService.save(reaiChannel));
    }

}

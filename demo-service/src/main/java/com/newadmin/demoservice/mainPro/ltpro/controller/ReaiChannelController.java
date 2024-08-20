package com.newadmin.demoservice.mainPro.ltpro.controller;

import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.democore.kduck.web.json.JsonPageObject;
import com.newadmin.demolog.log.core.annotation.Log;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiChannel;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiChannelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author couei
 * @since 2024-07-27
 */
@Tag(name = "频道 API")
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
    @Operation(summary = "分页查询频道数据", description = "分页查询频道数据")
    @GetMapping("/pageList")
    public JsonPageObject selectPageAll(Page page) {
        return new JsonPageObject(page, reaiChannelService.list(page));
    }

    @Operation(summary = "分页查询频道数据", description = "分页查询频道数据")
    @GetMapping("/channelList")
    public JsonPageObject listQuery(Page page, Integer isOfficial) {
        return new JsonPageObject(page, reaiChannelService.listQuery(page, isOfficial));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Log(ignore = true)
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
    @Operation(summary = "新增频道数据", description = "新增频道数据")
    @PostMapping("/add")
    public JsonObject insert(@RequestBody ReaiChannel reaiChannel) {
        return new JsonObject(reaiChannelService.save(reaiChannel));
    }

    @Operation(summary = "加入频道", description = "加入频道")
    @PostMapping("/join")
    public JsonObject join(@RequestBody ReaiChannel reaiChannel) {
        return new JsonObject(reaiChannelService.joinChannel(reaiChannel));
    }

    @Operation(summary = "退出频道", description = "退出频道")
    @DeleteMapping("/quit")
    public JsonObject quit(@RequestParam String id) {
        return new JsonObject(reaiChannelService.quitChannel(id));
    }

}

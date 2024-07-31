package com.newadmin.demoservice.mainPro.ltpro.controller;

import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiMusic;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiMusicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
 * @since 2024-06-20
 */
@Tag(name = "音乐管理 API")
@RestController
@RequestMapping("/music")
public class ReaiMusicController {

    @Autowired
    private ReaiMusicService reaiMusicService;

    /**
     * 新增数据
     *
     * @param music 新增实体
     * @return 新增结果
     */
    @Operation(summary = "新增音乐", description = "新增音乐")
    @PostMapping("/addMusic")
    public JsonObject insert(@RequestBody ReaiMusic music) {
        return new JsonObject(reaiMusicService.addSong(music));
    }

    @Operation(summary = "删除音乐", description = "删除音乐")
    @DeleteMapping("/deleteMusic")
    public JsonObject delete(String sid) {
        return new JsonObject(reaiMusicService.deleteSong(sid));
    }
}

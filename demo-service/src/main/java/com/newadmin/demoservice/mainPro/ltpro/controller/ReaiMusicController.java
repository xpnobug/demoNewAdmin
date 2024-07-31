package com.newadmin.demoservice.mainPro.ltpro.controller;

import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiMusic;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiMusicService;
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
    @PostMapping("/addMusic")
    public JsonObject insert(@RequestBody ReaiMusic music) {
        return new JsonObject(reaiMusicService.addSong(music));
    }

    @DeleteMapping("/deleteMusic")
    public JsonObject delete(String sid) {
        return new JsonObject(reaiMusicService.deleteSong(sid));
    }
}

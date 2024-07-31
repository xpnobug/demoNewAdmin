package com.newadmin.demoservice.mainPro.ltpro.controller;

import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiLikes;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiLikesService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 存储用户对文章点赞信息的表 前端控制器
 * </p>
 *
 * @author couei
 * @since 2024-06-21
 */
@RestController
@RequestMapping("/likes")
public class ReaiLikesController {

    @Autowired
    private ReaiLikesService reaiLikesService;

    /**
     * 点赞
     *
     * @param reaiLikes
     * @return
     */
    @PostMapping("/giveALike")
    public JsonObject giveALike(@RequestBody ReaiLikes reaiLikes, HttpServletRequest request) {
        String s = reaiLikesService.giveALike(reaiLikes, request);
        return new JsonObject(s);
    }

    @GetMapping("/getLikesList")
    public JsonObject getLikesList() {
        List<ReaiLikes> userInfoAndLikesList = reaiLikesService.getUserInfoAndLikesList(null, null);
        return new JsonObject(userInfoAndLikesList);
    }
}

package com.newadmin.demoservice.mainPro.comment.controller;

import com.newadmin.demoservice.mainPro.comment.service.ReaiCommentLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 评论点赞表 前端控制器
 * </p>
 *
 * @author couei
 * @since 2024-07-14
 */
@RestController
@RequestMapping("/reaiCommentLike")
public class ReaiCommentLikeController {

    @Autowired
    private ReaiCommentLikeService reaiCommentLikeService;

}

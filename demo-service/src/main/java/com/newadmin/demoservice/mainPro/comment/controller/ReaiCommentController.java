package com.newadmin.demoservice.mainPro.comment.controller;

import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.democore.kduck.web.json.JsonPageObject;
import com.newadmin.demolog.log.core.annotation.Log;
import com.newadmin.demoservice.mainPro.comment.dto.CommentDTO;
import com.newadmin.demoservice.mainPro.comment.service.ReaiCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author couei
 * @since 2024-07-14
 */
@Tag(name = "评论管理")
@RestController
@RequestMapping("/comment")
public class ReaiCommentController {

    @Autowired
    private ReaiCommentService commentService;

    @Log(ignore = true)
    @GetMapping("/page")
    public JsonPageObject page(Page page, String articleId) {
        return new JsonPageObject(page, commentService.pageByAid(articleId));
    }

    @Operation(summary = "添加评论")
    @PostMapping("/add")
    public JsonObject add(@RequestBody CommentDTO comment) {
        return new JsonObject(commentService.save(comment));
    }
}

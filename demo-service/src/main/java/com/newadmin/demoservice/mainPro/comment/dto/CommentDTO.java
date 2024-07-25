package com.newadmin.demoservice.mainPro.comment.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author readpage
 * @date 2023-01-07 9:39
 */
@Data
public class CommentDTO {

    private String id;
    /**
     * 文章id
     */
    private String articleId;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论父id
     */
    private String parentId;

    /**
     * 上传的文件
     */
    private MultipartFile[] files;
}

package com.newadmin.demoservice.mainPro.comment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author 评论点赞相关数据
 * @date 2023-01-07 16:03
 */
@Data
public class LikedDTO {

    /**
     * 评论id
     */
    private Integer commentId;

    /**
     * 用户id
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer uid;
}

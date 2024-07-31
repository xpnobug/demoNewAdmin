package com.newadmin.demoservice.mainPro.comment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.newadmin.democore.kduck.service.ValueMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 评论点赞表
 * </p>
 *
 * @author couei
 * @since 2024-07-14
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("reai_comment_like")
public class ReaiCommentLike extends ValueMap {

    /**
     * 评论点赞ID
     */
    public static final String ID = "id";

    /**
     * 用户ID
     */
    public static final String UID = "uid";

    /**
     * 评论ID
     */
    public static final String COMMENT_ID = "commentId";

    /**
     * 创建时间
     */
    public static final String CREATE_TIME = "createTime";

    /**
     * 修改时间
     */
    public static final String UPDATE_TIME = "updateTime";

    public ReaiCommentLike() {
    }

    public ReaiCommentLike(Map<String, Object> map) {
        super(map);
    }
}

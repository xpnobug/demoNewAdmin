package com.newadmin.demoservice.mainPro.comment.service;

import com.newadmin.demoservice.mainPro.comment.dto.CommentDTO;
import com.newadmin.demoservice.mainPro.comment.entity.ReaiComment;
import java.util.List;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author couei
 * @since 2024-07-14
 */
public interface ReaiCommentService {

    /**
     * 添加评论
     *
     * @param commentDTO
     * @return
     */
    ReaiComment save(CommentDTO commentDTO);

    /**
     * 根据文章id查询分页评论列表(评论对象里包含回复(reply)的数据)
     *
     * @param articleId 文章id
     * @return
     */
    List<ReaiComment> pageByAid(String articleId);

    /**
     * 分页查询回复
     *
     * @param parentId 父评论id
     * @return
     */
    List<ReaiComment> replyPage(String parentId);

    /**
     * 分页查询评论(评论对象里不包含回复(reply)的数据)
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<ReaiComment> page(int pageNum, int pageSize);

    /**
     * 批量修改点赞数量
     *
     * @param list
     * @return
     */
    boolean updateBatchById(List<ReaiComment> list);

}

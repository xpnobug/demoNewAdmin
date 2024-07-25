package com.newadmin.demoservice.mainPro.comment.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.newadmin.democommon.service.DefaultService;
import com.newadmin.democommon.service.ValueMap;
import com.newadmin.democommon.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democommon.sqlbuild.SelectBuilder;
import com.newadmin.democommon.utils.ConversionUtils;
import com.newadmin.demoservice.base.message.BaseMessageSend;
import com.newadmin.demoservice.mainPro.comment.dto.CommentDTO;
import com.newadmin.demoservice.mainPro.comment.entity.CommentUsers;
import com.newadmin.demoservice.mainPro.comment.entity.ReaiComment;
import com.newadmin.demoservice.mainPro.comment.service.ReaiCommentService;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiUsers;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiUsersService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author couei
 * @since 2024-07-14
 */
@Service
@RequiredArgsConstructor
public class ReaiCommentServiceImpl extends DefaultService implements ReaiCommentService {

    public static final String TABLE_NAME = "reai_comment";

    private final ReaiUsersService usersService;
    private final BaseMessageSend baseMessageSend;

    @Override
    public ReaiComment save(CommentDTO commentDTO) {
        //是否登录
        boolean login = StpUtil.isLogin();
        if (!login) {
            throw new RuntimeException("请先登录");
        }
        //获取用户信息
        String comment_img = null;
//        if (StrUtil.isNotEmpty(commentDTO.getFiles())) {
//            comment_img = FileUtils.upload(commentDTO.getFiles(), "comment_img");
//        }
        ReaiComment comment = new ReaiComment();
//        comment.setContentImg(comment_img);
        comment.setId(commentDTO.getId());
        comment.setContent(commentDTO.getContent());
        comment.setArticleId(commentDTO.getArticleId());
        comment.setParentId(commentDTO.getParentId());
        comment.setCreateTime(new Date());
        comment.setUserId(commentDTO.getUid());
        super.add(TABLE_NAME, comment);

        // 根据parentid 查userid
        String replyToUserId = null;
        if (commentDTO.getParentId() != null) {
            ReaiComment reaiComments = getReaiComments(commentDTO.getParentId());
            replyToUserId = reaiComments.getUserId();
        }
        //发送消息
        baseMessageSend.sendCommentMsg(comment, replyToUserId);
        return comment;
    }

    @Override
    public List<ReaiComment> pageByAid(String articleId) {
        List<ReaiComment> parentList = getReaiComments(articleId, null);

        List<ReaiComment> result = new ArrayList<>();
        // 遍历父评论列表并添加对应的回复和用户信息
        for (ReaiComment comment : parentList) {
            String id = comment.getId();

            // 查询回复
            List<ReaiComment> replyPage = getReaiComments(null, id);
            if (!replyPage.isEmpty()) {
                List<ReaiComment.Reply> replies = new ArrayList<>();

                for (ReaiComment replyComment : replyPage) {
                    ReaiComment.Reply reply = ConversionUtils.convert(replyComment,
                        ReaiComment.Reply.class);

                    ReaiUsers user = usersService.getUserById(reply.getUserId());
                    CommentUsers commentUser = new CommentUsers();
                    commentUser.setUsername(user.getUsername());
                    commentUser.setAvatar(user.getAvatar());
                    reply.setUser(commentUser);

                    replies.add(reply);
                }

                ReaiComment.ReplyList replyList = new ReaiComment.ReplyList();
                replyList.setTotal(replies.size());
                replyList.setList(replies);
                comment.setReply(replyList);
            }
            // 获取用户信息并设置到评论中
            ReaiUsers user = usersService.getUserById(comment.getUserId());
            CommentUsers commentUser = new CommentUsers();
            commentUser.setUserId(user.getUserId());
            commentUser.setUsername(user.getUsername());
            commentUser.setAvatar(user.getAvatar());
            comment.setUser(commentUser);
            if (comment.getParentId() == null) {
                result.add(comment);
            }
        }
        return result;
    }

    private List<ReaiComment> getReaiComments(String articleId, String parentId) {
        // 根据文章id查询评论列表(不包含回复)
        ValueMap params = new ValueMap();
        params.put("articleId", articleId);
        params.put("parentId", parentId);
        SelectBuilder selectBuilder = new SelectBuilder(params);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
            .and("article_id", ConditionType.EQUALS, ReaiComment.ARTICLE_ID)
            .and("parent_id", ConditionType.EQUALS, ReaiComment.PARENT_ID)
            .orderBy().desc("create_time");
        List<ReaiComment> parentList = super.listForBean(selectBuilder.build(), ReaiComment::new);
        return parentList;
    }

    private ReaiComment getReaiComments(String parentId) {
        // 根据文章id查询评论列表(不包含回复)
        ValueMap params = new ValueMap();
        params.put("parentId", parentId);
        SelectBuilder selectBuilder = new SelectBuilder(params);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
            .and("id", ConditionType.EQUALS, ReaiComment.PARENT_ID)
            .orderBy().desc("create_time");
        return super.getForBean(selectBuilder.build(), ReaiComment::new);
    }

    @Override
    public List<ReaiComment> replyPage(String parentId) {
        List<ReaiComment> replyList = getReaiComments(null, parentId);
//        for (ReaiComment reply : replyList) {
//            // 合并redis中的点赞数量
//            reply.setLikes(reply.getLikes() + getLikeCount(id));
//        }
        return replyList;
    }

    @Override
    public List<ReaiComment> page(int pageNum, int pageSize) {
        return List.of();
    }

    @Override
    public boolean updateBatchById(List<ReaiComment> list) {
        return false;
    }
}

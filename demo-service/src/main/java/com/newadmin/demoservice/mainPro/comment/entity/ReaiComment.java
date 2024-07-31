package com.newadmin.demoservice.mainPro.comment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.newadmin.democore.kduck.service.ValueMap;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author couei
 * @since 2024-07-14
 */

@TableName("reai_comment")
public class ReaiComment extends ValueMap {

    /**
     * 评论ID
     */
    public static final String ID = "id";

    /**
     * 父节点
     */
    public static final String PARENT_ID = "parentId";

    /**
     * 文章ID
     */
    public static final String ARTICLE_ID = "articleId";

    /**
     * 用户ID
     */
    public static final String USER_ID = "userId";

    /**
     * 地址
     */
    public static final String ADDRESS = "address";

    /**
     * 评论内容
     */
    public static final String CONTENT = "content";

    /**
     * 点赞数
     */
    public static final String LIKES = "likes";

    /**
     * 图片
     */
    public static final String CONTENT_IMG = "contentImg";

    /**
     * 创建时间
     */
    public static final String CREATE_TIME = "createTime";

    /***/
    public static final String USER = "user";

    /***/
    public static final String REPLY = "reply";

    public ReaiComment() {
    }

    public ReaiComment(Map<String, Object> map) {
        super(map);
    }

    /**
     * 设置 评论ID
     *
     * @param id 评论ID
     */
    public void setId(String id) {
        super.setValue(ID, id);
    }

    /**
     * 获取 评论ID
     *
     * @return 评论ID
     */
    public String getId() {
        return super.getValueAsString(ID);
    }

    /**
     * 设置 父节点
     *
     * @param parentId 父节点
     */
    public void setParentId(String parentId) {
        super.setValue(PARENT_ID, parentId);
    }

    /**
     * 获取 父节点
     *
     * @return 父节点
     */
    public String getParentId() {
        return super.getValueAsString(PARENT_ID);
    }

    /**
     * 设置 文章ID
     *
     * @param articleId 文章ID
     */
    public void setArticleId(String articleId) {
        super.setValue(ARTICLE_ID, articleId);
    }

    /**
     * 获取 文章ID
     *
     * @return 文章ID
     */
    public String getArticleId() {
        return super.getValueAsString(ARTICLE_ID);
    }

    /**
     * 设置 用户ID
     *
     * @param uid 用户ID
     */
    public void setUserId(String uid) {
        super.setValue(USER_ID, uid);
    }

    /**
     * 获取 用户ID
     *
     * @return 用户ID
     */
    public String getUserId() {
        return super.getValueAsString(USER_ID);
    }

    /**
     * 设置 地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        super.setValue(ADDRESS, address);
    }

    /**
     * 获取 地址
     *
     * @return 地址
     */
    public String getAddress() {
        return super.getValueAsString(ADDRESS);
    }

    /**
     * 设置 评论内容
     *
     * @param content 评论内容
     */
    public void setContent(String content) {
        super.setValue(CONTENT, content);
    }

    /**
     * 获取 评论内容
     *
     * @return 评论内容
     */
    public String getContent() {
        return super.getValueAsString(CONTENT);
    }

    /**
     * 设置 点赞数
     *
     * @param likes 点赞数
     */
    public void setLikes(Integer likes) {
        super.setValue(LIKES, likes);
    }

    /**
     * 获取 点赞数
     *
     * @return 点赞数
     */
    public Integer getLikes() {
        return super.getValueAsInteger(LIKES);
    }

    /**
     * 设置 图片
     *
     * @param contentImg 图片
     */
    public void setContentImg(String contentImg) {
        super.setValue(CONTENT_IMG, contentImg);
    }

    /**
     * 获取 图片
     *
     * @return 图片
     */
    public String getContentImg() {
        return super.getValueAsString(CONTENT_IMG);
    }

    /**
     * 设置 创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        super.setValue(CREATE_TIME, createTime);
    }

    /**
     * 获取 创建时间
     *
     * @return 创建时间
     */
    public Date getCreateTime() {
        return super.getValueAsDate(CREATE_TIME);
    }

    /**
     * 设置
     *
     * @param user
     */
    public void setUser(CommentUsers user) {
        super.setValue(USER, user);
    }

    /**
     * 获取
     *
     * @return
     */
    public CommentUsers getUser() {
        return super.getValueAsBean(USER, CommentUsers.class);
    }

    public void setReply(ReplyList reply) {
        super.setValue(REPLY, reply);
    }

    public ReplyList getReply() {
        return super.getValueAsBean(REPLY, ReplyList.class);
    }

    public static class ReplyList extends ValueMap {

        public static final String TOTAL = "total";
        public static final String LIST = "list";

        public void setTotal(Integer total) {
            super.setValue(TOTAL, total);
        }

        public Integer getTotal() {
            return super.getValueAsInteger(TOTAL);
        }

        public void setList(List<Reply> list) {
            super.setValue(LIST, list);
        }

        public List<Reply> getList() {
            return super.getValueAsList(LIST);
        }
    }

    public static class Reply extends ValueMap {

        public void setId(String id) {
            super.setValue(ID, id);
        }

        public String getId() {
            return super.getValueAsString(ID);
        }

        public void setParentId(String parentId) {
            super.setValue(PARENT_ID, parentId);
        }

        public String getParentId() {
            return super.getValueAsString(PARENT_ID);
        }

        public void setUserId(String uid) {
            super.setValue(USER_ID, uid);
        }

        public String getUserId() {
            return super.getValueAsString(USER_ID);
        }

        public void setContent(String content) {
            super.setValue(CONTENT, content);
        }

        public String getContent() {
            return super.getValueAsString(CONTENT);
        }

        public void setCreateTime(String createTime) {
            super.setValue(CREATE_TIME, createTime);
        }

        public String getCreateTime() {
            return super.getValueAsString(CREATE_TIME);
        }

        public void setUser(CommentUsers user) {
            super.setValue(USER, user);
        }

        public CommentUsers getUser() {
            return super.getValueAsBean(USER, CommentUsers.class);
        }
    }
}

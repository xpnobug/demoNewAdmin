package com.newadmin.demoservice.mainPro.ltpro.entity;

import com.newadmin.democore.kduck.service.ValueMap;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author couei
 * @since 2024-05-16
 */

public class ReaiArticle extends ValueMap {

    /**
     * 文章ID
     */
    public static final String ARTICLE_ID = "articleId";

    /**
     * 文章标题
     */
    public static final String TITLE = "title";

    /**
     * 文章内容
     */
    public static final String CONTENT = "content";

    /**
     * 文章作者
     */
    public static final String AUTHOR = "author";

    /**
     * 发布日期
     */
    public static final String PUBLISH_DATE = "publishDate";

    /**
     * 文章标签
     */
    public static final String TAG = "tag";

    /**
     * 封面图片链接
     */
    public static final String COVER_IMAGE = "coverImage";

    /**
     * 阅读次数
     */
    public static final String READ_COUNT = "readCount";

    /**
     * 点赞次数
     */
    public static final String LIKE_COUNT = "likeCount";

    /**
     * 评论次数
     */
    public static final String COMMENT_COUNT = "commentCount";

    /**
     * 分享次数
     */
    public static final String SHARE_COUNT = "shareCount";

    /**
     * 收藏次数
     */
    public static final String COLLECT_COUNT = "collectCount";

    /**
     * 文章摘要
     */
    public static final String SUMMARY = "summary";

    /**
     * 文章状态
     */
    public static final String ARTICLE_STATUS = "articleStatus";

    /**
     * 文章来源
     */
    public static final String SOURCE = "source";

    /**
     * 文章链接
     */
    public static final String URL = "url";

    /**
     * 推荐指数
     */
    public static final String RECOMMENDATIONS = "recommendations";

    /**
     * 字数统计
     */
    public static final String WORD_COUNT = "wordCount";

    /**
     * 阅读时长（分钟）
     */
    public static final String READING_TIME = "readingTime";

    /**
     * 是否置顶
     */
    public static final String IS_FEATURED = "isFeatured";

    /**
     * 相关文章列表
     */
    public static final String RELATED_ARTICLES = "relatedArticles";

    /**
     * 编辑建议
     */
    public static final String EDITORIAL_SUGGESTIONS = "editorialSuggestions";

    /**
     * SEO相关信息
     */
    public static final String SEO_INFORMATION = "seoInformation";

    /**
     * 版权信息
     */
    public static final String COPYRIGHT = "copyright";

    /**
     * 发布平台
     */
    public static final String PUBLISH_PLATFORM = "publishPlatform";
    /***/
    public static final String CHANNEL_ID = "channelId";

    /**
     * 用户id
     */
    public static final String USER_ID = "userId";

    /**
     * 修改时间
     */
    public static final String UPDATE_TIME = "updateTime";

    /**
     * 设置不参与查询
     */
    public static final String AVATAR = "avatar";
    /***/
    public static final String EXP = "exp";
    /***/
    public static final String LEVEL = "level";
    /***/
    public static final String IMG_LIST = "imgList";

    /**
     * 地址
     */
    public static final String ADDRESS = "address";
    /***/
    public static final String NAME = "name";
    /***/
    public static final String TYPE = "type";

    public ReaiArticle() {
    }

    public ReaiArticle(Map<String, Object> map) {
        super(map);
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
     * 设置 文章标题
     *
     * @param title 文章标题
     */
    public void setTitle(String title) {
        super.setValue(TITLE, title);
    }

    /**
     * 获取 文章标题
     *
     * @return 文章标题
     */
    public String getTitle() {
        return super.getValueAsString(TITLE);
    }

    /**
     * 设置 文章内容
     *
     * @param content 文章内容
     */
    public void setContent(String content) {
        super.setValue(CONTENT, content);
    }

    /**
     * 获取 文章内容
     *
     * @return 文章内容
     */
    public String getContent() {
        return super.getValueAsString(CONTENT);
    }

    /**
     * 设置 文章作者
     *
     * @param author 文章作者
     */
    public void setAuthor(String author) {
        super.setValue(AUTHOR, author);
    }

    /**
     * 获取 文章作者
     *
     * @return 文章作者
     */
    public String getAuthor() {
        return super.getValueAsString(AUTHOR);
    }

    /**
     * 设置 发布日期
     *
     * @param publishDate 发布日期
     */
    public void setPublishDate(Date publishDate) {
        super.setValue(PUBLISH_DATE, publishDate);
    }

    /**
     * 获取 发布日期
     *
     * @return 发布日期
     */
    public Date getPublishDate() {
        return super.getValueAsDate(PUBLISH_DATE);
    }

    /**
     * 设置 文章标签
     *
     * @param tag 文章标签
     */
    public void setTag(String tag) {
        super.setValue(TAG, tag);
    }

    /**
     * 获取 文章标签
     *
     * @return 文章标签
     */
    public String getTag() {
        return super.getValueAsString(TAG);
    }

    /**
     * 设置 封面图片链接
     *
     * @param coverImage 封面图片链接
     */
    public void setCoverImage(String coverImage) {
        super.setValue(COVER_IMAGE, coverImage);
    }

    /**
     * 获取 封面图片链接
     *
     * @return 封面图片链接
     */
    public String getCoverImage() {
        return super.getValueAsString(COVER_IMAGE);
    }

    /**
     * 设置 阅读次数
     *
     * @param readCount 阅读次数
     */
    public void setReadCount(Integer readCount) {
        super.setValue(READ_COUNT, readCount);
    }

    /**
     * 获取 阅读次数
     *
     * @return 阅读次数
     */
    public Integer getReadCount() {
        return super.getValueAsInteger(READ_COUNT);
    }

    /**
     * 设置 点赞次数
     *
     * @param likeCount 点赞次数
     */
    public void setLikeCount(Integer likeCount) {
        super.setValue(LIKE_COUNT, likeCount);
    }

    /**
     * 获取 点赞次数
     *
     * @return 点赞次数
     */
    public Integer getLikeCount() {
        return super.getValueAsInteger(LIKE_COUNT);
    }

    /**
     * 设置 评论次数
     *
     * @param commentCount 评论次数
     */
    public void setCommentCount(Integer commentCount) {
        super.setValue(COMMENT_COUNT, commentCount);
    }

    /**
     * 获取 评论次数
     *
     * @return 评论次数
     */
    public Integer getCommentCount() {
        return super.getValueAsInteger(COMMENT_COUNT);
    }

    /**
     * 设置 分享次数
     *
     * @param shareCount 分享次数
     */
    public void setShareCount(Integer shareCount) {
        super.setValue(SHARE_COUNT, shareCount);
    }

    /**
     * 获取 分享次数
     *
     * @return 分享次数
     */
    public Integer getShareCount() {
        return super.getValueAsInteger(SHARE_COUNT);
    }

    /**
     * 设置 收藏次数
     *
     * @param collectCount 收藏次数
     */
    public void setCollectCount(Integer collectCount) {
        super.setValue(COLLECT_COUNT, collectCount);
    }

    /**
     * 获取 收藏次数
     *
     * @return 收藏次数
     */
    public Integer getCollectCount() {
        return super.getValueAsInteger(COLLECT_COUNT);
    }

    /**
     * 设置 文章摘要
     *
     * @param summary 文章摘要
     */
    public void setSummary(String summary) {
        super.setValue(SUMMARY, summary);
    }

    /**
     * 获取 文章摘要
     *
     * @return 文章摘要
     */
    public String getSummary() {
        return super.getValueAsString(SUMMARY);
    }

    /**
     * 设置 文章状态
     *
     * @param articleStatus 文章状态
     */
    public void setArticleStatus(String articleStatus) {
        super.setValue(ARTICLE_STATUS, articleStatus);
    }

    /**
     * 获取 文章状态
     *
     * @return 文章状态
     */
    public String getArticleStatus() {
        return super.getValueAsString(ARTICLE_STATUS);
    }

    /**
     * 设置 文章来源
     *
     * @param source 文章来源
     */
    public void setSource(String source) {
        super.setValue(SOURCE, source);
    }

    /**
     * 获取 文章来源
     *
     * @return 文章来源
     */
    public String getSource() {
        return super.getValueAsString(SOURCE);
    }

    /**
     * 设置 文章链接
     *
     * @param url 文章链接
     */
    public void setUrl(String url) {
        super.setValue(URL, url);
    }

    /**
     * 获取 文章链接
     *
     * @return 文章链接
     */
    public String getUrl() {
        return super.getValueAsString(URL);
    }

    /**
     * 设置 推荐指数
     *
     * @param recommendations 推荐指数
     */
    public void setRecommendations(BigDecimal recommendations) {
        super.setValue(RECOMMENDATIONS, recommendations);
    }

//    /**
//     * 获取 推荐指数
//     *
//     * @return 推荐指数
//     */
//    public BigDecimal getRecommendations() {
//        return super.getValueAsBigDecimal(RECOMMENDATIONS);
//    }

    /**
     * 设置 字数统计
     *
     * @param wordCount 字数统计
     */
    public void setWordCount(Integer wordCount) {
        super.setValue(WORD_COUNT, wordCount);
    }

    /**
     * 获取 字数统计
     *
     * @return 字数统计
     */
    public Integer getWordCount() {
        return super.getValueAsInteger(WORD_COUNT);
    }

    /**
     * 设置 阅读时长（分钟）
     *
     * @param readingTime 阅读时长（分钟）
     */
    public void setReadingTime(Integer readingTime) {
        super.setValue(READING_TIME, readingTime);
    }

    /**
     * 获取 阅读时长（分钟）
     *
     * @return 阅读时长（分钟）
     */
    public Integer getReadingTime() {
        return super.getValueAsInteger(READING_TIME);
    }

    /**
     * 设置 是否置顶
     *
     * @param isFeatured 是否置顶
     */
    public void setIsFeatured(Boolean isFeatured) {
        super.setValue(IS_FEATURED, isFeatured);
    }

    /**
     * 获取 是否置顶
     *
     * @return 是否置顶
     */
    public Boolean getIsFeatured() {
        return super.getValueAsBoolean(IS_FEATURED);
    }

    /**
     * 设置 相关文章列表
     *
     * @param relatedArticles 相关文章列表
     */
    public void setRelatedArticles(String relatedArticles) {
        super.setValue(RELATED_ARTICLES, relatedArticles);
    }

    /**
     * 获取 相关文章列表
     *
     * @return 相关文章列表
     */
    public String getRelatedArticles() {
        return super.getValueAsString(RELATED_ARTICLES);
    }

    /**
     * 设置 编辑建议
     *
     * @param editorialSuggestions 编辑建议
     */
    public void setEditorialSuggestions(String editorialSuggestions) {
        super.setValue(EDITORIAL_SUGGESTIONS, editorialSuggestions);
    }

    /**
     * 获取 编辑建议
     *
     * @return 编辑建议
     */
    public String getEditorialSuggestions() {
        return super.getValueAsString(EDITORIAL_SUGGESTIONS);
    }

    /**
     * 设置 SEO相关信息
     *
     * @param seoInformation SEO相关信息
     */
    public void setSeoInformation(String seoInformation) {
        super.setValue(SEO_INFORMATION, seoInformation);
    }

    /**
     * 获取 SEO相关信息
     *
     * @return SEO相关信息
     */
    public String getSeoInformation() {
        return super.getValueAsString(SEO_INFORMATION);
    }

    /**
     * 设置 版权信息
     *
     * @param copyright 版权信息
     */
    public void setCopyright(String copyright) {
        super.setValue(COPYRIGHT, copyright);
    }

    /**
     * 获取 版权信息
     *
     * @return 版权信息
     */
    public String getCopyright() {
        return super.getValueAsString(COPYRIGHT);
    }

    /**
     * 设置 发布平台
     *
     * @param publishPlatform 发布平台
     */
    public void setPublishPlatform(String publishPlatform) {
        super.setValue(PUBLISH_PLATFORM, publishPlatform);
    }

    /**
     * 获取 发布平台
     *
     * @return 发布平台
     */
    public String getPublishPlatform() {
        return super.getValueAsString(PUBLISH_PLATFORM);
    }

    /**
     * 设置 用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        super.setValue(USER_ID, userId);
    }

    /**
     * 获取 用户id
     *
     * @return 用户id
     */
    public String getUserId() {
        return super.getValueAsString(USER_ID);
    }

    /**
     * 设置 设置不参与查询
     *
     * @param avatar 设置不参与查询
     */
    public void setAvatar(String avatar) {
        super.setValue(AVATAR, avatar);
    }

    /**
     * 获取 设置不参与查询
     *
     * @return 设置不参与查询
     */
    public String getAvatar() {
        return super.getValueAsString(AVATAR);
    }

    /**
     * 设置
     *
     * @param exp
     */
    public void setExp(String exp) {
        super.setValue(EXP, exp);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getExp() {
        return super.getValueAsString(EXP);
    }

    /**
     * 设置
     *
     * @param level
     */
    public void setLevel(Integer level) {
        super.setValue(LEVEL, level);
    }

    /**
     * 获取
     *
     * @return
     */
    public Integer getLevel() {
        return super.getValueAsInteger(LEVEL);
    }

    /**
     * 设置
     *
     * @param imgList
     */
    public void setImgList(List<String> imgList) {
        super.setValue(IMG_LIST, imgList);
    }

    /**
     * 获取
     *
     * @return
     */
    public List<String> getImgList() {
        return super.getValueAsList(IMG_LIST);
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
     * 设置 修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        super.setValue(UPDATE_TIME, updateTime);
    }

    /**
     * 获取 修改时间
     *
     * @return 修改时间
     */
    public Date getUpdateTime() {
        return super.getValueAsDate(UPDATE_TIME);
    }

    /**
     * 设置
     *
     * @param channelId
     */
    public void setChannelId(String channelId) {
        super.setValue(CHANNEL_ID, channelId);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getChannelId() {
        return super.getValueAsString(CHANNEL_ID);
    }

    /**
     * 设置
     *
     * @param name
     */
    public void setName(String name) {
        super.setValue(NAME, name);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getName() {
        return super.getValueAsString(NAME);
    }

    /**
     * 设置
     *
     * @param type
     */
    public void setType(String type) {
        super.setValue(TYPE, type);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getType() {
        return super.getValueAsString(TYPE);
    }
}

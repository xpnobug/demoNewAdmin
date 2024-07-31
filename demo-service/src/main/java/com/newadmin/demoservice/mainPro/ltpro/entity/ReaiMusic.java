package com.newadmin.demoservice.mainPro.ltpro.entity;

import com.newadmin.democore.kduck.service.ValueMap;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author couei
 * @since 2024-06-20
 */

public class ReaiMusic extends ValueMap {

    /***/
    public static final String SID = "sid";

    /***/
    public static final String USER_ID = "userId";

    /***/
    public static final String ARTICLE_ID = "articleId";

    /**
     * 音乐名称
     */
    public static final String SONG_NAME = "songName";

    /**
     * 歌手
     */
    public static final String SINGER = "singer";

    /**
     * 音源url
     */
    public static final String SONG_URL = "songUrl";

    /**
     * 音乐图片
     */
    public static final String SONG_IMG = "songImg";

    /***/
    public static final String SONG_ID = "songId";

    /***/
    public static final String CREATE_DATE = "createDate";

    public ReaiMusic() {
    }

    public ReaiMusic(Map<String, Object> map) {
        super(map);
    }

    /**
     * 设置
     *
     * @param sid
     */
    public void setSid(String sid) {
        super.setValue(SID, sid);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getSid() {
        return super.getValueAsString(SID);
    }

    /**
     * 设置
     *
     * @param userId
     */
    public void setUserId(String userId) {
        super.setValue(USER_ID, userId);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getUserId() {
        return super.getValueAsString(USER_ID);
    }

    /**
     * 设置
     *
     * @param articleId
     */
    public void setArticleId(String articleId) {
        super.setValue(ARTICLE_ID, articleId);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getArticleId() {
        return super.getValueAsString(ARTICLE_ID);
    }

    /**
     * 设置 音乐名称
     *
     * @param songName 音乐名称
     */
    public void setSongName(String songName) {
        super.setValue(SONG_NAME, songName);
    }

    /**
     * 获取 音乐名称
     *
     * @return 音乐名称
     */
    public String getSongName() {
        return super.getValueAsString(SONG_NAME);
    }

    /**
     * 设置 歌手
     *
     * @param singer 歌手
     */
    public void setSinger(String singer) {
        super.setValue(SINGER, singer);
    }

    /**
     * 获取 歌手
     *
     * @return 歌手
     */
    public String getSinger() {
        return super.getValueAsString(SINGER);
    }

    /**
     * 设置 音源url
     *
     * @param songUrl 音源url
     */
    public void setSongUrl(String songUrl) {
        super.setValue(SONG_URL, songUrl);
    }

    /**
     * 获取 音源url
     *
     * @return 音源url
     */
    public String getSongUrl() {
        return super.getValueAsString(SONG_URL);
    }

    /**
     * 设置 音乐图片
     *
     * @param songImg 音乐图片
     */
    public void setSongImg(String songImg) {
        super.setValue(SONG_IMG, songImg);
    }

    /**
     * 获取 音乐图片
     *
     * @return 音乐图片
     */
    public String getSongImg() {
        return super.getValueAsString(SONG_IMG);
    }

    /**
     * 设置
     *
     * @param songId
     */
    public void setSongId(String songId) {
        super.setValue(SONG_ID, songId);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getSongId() {
        return super.getValueAsString(SONG_ID);
    }

    /**
     * 设置
     *
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        super.setValue(CREATE_DATE, createDate);
    }

    /**
     * 获取
     *
     * @return
     */
    public Date getCreateDate() {
        return super.getValueAsDate(CREATE_DATE);
    }
}

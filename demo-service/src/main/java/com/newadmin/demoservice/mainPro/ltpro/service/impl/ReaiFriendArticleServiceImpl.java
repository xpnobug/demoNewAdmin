package com.newadmin.demoservice.mainPro.ltpro.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.demoservice.mainPro.filepro.entity.FileDO;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiArticle;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiLikes;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiMusic;
import com.newadmin.demoservice.mainPro.ltpro.query.ArticleQuery;
import com.newadmin.demoservice.mainPro.ltpro.query.ChannelQuery;
import com.newadmin.demoservice.mainPro.ltpro.query.FileQuery;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiFriendArticleService;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiMusicService;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiUsersService;
import com.newadmin.demoservice.util.FileTypeUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author couei
 * @since 2024-05-16
 */
@Service
@RequiredArgsConstructor
public class ReaiFriendArticleServiceImpl extends DefaultService implements
    ReaiFriendArticleService {

    public static final String TABLE_NAME = "reai_article";

    private final ReaiUsersService usersService;
    private final ReaiMusicService songService;
    private final ArticleQuery friendArticleQuery;
    private final FileQuery fileQuery;
    private final ChannelQuery channelQuery;

    /**
     * 朋友圈数据列表
     *
     * @param page
     * @param userId
     * @return
     */
    @Override
    public List<ReaiArticle> friendCircleList(Page page, String userId) {
        // 如果用户没有登录，返回所有用户发布的朋友圈数据
        if (Objects.equals(userId, "null")) {
            // 当前未登录获取所有朋友圈数据
            List<ReaiArticle> articleList = friendArticleQuery.queryPageFriendArticleList(page,
                null);
            // 获取媒体信息
            getMediaInfo(null, articleList);
            return articleList;
        } else {
            // 获取当前登录用户的朋友圈数据
            List<ReaiArticle> articleList = friendArticleQuery.queryPageFriendArticleList(page,
                userId);
            // 获取媒体信息
            getMediaInfo(userId, articleList);
            return articleList;
        }

    }

    private void getMediaInfo(String userId, List<ReaiArticle> articleList) {
        String[] articleIds = articleList.stream().map(ReaiArticle::getArticleId)
            .toArray(String[]::new);
        // 获取所有文章相关的文件（图片和视频）
        // 为每篇文章设置图片列表
        articleList.forEach(item -> {
            if (StringUtils.isNotBlank(item.getFileId())) {
                String[] ids = item.getFileId().split("_");
                List<FileDO> imgList = fileQuery.fileDOList(ids);
                item.setImgList(imgList);
            } else {
                item.setImgList(new ArrayList<>());
            }
        });

        // 获取所有文章相关的音乐
        List<ReaiMusic> musicList = songService.songListById(null, articleIds);
        // 为每篇文章设置音乐列表
        articleList.forEach(item -> {
            List<ReaiMusic> itemMusicList = musicList.stream()
                .filter(music -> music.getArticleId().equals(item.getArticleId()))
                .collect(Collectors.toList());
            item.put("musicList", itemMusicList);
        });

        // 为每篇文章设置视频列表
        articleList.forEach(item -> {
            if (StringUtils.isNotBlank(item.getFileId())) {
                String[] ids = item.getFileId().split("_");
                List<FileDO> fileList = fileQuery.fileDOList(ids);
                List<String> videoList = fileList.stream()
                    .map(FileDO::getUrl)
                    .filter(url -> "video".equals(FileTypeUtil.fileTypeCheck(url)))
                    .collect(Collectors.toList());
                item.put("videoList", videoList);
            } else {
                item.put("videoList", new ArrayList<>());
            }
        });

        // 获取点赞信息（注释掉的代码，启用时根据需要取消注释）
//    List<ReaiLikes> likesByIds = likesService.getLikesByIds(userId, idList.toString());
//    articleList.forEach(item -> {
//        List<ReaiLikes> itemLikesList = likesByIds.stream()
//            .filter(likes -> likes.getArticleId().equals(item.getArticleId()))
//            .collect(Collectors.toList());
//        item.put("likesList", itemLikesList);
//    });
    }

    /**
     * 根据用户id查询
     *
     * @param uid       用户id
     * @param articleId 文章id
     * @return List<ReaiLikes>
     */
    public List<ReaiLikes> getLikesByIds(String uid, String articleId) {
        //根据用户id查询
        ValueMap params = new ValueMap();
        params.put(ReaiLikes.USER_ID, uid);
        params.put(ReaiLikes.ARTICLE_ID, articleId);
        SelectBuilder selectBuilder = new SelectBuilder(params);
        selectBuilder.from("", super.getEntityDef(ReaiLikesServiceImpl.TABLE_NAME))
            .where()
            .and("user_id", ConditionType.EQUALS, ReaiLikes.USER_ID)
            .and("article_id", ConditionType.EQUALS, ReaiLikes.ARTICLE_ID);
        return super.listForBean(selectBuilder.build(), ReaiLikes::new);
    }

    /**
     * 删除点赞
     *
     * @param articleId 文章id
     */
    public void deleteLikes(String[] articleId) {
        super.delete(ReaiLikesServiceImpl.TABLE_NAME, ReaiLikes.ARTICLE_ID, articleId);
    }

    @Override
    public boolean deleteArticle(String id) {
        // 根据文章ID获取相关的音乐列表
        List<ReaiMusic> musicList = songService.songListById(null, new String[]{id});

        // 根据文章ID获取相关的点赞信息
        List<ReaiLikes> likesByIdsList = getLikesByIds(null, id);

        // 如果存在点赞信息，进行删除
        if (!likesByIdsList.isEmpty()) {
            // 提取所有点赞记录中的文章ID并去重
            String[] articleIdsArray = likesByIdsList.stream()
                .map(ReaiLikes::getArticleId)
                .distinct()
                .toArray(String[]::new);

            // 删除所有关联的点赞信息
            deleteLikes(articleIdsArray);
        }

        // 如果存在关联的音乐记录，进行删除
        if (!musicList.isEmpty()) {
            // 删除所有关联的音乐记录
            musicList.stream()
                .map(ReaiMusic::getSid)
                .forEach(songService::deleteSong);
        }

        // 删除文章记录
        super.delete(TABLE_NAME, ReaiArticle.ARTICLE_ID, new String[]{id});

        // 返回删除操作的结果
        return true;
    }

}

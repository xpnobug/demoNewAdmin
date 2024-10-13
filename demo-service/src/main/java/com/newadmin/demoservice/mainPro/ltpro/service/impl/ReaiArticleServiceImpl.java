package com.newadmin.demoservice.mainPro.ltpro.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.newadmin.democonfig.redisCommon.constant.CacheConstants;
import com.newadmin.democonfig.redisCommon.util.RedisUtils;
import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.democore.kduck.utils.ConversionUtils;
import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.demoservice.mainPro.filepro.entity.FileDO;
import com.newadmin.demoservice.mainPro.filepro.util.FileUploadUtils;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiArticle;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiChannel;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiLikes;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiMusic;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiUsers;
import com.newadmin.demoservice.mainPro.ltpro.query.ArticleQuery;
import com.newadmin.demoservice.mainPro.ltpro.query.ChannelQuery;
import com.newadmin.demoservice.mainPro.ltpro.query.FileQuery;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiArticleService;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiMusicService;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiUsersService;
import com.newadmin.demoservice.mainPro.ltpro.vo.ReaiArticleList;
import com.newadmin.demoservice.mainPro.ltpro.vo.ReaiArticleParamVo;
import com.newadmin.demoservice.mainPro.ltpro.vo.ReaiUsersParamVo;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
public class ReaiArticleServiceImpl extends DefaultService implements ReaiArticleService {

    public static final String TABLE_NAME = "reai_article";

    private final ReaiUsersService usersService;
    private final ReaiMusicService songService;
    private final ArticleQuery articleQuery;
    private final FileQuery fileQuery;
    private final ChannelQuery channelQuery;

    /**
     * 首页展示的各版块文章列表
     *
     * @return
     */
    @Override
    public List<ReaiArticleList> getArticleList() {
        // 尝试从 Redis 缓存中获取数据
        List<ReaiArticle> infolist = RedisUtils.get(CacheConstants.HOME_ARTICLE_LIST_KEY);
        // 如果缓存中有数据，直接返回处理结果
        if (infolist != null && !infolist.isEmpty()) {
            return buildArticleList(infolist);
        }
        // 如果缓存中没有数据，从数据库中查询
        infolist = articleQuery.queryArticleAll();
        // 如果查询结果不为空，将数据存入 Redis，设置过期时间为10分钟
        if (infolist != null && !infolist.isEmpty()) {
            RedisUtils.set(CacheConstants.HOME_ARTICLE_LIST_KEY, infolist, Duration.ofMinutes(10));
        }
        // 构建并返回文章列表
        return buildArticleList(infolist);
    }


    /**
     * 根据用户id获取文章数据(个人信息)
     * @param userId
     * @return
     */
    @Override
    public List<ReaiArticle> getArticleByUserId(Page page, String userId) {
        // 根据用户id 获取 文章列表
        List<ReaiArticle> articleList = articleQuery.queryArticleListByUserId(page, userId);
        articleList.forEach(article -> {
            if (StringUtils.isNotBlank(article.getFileId())) {
                String[] ids = article.getFileId().split("_");
                List<FileDO> files = fileQuery.fileDOList(ids);
                // 设置封面图片
                article.put("coverImg", files.get(0));
                // 设置图片列表
                article.put("imgList", files);
            }
            if (StringUtils.isNotBlank(article.getChannelId())) {
                // 获取文章版块信息
                ReaiChannel reaiChannel = channelQuery.queryChannel(article.getChannelId());
                if (reaiChannel != null) {
                    article.put("name", reaiChannel.getName());
                    article.put("type", reaiChannel.getType());
                } else {
                    article.put("name", "推荐");
                }
            } else {
                article.put("name", "推荐");
            }
        });
        return articleList;
    }

    /**
     * 新增数据(发布文章)
     *
     * @param article
     * @return
     */
    @Override
    @Transactional
    public boolean addArticle(ReaiArticleParamVo article) {
        //获取当前登录的用户信息
        String userId = StpUtil.getLoginIdAsString();
        Assert.notNull(userId, "用户未登录");
        if (article.getArticleId() == null || Objects.equals(article.getArticleId(), "")) {
            // 根据前端传的值添加
            article.setPublishDate(new Date());
            // 设置图片
            article.setFileId(FileUploadUtils.getFileIds(article.getImgList()));

            // 设置文章状态
            ReaiArticle convert = new ReaiArticle();
            // 设置主图
            if (StringUtils.isNotBlank(article.getFileId())) {
                String[] ids = article.getFileId().split("_");
                List<FileDO> file = fileQuery.fileDOList(ids);
                article.setCoverImage(file.get(0).getUrl());
            }
            BeanUtils.copyProperties(article, convert);
            super.add(TABLE_NAME, convert);
        } else {
            ReaiArticle convert = new ReaiArticle();

            //更新
            article.setFileId(FileUploadUtils.getFileIds(article.getImgList()));
            if (StringUtils.isNotBlank(article.getFileId())) {
                String[] ids = article.getFileId().split("_");
                List<FileDO> file = fileQuery.fileDOList(ids);
                article.setCoverImage(file.get(0).getUrl());
            }
            article.setUpdateTime(new Date());
            BeanUtils.copyProperties(article, convert);
            super.update(TABLE_NAME, convert);
        }
        // 删除redis缓存
        RedisUtils.delete(CacheConstants.HOME_ARTICLE_LIST_KEY);

        //返回添加之后的主键
        return true;
    }


    /**
     * 构建文章列表返回结果
     *
     * @param infolist 查询到的文章数据
     * @return 构建好的文章列表
     */
    private List<ReaiArticleList> buildArticleList(List<ReaiArticle> infolist) {
        List<ReaiArticleList> list = new ArrayList<>();

        // 根据发布板块分组, 如果发布板块为空则不加入分组
        Map<String, List<ReaiArticle>> listMap = infolist.stream()
            .filter(item -> item.getChannelId() != null && item.getType() != null)
            .collect(Collectors.groupingBy(ReaiArticle::getType));

        // 将文章分组信息存放到 ReaiArticleList
        listMap.forEach((category, itemsInCategory) -> {
            ReaiArticleList articleList = new ReaiArticleList();
            articleList.put(ReaiArticleList.TYPE_NAME, category);
            articleList.put(ReaiArticleList.ARTICLE_LIST, itemsInCategory);
            list.add(articleList);
        });

        return list;
    }

    /**
     * 获取文章详情
     * @param articleId
     * @return
     */
    @Override
    public ReaiArticle getArticleInfo(String articleId) {
        ReaiArticle postInfo = articleQuery.queryArticleInfoById(articleId);
        if (postInfo != null) {
            if (StringUtils.isNotBlank(postInfo.getFileId())) {
                //获取图片
                String[] ids = postInfo.getFileId().split("_");
                List<FileDO> files = fileQuery.fileDOList(ids);
                // 设置封面图片
                postInfo.put("coverImage", files.get(0).getUrl());
                // 设置图片列表
                postInfo.put("imgList", files);
            }
        }
        return postInfo;
    }

    /**
     * 获取社区文章列表(分页)
     * @param page
     * @return
     */
    @Override
    public List<ReaiArticle> selectAllList(Page page) {
        List<ReaiArticle> infolist = articleQuery.queryPageArticleList(page);
        // 设置图片列表
        infolist.forEach(item -> {
            if (StringUtils.isNotBlank(item.getFileId())) {
                String[] ids = item.getFileId().split("_");
                List<FileDO> files = fileQuery.fileDOList(ids);
                item.put("coverImg", files.get(0));
                item.put("imgList", files);
            }
            if (StringUtils.isNotBlank(item.getChannelId())) {
                // 获取文章版块信息
                ReaiChannel reaiChannel = channelQuery.queryChannel(item.getChannelId());
                if (reaiChannel != null) {
                    item.put("name", reaiChannel.getName());
                    item.put("type", reaiChannel.getType());
                } else {
                    item.put("name", "推荐");
                }
            } else {
                item.put("name", "推荐");
            }
        });
        return infolist;
    }

    @Override
    public ReaiArticle updateArticle(ReaiArticle article) {
        ReaiArticle convert = ConversionUtils.convert(article, ReaiArticle.class);
        super.update(TABLE_NAME, convert);
        return article;
    }

    /**
     * 朋友圈数据列表
     *
     * @param page
     * @param userId
     * @return
     */
    @Override
    public List<ReaiArticle> friendCircleList(Page page, String userId) {
        //如果用户没有登录，返回所有用户发布的朋友圈数据
        if (Objects.equals(userId, "null") || userId == null) {
            List<ReaiArticle> articleList = friendArticleList(page, null);
            //筛选发布版块为朋友圈的文章
            extracted(userId, articleList);
            return articleList;
        } else {
            List<ReaiArticle> articleList = friendArticleList(page, userId);
            extracted(userId, articleList);
            return articleList;
        }
    }


    private void extracted(String userId, List<ReaiArticle> articleList) {
        if (!Objects.equals(userId, "null") && userId != null) {
            //获取用户id
            ReaiUsers users = usersService.getUserById(userId);
            List<String> idList = new ArrayList<>();

            //获取用户信息
            articleList.forEach(item -> {
                idList.add(item.getArticleId());
                item.setAuthor(users.getNickName());
                item.setAvatar(users.getAvatar());
                item.setExp(users.getExp());
                item.setLevel(users.getLevel());
            });
            getMediaInfo(userId, articleList, idList);
        }
        List<String> articleIds = new ArrayList<>();
        Set<String> userIds = new HashSet<>();

        // 填充 userIds 和 articleIds
        articleList.forEach(item -> {
            userIds.add(item.getUserId());
            articleIds.add(item.getArticleId());
        });

        // 使用 userIds 获取用户信息
        List<ReaiUsersParamVo> usersList = usersService.usersListById(new ArrayList<>(userIds));

        // 创建 userId 到用户信息的映射
        Map<String, ReaiUsersParamVo> userMap = usersList.stream()
            .collect(Collectors.toMap(ReaiUsersParamVo::getUserId, Function.identity()));

        // 填充文章的作者信息
        articleList.forEach(item -> {
            ReaiUsersParamVo users = userMap.get(item.getUserId());
            if (users != null) {
                item.setAuthor(users.getNickName());
                item.setAvatar(users.getAvatar());
                item.setExp(users.getExp());
                item.setLevel(users.getLevel());
            }
        });
        //获取媒体信息
        getMediaInfo(userId, articleList, articleIds);
    }

    private void getMediaInfo(String userId, List<ReaiArticle> articleList, List<String> idList) {
        // 获取所有文章相关的文件（图片和视频）
//        List<File> fileList = fileService.getFileListById(idList);
//
//        // 为每篇文章设置图片列表
//        articleList.forEach(item -> {
//            List<String> imgList = fileList.stream()
//                .filter(file -> file.getArticleId().equals(item.getArticleId())
//                    && "image".equals(file.getFileType()))
//                .map(File::getSrc)
//                .collect(Collectors.toList());
//            item.setImgList(imgList);
//        });

        // 获取所有文章相关的音乐
        List<ReaiMusic> musicList = songService.songListById(null, idList);

        // 为每篇文章设置音乐列表
        articleList.forEach(item -> {
            List<ReaiMusic> itemMusicList = musicList.stream()
                .filter(music -> music.getArticleId().equals(item.getArticleId()))
                .collect(Collectors.toList());
            item.put("musicList", itemMusicList);
        });

        // 为每篇文章设置视频列表
//        articleList.forEach(item -> {
//            List<String> videoList = fileList.stream()
//                .filter(file -> file.getArticleId().equals(item.getArticleId())
//                    && "video".equals(file.getFileType()))
//                .map(File::getSrc)
//                .collect(Collectors.toList());
//            item.put("videoList", videoList);
//        });

        // 获取点赞信息（注释掉的代码，启用时根据需要取消注释）
//    List<ReaiLikes> likesByIds = likesService.getLikesByIds(userId, idList.toString());
//    articleList.forEach(item -> {
//        List<ReaiLikes> itemLikesList = likesByIds.stream()
//            .filter(likes -> likes.getArticleId().equals(item.getArticleId()))
//            .collect(Collectors.toList());
//        item.put("likesList", itemLikesList);
//    });
    }

    @Override
    public List<ReaiArticle> friendArticleList(Page page, String userId) {
        ValueMap param = new ValueMap();
        param.put(ReaiArticle.USER_ID, userId);
        param.put(ReaiArticle.CHANNEL_ID, "friendCircle");
        SelectBuilder selectBuilder = new SelectBuilder(param);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
            .and("user_id", ConditionType.EQUALS, ReaiArticle.USER_ID)
            .and("channel_id", ConditionType.EQUALS, ReaiArticle.CHANNEL_ID)
            .orderBy().desc("publish_date");
        return super.listForBean(selectBuilder.build(), page, ReaiArticle::new);
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
        List<ReaiMusic> musicList = songService.songListById(null, Collections.singletonList(id));

        // 根据文章ID获取相关的文件列表
//        List<File> fileList = fileService.getFileListById(Collections.singletonList(id));

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

        // 如果存在关联的文件记录，进行删除
//        if (!fileList.isEmpty()) {
//            // 删除所有关联的文件记录
//            fileList.stream()
//                .map(File::getId)
//                .forEach(fileService::delectFile);
//        }

        // 删除文章记录
        super.delete(TABLE_NAME, ReaiArticle.ARTICLE_ID, new String[]{id});

        // 返回删除操作的结果
        return true;
    }

}

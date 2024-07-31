package com.newadmin.demoservice.mainPro.ltpro.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.newadmin.democore.kduck.id.IdGenerator;
import com.newadmin.democore.kduck.query.QuerySupport;
import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ParamMap;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.democore.kduck.utils.ConversionUtils;
import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiArticle;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiLikes;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiMusic;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiUsers;
import com.newadmin.demoservice.mainPro.ltpro.query.ArticleQuery;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiArticleService;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiMusicService;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiUsersService;
import com.newadmin.demoservice.mainPro.ltpro.vo.ReaiArticleList;
import com.newadmin.demoservice.mainPro.nas.entity.File;
import com.newadmin.demoservice.mainPro.nas.service.FileService;
import com.newadmin.demoservice.util.FileTypeUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
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

    private final IdGenerator idGenerator;
    private final ReaiUsersService usersService;
    private final FileService fileService;
    private final ReaiMusicService songService;

    @Override
    public List<ReaiArticle> articleList(String userId) {
        Map<String, Object> paramMap = ParamMap.create("userId", userId)
            .set("publishPlatform", "friendCircle").
            toMap();
        QuerySupport query = super.getQuery(ArticleQuery.class, paramMap);
        List<ReaiArticle> infolist = super.listForBean(query, ReaiArticle::new);
        return infolist;
    }

    @Override
    public List<ReaiArticleList> getArticleList() {
        // 获取文章列表
        QuerySupport query = super.getQuery(ArticleQuery.class, null);
        List<ReaiArticle> infolist = super.listForBean(query, ReaiArticle::new);

        List<ReaiArticleList> list = new ArrayList<>();
        // 根据发布平台对文章进行分组,并根据发布时间进行降序排序
        infolist.sort((o1, o2) -> o2.getPublishDate().compareTo(o1.getPublishDate()));

        //获取用户id
        //筛选出每个文章中的userid,存放在一个数组中,去重
        List<String> userIdList = infolist.stream().map(ReaiArticle::getUserId).distinct()
            .collect(Collectors.toList());
        //根据userid获取用户信息

        List<ReaiUsers> usersList = usersService.usersListById(userIdList);

        //根据发布板块分组,如果发布板块为空不加入分组
        Map<String, List<ReaiArticle>> listMap = infolist.stream()
            .filter(item -> item.getPublishPlatform() != null)
            .collect(Collectors.groupingBy(ReaiArticle::getPublishPlatform));

        // 遍历分组后的文章列表
        //将用户信息存放在文章中
        listMap.forEach((category, itemsInCategory) -> {
            ReaiArticleList articleList = new ReaiArticleList();
            articleList.put(ReaiArticleList.TYPE_NAME, category);
            //获取用户id
            itemsInCategory.forEach(users -> {
                //获取用户信息
                ReaiUsers user = usersList.stream()
                    .filter(item -> item.getUserId().equals(users.getUserId()))
                    .findFirst().orElse(null);
                if (user != null) {
                    users.setAuthor(user.getUsername());
                    users.setAvatar(user.getAvatar());
                }
            });
            articleList.put(ReaiArticleList.ARTICLE_LIST, itemsInCategory);
            list.add(articleList);
        });
        return list;
    }

    @Override
    @Transactional
    public ReaiArticle addArticle(ReaiArticle article) {
        //获取当前登录的用户信息
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        Assert.notNull(tokenInfo.loginId, "用户未登录");
        if (article.getArticleId() == null) {
            //根据前端传的值添加
            article.setPublishDate(new Date());
            ReaiArticle convert = ConversionUtils.convert(article, ReaiArticle.class);
            String aid = super.add(TABLE_NAME, convert).toString();
            //添加图片
            List<String> imgList = article.getImgList();
            String userId = tokenInfo.loginId.toString();
            if (imgList != null) {
                for (String imgUrl : imgList) {
                    File file = new File();
                    file.setSrc(imgUrl);
                    file.setUpdatedAt(new Date());
                    file.setCreatedAt(new Date());
                    file.setUserId(Long.valueOf(userId));
                    file.setArticleId(aid);
                    file.setFileType(FileTypeUtil.fileTypeCheck(imgUrl));
                    fileService.addImg(file);
                }
            }
            article.setArticleId(idGenerator.nextId().toString());
        } else {
            //更新
            ReaiArticle convert = ConversionUtils.convert(article, ReaiArticle.class);
            convert.setUpdateTime(new Date());
            super.update(TABLE_NAME, convert);
        }
        //返回添加之后的主键
        return article;
    }

    @Override
    public ReaiArticle getArticleInfo(Serializable id) {
        ReaiArticle article = super.getForBean(TABLE_NAME, "articleId", id.toString(),
            ReaiArticle::new);
        // 获取用户信息
        ReaiUsers user = super.getForBean(ReaiUsersServiceImpl.TABLE_NAME, "userId",
            article.getUserId(),
            ReaiUsers::new);
        article.setAuthor(user.getUsername());
        article.setAvatar(user.getAvatar());
        article.setExp(user.getExp());
        //获取图片
        List<File> fileList = fileService.getFileList(article.getArticleId(), null);

        article.setImgList(fileList.stream().map(File::getSrc).collect(Collectors.toList()));
        // 获取评论列表

        // 获取点赞列表

        // 获取收藏列表

        // 获取阅读量

        // 获取评论量

        // 获取点赞量

        // 获取收藏量

        return article;
    }

    @Override
    public List<ReaiArticle> getArticleByUserId(String id) {
        //根据用户id
        List<ReaiArticle> articleList = articleList(id);

        //根据发布时间排序
        articleList.sort((o1, o2) -> o2.getPublishDate().compareTo(o1.getPublishDate()));
        List<ReaiArticle> collect = articleList.stream().filter(item -> item.getUserId().equals(id))
            .collect(Collectors.toList());
        //获取用户id
        extracted(id, collect);
        return collect;
    }

    @Override
    public List<ReaiArticle> selectAllList(Page page) {
        //根据发布时间排序
        List<ReaiArticle> reaiArticles = articleList(null);
        reaiArticles.sort((o1, o2) -> o2.getPublishDate().compareTo(o1.getPublishDate()));

        //获取用户id
        //筛选出每个文章中的userid,存放在一个数组中,去重
        List<String> userIdList = reaiArticles.stream().map(ReaiArticle::getUserId).distinct()
            .collect(Collectors.toList());

        //根据userid获取用户信息
        List<ReaiUsers> usersList = usersService.usersListById(userIdList);

        List<String> idList = new ArrayList<>();
        //将用户信息存放在文章中
        reaiArticles.forEach(item -> {
            //获取用户信息
            ReaiUsers user = usersList.stream().filter(a -> a.getUserId().equals(item.getUserId()))
                .findFirst().orElse(null);
            //获取图片
            idList.add(item.getArticleId());
            if (user != null && user.getUserId().equals(item.getUserId())) {
                item.setAuthor(user.getUsername());
                item.setAvatar(user.getAvatar());
                item.setExp(user.getExp());
                item.setLevel(user.getLevel());

            }
        });
        List<File> fileList = fileService.getFileListById(idList);
        reaiArticles.forEach(item -> {
            //判断文章id 和文件中的id是否一致
            item.setImgList(
                fileList.stream().filter(file -> file.getArticleId().equals(item.getArticleId()))
                    .map(File::getSrc).collect(Collectors.toList()));
        });

        page.setCount(reaiArticles.size());
        return reaiArticles.stream()
            .skip((long) (page.getCurrentPage() - 1) * page.getPageSize()).limit(page.getPageSize())
            .toList();
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
        }
        List<ReaiArticle> articleList = friendArticleList(page, userId);
        extracted(userId, articleList);
        return articleList;
    }

    @NotNull
    private List<ReaiArticle> getList(Page page, String userId,
        List<ReaiArticle> articleList) {
        //查询发布平台为 friendCircle：朋友圈 的数据
        //筛选发布版块为朋友圈的文章
        List<ReaiArticle> filterAfterList = articleList.stream().filter(
                item -> item.getPublishPlatform() != null && item.getPublishPlatform()
                    .equals("friendCircle"))
            .toList();
        //如果过滤后的列表为空，则修改page
        if (filterAfterList.isEmpty()) {
            page.setCount(0);
            page.setMaxPage(0);
        }
        extracted(userId, filterAfterList);
        return filterAfterList;
    }

    private void extracted(String userId, List<ReaiArticle> articleList) {
        if (!Objects.equals(userId, "null")) {
            //获取用户id
            ReaiUsers users = usersService.getUserById(userId);
            List<String> idList = new ArrayList<>();

            //获取用户信息
            articleList.forEach(item -> {
                idList.add(item.getArticleId());
                item.setAuthor(users.getUsername());
                item.setAvatar(users.getAvatar());
                item.setExp(users.getExp());
                item.setLevel(users.getLevel());
            });
            getMediaInfo(userId, articleList, idList);
        }
        List<String> articleIds = new ArrayList<>();
        List<String> userIds = new ArrayList<>();
        List<ReaiUsers> usersList = usersService.usersListById(userIds);
        //获取用户信息
        articleList.forEach(item -> {
            userIds.add(item.getUserId());
            articleIds.add(item.getArticleId());
            //获取用户信息
            ReaiUsers users = usersList.stream().filter(
                    user -> user.getUserId().equals(item.getUserId()))
                .findFirst().orElse(null);
            item.setAuthor(users.getUsername());
            item.setAvatar(users.getAvatar());
            item.setExp(users.getExp());
            item.setLevel(users.getLevel());
        });
        //获取媒体信息
        getMediaInfo(userId, articleList, articleIds);

    }

    private void getMediaInfo(String userId, List<ReaiArticle> articleList, List<String> idList) {
        //获取图片
        List<File> fileList = fileService.getFileListById(idList);
        articleList.forEach(item -> {
            //判断文章id 是否一致
            item.setImgList(
                fileList.stream().filter(
                        file -> file.getArticleId().equals(item.getArticleId())
                            && file.getFileType() != null && file.getFileType()
                            .equals("image"))
                    .map(File::getSrc).collect(Collectors.toList()));
        });
        //获取音乐
        List<ReaiMusic> musicList = songService.songListById(null, idList);
        articleList.forEach(item -> {
            //判断文章id 否一致
            item.put("musicList", musicList.stream()
                .filter(music -> music.getArticleId().equals(item.getArticleId())));
        });
        //获取视频
        articleList.forEach(item -> {
            item.put("videoList", fileList.stream().filter(
                    file -> file.getArticleId().equals(item.getArticleId())
                        && file.getFileType() != null && file.getFileType()
                        .equals("video"))
                .map(File::getSrc).collect(Collectors.toList()));
        });

        //获取点赞信息
//        List<ReaiLikes> likesByIds = likesService.getLikesByIds(userId, idList.toString());
//        articleList.forEach(item -> {
//            item.put("likesList", likesByIds.stream().filter(
//                    likes -> likes.getArticleId().equals(item.getArticleId()))
//                .collect(Collectors.toList()));
//        });

    }

    @Override
    public List<ReaiArticle> friendArticleList(Page page, String userId) {
        ValueMap param = new ValueMap();
        param.put(ReaiArticle.USER_ID, userId);
        param.put(ReaiArticle.PUBLISH_PLATFORM, "friendCircle");
        SelectBuilder selectBuilder = new SelectBuilder(param);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
            .and("user_id", ConditionType.EQUALS, ReaiArticle.USER_ID)
            .and("publish_platform", ConditionType.EQUALS, ReaiArticle.PUBLISH_PLATFORM)
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
    public JsonObject deleteArticle(String id) {
        //根据id获取音乐列表
        List<ReaiMusic> musicList = songService.songListById(null, Collections.singletonList(id));
        //根据id获取文件列表
        List<File> fileList = fileService.getFileListById(Collections.singletonList(id));
        //根据id获取点赞信息
        List<ReaiLikes> likesByIdsList = getLikesByIds(null, id);

        if (!likesByIdsList.isEmpty()) {
            // 删除点赞信息
            String[] articleIdsArray = likesByIdsList.stream().distinct()
                .map(ReaiLikes::getArticleId)
                .toArray(String[]::new);
            // 调用 deleteLikes 方法删除点赞信息
            deleteLikes(articleIdsArray);
        }
        //如果音乐列表不为空
        if (!musicList.isEmpty()) {
            //获取id
            musicList.stream().map(ReaiMusic::getSid).forEach(
                songService::deleteSong
            );
        }

        //如果文件列表不为空
        if (!fileList.isEmpty()) {
            //获取id
            fileList.stream().map(File::getId).forEach(
                fileService::delectFile
            );
        }

        //根据id删除文章
        super.delete(TABLE_NAME, ReaiArticle.ARTICLE_ID, new String[]{id});
        return null;
    }

//    @Override
//    public List<ReaiArticle> friendArticleList(Page page, String userId) {
//        ValueMap param = new ValueMap();
//        param.put(ReaiArticle.USER_ID, userId);
//        param.put(ReaiArticle.PUBLISH_PLATFORM, "friendCircle");
//        SelectBuilder selectBuilder = new SelectBuilder(param);
//        selectBuilder.bindFields("a",
//                BeanDefUtils.excludeField(super.getFieldDefList(TABLE_NAME),
//                    "seoInformation", "copyright","summary","source","recommendations","readingTime",
//                    "relatedArticles","editorialSuggestions"))
//            .bindFields("b",
//                BeanDefUtils.includeField(super.getFieldDefList(ReaiLikesServiceImpl.TABLE_NAME),
//                    "guestId"));
//
//        selectBuilder.from("a", super.getEntityDef(TABLE_NAME))
//            .innerJoinOn("b", super.getEntityDef(ReaiLikesServiceImpl.TABLE_NAME),
//                ReaiLikes.ARTICLE_ID)
//            .where()
//            .and("a.user_id", ConditionType.EQUALS, ReaiArticle.USER_ID)
//            .and("a.publish_platform", ConditionType.EQUALS, ReaiArticle.PUBLISH_PLATFORM)
//            .orderBy().desc("a.publish_date");
//        return super.listForBean(selectBuilder.build(), page, ReaiArticle::new);
//    }

}

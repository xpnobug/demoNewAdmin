package com.newadmin.demoservice.mainPro.ltpro.service.impl;

import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiArticle;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiLikes;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiUsers;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiArticleService;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiLikesService;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiUsersService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 存储用户对文章点赞信息的表 服务实现类
 * </p>
 *
 * @author couei
 * @since 2024-06-21
 */
@Service
@RequiredArgsConstructor
public class ReaiLikesServiceImpl extends DefaultService implements ReaiLikesService {

    public static final String TABLE_NAME = "reai_likes";

    public final ReaiArticleService articleService;
    public final ReaiUsersService usersService;

    /**
     * 点赞
     *
     * @param likes
     * @return
     */
    @Override
    public String giveALike(ReaiLikes likes, HttpServletRequest request) {
        // 获取客户端IP和User-Agent，用于生成或识别用户/游客
//        String clientIp = VisitorIdentifier.getClientIp(request);
//        String userAgent = VisitorIdentifier.getUserAgent(request);

        // 结合 IP、User-Agent 和当前日期生成唯一标识符
//        String guestId = UniqueIdGenerator.generateDailyUniqueId(clientIp, userAgent);

        String guestId = likes.getGuestId();
// 设置游客ID或清除（如果是登录用户）
        likes.setGuestId(Objects.equals(likes.getUserId(), "null") ? guestId : null);
        likes.setUserId(Objects.equals(likes.getUserId(), "null") ? null : likes.getUserId());
        likes.setLikedAt(new Date());

        // 检查数据库中是否存在相同的点赞记录
        List<ReaiLikes> existingLikes = getLikesById(likes.getUserId(), likes.getArticleId(),
            likes.getGuestId());

        if (existingLikes.isEmpty()) {
            // 如果没有找到，说明之前没有点赞，直接添加点赞记录
            add(TABLE_NAME, likes);
            return "点赞成功";
        } else {
            for (ReaiLikes existingLike : existingLikes) {
                if (likes.getArticleId().equals(existingLike.getArticleId())) {
                    if (likes.getUserId() != null && likes.getUserId()
                        .equals(existingLike.getUserId())) {
                        // 登录用户的重复点赞，取消点赞
                        delete(TABLE_NAME, new String[]{existingLike.getLikeId()});
                        return "取消点赞成功";
                    } else if (likes.getUserId() == null && guestId.equals(
                        existingLike.getGuestId())) {
                        // 游客的重复点赞，取消点赞
                        delete(TABLE_NAME, new String[]{existingLike.getLikeId()});
                        return "取消点赞成功";
                    }
                }
            }
            // 如果用户已登录并且数据库中没有对应用户的点赞记录，但存在游客的点赞记录，此处添加用户点赞
            if (likes.getUserId() != null) {
                add(TABLE_NAME, likes);
                return "点赞成功";
            }
        }
        return null; // 考虑返回更具体的信息或错误提示
    }

    @Override
    public List<ReaiLikes> getLikesByIds(String uid, String articleId) {
        //根据用户id查询
        ValueMap params = new ValueMap();
        params.put(ReaiLikes.USER_ID, uid);
        params.put(ReaiLikes.ARTICLE_ID, articleId);
        SelectBuilder selectBuilder = new SelectBuilder(params);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
            .and("user_id", ConditionType.EQUALS, ReaiLikes.USER_ID)
            .and("article_id", ConditionType.EQUALS, ReaiLikes.ARTICLE_ID);
        return super.listForBean(selectBuilder.build(), ReaiLikes::new);
    }

    @Override
    public List<ReaiLikes> getLikesByIds(String uid, String[] articleId) {
        //根据用户id查询
        ValueMap params = new ValueMap();
        params.put(ReaiLikes.USER_ID, uid);
        params.put(ReaiLikes.ARTICLE_ID, articleId);
        SelectBuilder selectBuilder = new SelectBuilder(params);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
            .and("user_id", ConditionType.EQUALS, ReaiLikes.USER_ID)
            .and("article_id", ConditionType.EQUALS, ReaiLikes.ARTICLE_ID);
        return super.listForBean(selectBuilder.build(), ReaiLikes::new);
    }

    public List<ReaiLikes> getLikesById(String uid, String articleId, String guestId) {
        // 定义 SQL 查询，考虑了用户ID和游客ID
        String sql = "SELECT like_id, article_id, user_id, guest_id, liked_at "
            + "FROM reai_likes "
            + "WHERE article_id ='" + articleId + "'"
            + "AND ((user_id = '" + uid + "' AND user_id IS NOT NULL) OR (guest_id = '" + guestId
            + "' AND guest_id IS NOT NULL))";

        ValueMap params = new ValueMap();
        params.put(ReaiLikes.ARTICLE_ID, articleId);
        if (uid != null) {
            params.put(ReaiLikes.USER_ID, uid); // 仅设置用户ID如果非空
        }
        params.put(ReaiLikes.GUEST_ID, guestId); // 设置游客ID

        SelectBuilder selectBuilder = new SelectBuilder(sql, params);

        return listForBean(selectBuilder.build(), ReaiLikes::new);
    }

    @Override
    public List<ReaiLikes> getUserInfoAndLikesList(String userId, String articleId) {
        //获取点赞列表
        List<ReaiLikes> list = getLikesByIds(userId, articleId);

        //获取文章信息
        List<ReaiArticle> articleList = articleService.friendArticleList(null, userId);
        List<String> userIds = new ArrayList<>();
        //获取用户信息
        articleList.forEach(item -> {
            userIds.add(item.getUserId());
        });
        List<ReaiUsers> usersList = usersService.usersListById(userIds);

        list.forEach(item -> {
            //获取用户信息
            ReaiUsers users = usersList.stream()
                .filter(user -> user.getUserId().equals(item.getUserId())).findFirst().orElse(null);
            if (users != null) {
                item.put("userName", users.getUsername());
            } else {
                item.put("userName", "访客");
            }
        });

        return list;
    }
}

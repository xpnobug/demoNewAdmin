package com.newadmin.demoservice.mainPro.ltpro.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.newadmin.democommon.id.IdGenerator;
import com.newadmin.democommon.query.QuerySupport;
import com.newadmin.democommon.service.DefaultService;
import com.newadmin.democommon.service.ParamMap;
import com.newadmin.democommon.service.ValueMap;
import com.newadmin.democommon.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democommon.sqlbuild.SelectBuilder;
import com.newadmin.democommon.utils.Page;
import com.newadmin.demoservice.mainPro.ltpro.auth.model.resp.UserInfoResp;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiFollow;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiUsers;
import com.newadmin.demoservice.mainPro.ltpro.query.UserQuery;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiArticleService;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiFollowService;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiLogService;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiUsersService;
import com.newadmin.demoservice.mainPro.ltpro.vo.Statistics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author couei
 * @since 2024-05-12
 */
@Service
@RequiredArgsConstructor
public class ReaiUsersServiceImpl extends DefaultService implements
    ReaiUsersService {

    public static final String TABLE_NAME = "reai_users";
    private final IdGenerator idGenerator;
    private final ReaiLogService logService;
    private final ReaiFollowService followService;

    //解决循环依赖
    @Autowired
    @Lazy
    private ReaiArticleService articleService;

    @Override
    public ReaiUsers usersById(String id) {
        if (Objects.equals(id, "null")) {
            return null;
        } else {
            Map<String, Object> paramMap = ParamMap.create("userId", id).toMap();
            QuerySupport query = super.getQuery(UserQuery.class, paramMap);
            return super.getForBean(query, null, ReaiUsers::new);
        }
    }

    @Override
    public List<ReaiUsers> usersListById(List<String> ids) {
        Map<String, Object> paramMap = ParamMap.create("userId", ids).toMap();
        QuerySupport query = super.getQuery(UserQuery.class, paramMap);
        return super.listForBean(query, null, ReaiUsers::new);
    }

    @Override
    public List<ReaiUsers> usersList(Page page) {
        QuerySupport query = super.getQuery(UserQuery.class, null);
        return super.listForBean(query, page, ReaiUsers::new);
    }

    /**
     */
    @Override
    public ReaiUsers register(ReaiUsers user) {

        // 获取前端传的账号密码
//        String username = user.getUsername();
//        String password = user.getPassword();
//        String email = user.getEmail();

        // 调用数据库查询该用户是否存在
//        ReaiUsers existUser = super.getForBean(TABLE_NAME, "username", username, ReaiUsers::new);
//        ReaiUsers existEmail = super.getForBean(TABLE_NAME, "email", email, ReaiUsers::new);
//
//        // 如果用户存在，返回null
//        if (existUser != null || existEmail != null) {
//            return null;
//        }
        //todo 注册时需要验证邮箱

        // 如果不存在，则将用户信息插入数据库，并返回插入的用户信息
        // 密码加密
        try {
//            String pwd = EncryptionUtil.encryptWithAES(password, password);
//            user.setPassword(pwd);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //默认背景
        user.setUserCover(
            "https://alist.reaicc.com/nas/image/jpeg/2024-05/1/fb02f075-42bb-4e0a-bb0a-dafc1032e4b6.jpg");

        //默认头像
        if (user.getAvatar() == null) {
            user.setAvatar(
                "https://alist.reaicc.com/nas/image/jpeg/2024-05/1/51c82bda-f7bf-422c-a8d1-fac48e863140.jpg");
        }
        // 设置用户等级
        //1级  1-20
        //2级 21-50
        //3级 51-100
        //4级 101-200
        //5级 201-400
        //6级 401-700
        //7级 701-1000
        //8级 1001-1500
        //9级 1501-2000
        //10级 2001-3000
        //11级 2501-3000
        //12级 3001-4000
        //13级 4001-5000
        //14级 5001-6500
        //15级 6501-8000
        //16级 8001-10000
        //17级 10001-12000
        //18级 12001-15000
        //19级 15001-20000
        //20级 20001-99999+
        user.setLevel(1);
        user.setExp("1");
        user.setRegistrationTime(new Date());
        user.setUserType("普通用户");
        user.setStatus("正常");
        super.add(TABLE_NAME, user);
        return user;
    }

    @Override
    public ReaiUsers updateUserInfo(ReaiUsers user) {
        // 更新用户信息
        ReaiUsers userInfo = new ReaiUsers();
        userInfo.setUserId(user.getUserId());
        if (!user.getUsername().isEmpty()) {
            userInfo.setUsername(user.getUsername());
        }

        if (!user.getEmail().isEmpty()) {
            userInfo.setEmail(user.getEmail());
        }

        if (!user.getAvatar().isEmpty()) {
            userInfo.setAvatar(user.getAvatar());
        }

        if (!user.getUserCover().isEmpty()) {
            userInfo.setUserCover(user.getUserCover());
        }
        super.update(TABLE_NAME, userInfo);
        return user;
    }

    @Override
    public List<ReaiUsers> pageList(Page page) {
        //获取所有注册的用户
        List<ReaiUsers> reaiUsers = this.usersList(page);
        //是否登录
        boolean login = StpUtil.isLogin();
        if (login) {
            //获取当前登录的用户信息
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            String userId = tokenInfo.loginId.toString();

            // 获取当前用户关注的用户列表
            List<ReaiFollow> followList = followService.getFollowList(userId, null);

            // 获取统计的数量
            Statistics statistics = this.getStatistics(userId);

            // 排除当前登录的自己
            reaiUsers.removeIf(user -> user.getUserId().equals(userId));
            // 对比reaiUsers 中userid 和 followList 中 followUserId是否一致
            reaiUsers.forEach(user -> {
                // 判断当前用户是否关注了该用户
                boolean isFollow = followList.stream()
                    .anyMatch(follow -> follow.getFollowUserId().equals(user.getUserId()));
                // 设置关注状态
                user.setIsFollow(isFollow);
                user.setStatistics(statistics);
            });
        }
        List<String> ids = reaiUsers.stream().map(ReaiUsers::getUserId).toList();
        reaiUsers.forEach(user -> {
            // 获取统计的数量
            Statistics statistics = this.getStatistics(user.getUserId());
            user.setStatistics(statistics);
        });
        return reaiUsers;
    }

    @Override
    public List<ReaiUsers> getFollowListById(String id) {
        // 获取当前用户关注的用户列表
        List<ReaiFollow> followList = followService.getFollowList(id, null);
        List<ReaiUsers> list = new ArrayList<>();

        for (ReaiFollow reaiFollow : followList) {
            // 获取关注用户的用户信息
            ReaiUsers user = this.getUserById(reaiFollow.getFollowUserId());
            list.add(user);
        }
        // 对比reaiUsers 中userid 和 followList 中 followUserId是否一致
        list.forEach(user -> {
            // 获取统计的数量
            Statistics statistics = this.getStatistics(user.getUserId());
            // 判断当前用户是否关注了该用户
            boolean isFollow = followList.stream()
                .anyMatch(follow -> follow.getFollowUserId().equals(user.getUserId()));
            // 设置关注状态
            user.setIsFollow(isFollow);
            user.setStatistics(statistics);
        });
        return list;
    }

    @Override
    public ReaiUsers getUserById(Serializable id) {
        ReaiUsers reaiUsers = usersById(String.valueOf(id));
        // 获取当前登录的用户信息
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        if (tokenInfo.loginId != null) {
            String userId = tokenInfo.loginId.toString();
            if (!id.equals(userId)) {
                // 获取当前用户关注的用户列表
                List<ReaiFollow> followList = followService.getFollowList(userId, null);
                // 对比reaiUsers 中userid 和 followList 中 followUserId是否一致
                // 判断当前用户是否关注了该用户
                boolean isFollow = followList.stream()
                    .anyMatch(follow -> follow.getFollowUserId().equals(id));
                // 设置关注状态
                reaiUsers.setIsFollow(isFollow);
            }
        }
        return reaiUsers;
    }

    @Override
    public UserInfoResp getUser(Serializable id) {
        ReaiUsers reaiUsers = usersById(String.valueOf(id));
        // 获取当前登录的用户信息
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        if (tokenInfo.loginId != null) {
            String userId = tokenInfo.loginId.toString();
            if (!id.equals(userId)) {
                // 获取当前用户关注的用户列表
                List<ReaiFollow> followList = followService.getFollowList(userId, null);
                // 对比reaiUsers 中userid 和 followList 中 followUserId是否一致
                // 判断当前用户是否关注了该用户
                boolean isFollow = followList.stream()
                    .anyMatch(follow -> follow.getFollowUserId().equals(id));
                // 设置关注状态
                reaiUsers.setIsFollow(isFollow);
            }
        }
        UserInfoResp userInfoResp = new UserInfoResp();
        if (reaiUsers != null) {
            BeanUtils.copyProperties(reaiUsers, userInfoResp);
        }
        return userInfoResp;
    }

    @Override
    public ReaiUsers getByUsername(String username) {
        ValueMap paramMap = new ValueMap();
        paramMap.put(ReaiUsers.USERNAME, username);
        SelectBuilder selectBuilder = new SelectBuilder(paramMap);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME)).where()
            .and("username", ConditionType.EQUALS, ReaiUsers.USERNAME);
        return super.getForBean(selectBuilder.build(), null, ReaiUsers::new);
    }

    @Override
    public ReaiUsers getByPhone(String phone) {
        ValueMap paramMap = new ValueMap();
        paramMap.put(ReaiUsers.PHONE_NUMBER, phone);
        SelectBuilder selectBuilder = new SelectBuilder(paramMap);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME)).where()
            .and("phone_number", ConditionType.EQUALS, ReaiUsers.PHONE_NUMBER);
        return super.getForBean(selectBuilder.build(), null, ReaiUsers::new);
    }

    @Override
    public ReaiUsers getByEmail(String email) {
        ValueMap paramMap = new ValueMap();
        paramMap.put(ReaiUsers.EMAIL, email);
        SelectBuilder selectBuilder = new SelectBuilder(paramMap);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME)).where()
            .and("email", ConditionType.EQUALS, ReaiUsers.EMAIL);
        return super.getForBean(selectBuilder.build(), null, ReaiUsers::new);
    }

    public Statistics getStatistics(String userId) {
        //1.获取用户发布的文章数量
        int articleCount = articleService.articleList(userId).size();
        //2.获取用户关注的用户数量
        int followCount = followService.getFollowList(userId, null).size();
        //3.获取用户的粉丝数量
        int followerCount = followService.getFollowList(null, userId).size();

        //返回统计结果
        Statistics statistics = new Statistics();
        statistics.put(Statistics.PUBLISH_ARTICLE_COUNT, articleCount);
        statistics.put(Statistics.FOLLOW_COUNT, followCount);
        statistics.put(Statistics.FANS_COUNT, followerCount);

        return statistics;
    }

}

package com.newadmin.demoservice.mainPro.ltpro.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.newadmin.democore.kduck.query.QuerySupport;
import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ParamMap;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiFollow;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiUsers;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.query.UserInfoQuery;
import com.newadmin.demoservice.mainPro.ltpro.query.StatisticsQuery;
import com.newadmin.demoservice.mainPro.ltpro.query.UserQuery;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiFollowService;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiUsersService;
import com.newadmin.demoservice.mainPro.ltpro.vo.ReaiUsersParamVo;
import com.newadmin.demoservice.mainPro.ltpro.vo.ReaiUsersVo;
import com.newadmin.demoservice.mainPro.ltpro.vo.Statistics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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
    private final ReaiFollowService followService;
    private final StatisticsQuery statisticsQuery;

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return
     */
    @Override
    public ReaiUsers getByUsername(String username) {
        ValueMap paramMap = new ValueMap();
        paramMap.put(ReaiUsers.USERNAME, username);
        SelectBuilder selectBuilder = new SelectBuilder(paramMap);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME)).where()
            .and("username", ConditionType.EQUALS, ReaiUsers.USERNAME);
        return super.getForBean(selectBuilder.build(), null, ReaiUsers::new);
    }

    /**
     * 根据手机号查询用户
     *
     * @param phone 手机号
     * @return
     */
    @Override
    public ReaiUsers getByPhone(String phone) {
        ValueMap paramMap = new ValueMap();
        paramMap.put(ReaiUsers.PHONE_NUMBER, phone);
        SelectBuilder selectBuilder = new SelectBuilder(paramMap);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME)).where()
            .and("phone_number", ConditionType.EQUALS, ReaiUsers.PHONE_NUMBER);
        return super.getForBean(selectBuilder.build(), null, ReaiUsers::new);
    }

    /**
     * 根据邮箱查询用户
     *
     * @param email 邮箱
     * @return
     */
    @Override
    public ReaiUsers getByEmail(String email) {
        ValueMap paramMap = new ValueMap();
        paramMap.put(ReaiUsers.EMAIL, email);
        SelectBuilder selectBuilder = new SelectBuilder(paramMap);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME)).where()
            .and("email", ConditionType.EQUALS, ReaiUsers.EMAIL);
        return super.getForBean(selectBuilder.build(), null, ReaiUsers::new);
    }

    /**
     * 根据用户id查询用户
     *
     * @param userId
     * @return
     */
    @Override
    public ReaiUsers getUserInfo(String userId) {
        ValueMap paramMap = new ValueMap();
        paramMap.put(ReaiUsers.USER_ID, userId);
        SelectBuilder selectBuilder = new SelectBuilder(paramMap);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME)).where()
            .and("user_id", ConditionType.EQUALS, ReaiUsers.USER_ID);
        return super.getForBean(selectBuilder.build(), null, ReaiUsers::new);
    }

    /**
     * 根据用户id列表查询用户 (列表)
     *
     * @param ids
     * @return
     */
    @Override
    public List<ReaiUsersParamVo> usersListById(List<String> ids) {
        Map<String, Object> paramMap = ParamMap.create("userId", ids).toMap();
        QuerySupport query = super.getQuery(UserQuery.class, paramMap);
        return super.listForBean(query, null, ReaiUsersParamVo::new);
    }

    /**
     * 分页获取用户信息
     *
     * @param page
     * @return
     */
    @Override
    public List<ReaiUsersParamVo> usersListByPage(Page page) {
        QuerySupport query = super.getQuery(UserQuery.class, null);
        return super.listForBean(query, page, ReaiUsersParamVo::new);
    }

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    @Override
    public ReaiUsers register(ReaiUsers user) {
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

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @Override
    public ReaiUsers updateUserInfo(ReaiUsers user) {
        // 更新用户信息
        ReaiUsers userInfo = getUserInfo(user.getUserId());
        if (userInfo != null) {
            userInfo.setAvatar(
                Objects.equals(user.getAvatar(), "") ? userInfo.getAvatar() : user.getAvatar());
            userInfo.setUserCover(
                Objects.equals(user.getUserCover(), "") ? userInfo.getUserCover()
                    : user.getUserCover());
            userInfo.setNickName(
                Objects.equals(user.getNickName(), "") ? userInfo.getNickName()
                    : user.getNickName());
            userInfo.setEmail(
                Objects.equals(user.getEmail(), "") ? userInfo.getEmail() : user.getEmail());
        }
        super.update(TABLE_NAME, ReaiUsers.USER_ID, userInfo);
        return user;
    }

    /**
     * 用户版块列表
     *
     * @param page
     * @return
     */
    @Override
    public List<UserInfoQuery> pageList(Page page) {
        //获取所有注册的用户
        List<ReaiUsersParamVo> userList = usersListByPage(page);
        List<UserInfoQuery> queryList = new ArrayList<>();
        //是否登录
        boolean login = StpUtil.isLogin();
        if (login) {
            //获取当前登录的用户信息
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            String userId = tokenInfo.loginId.toString();

            // 获取当前用户关注的用户列表
            List<ReaiFollow> followList = followService.getFollowList(userId, null, null);

            // 排除当前登录的自己
            userList.removeIf(user -> user.getUserId().equals(userId));
            // 对比reaiUsers 中userid 和 followList 中 followUserId是否一致
            userList.stream().forEach(reaiUser -> {
                UserInfoQuery userInfoQuery = new UserInfoQuery();
                BeanUtils.copyProperties(reaiUser, userInfoQuery);
                queryList.add(userInfoQuery);
            });

            queryList.forEach(user -> {
                // 判断当前用户是否关注了该用户
                boolean isFollow = followList.stream()
                    .anyMatch(follow -> follow.getFollowUserId().equals(user.getUserId()));
                // 获取统计的数量
                Statistics statistics = statisticsQuery.getUserStatistics(user.getUserId());
                // 设置关注状态
                user.setIsFollow(isFollow);
                user.setStatistics(statistics);
            });
        } else {

            userList.stream().forEach(reaiUser -> {
                UserInfoQuery userInfoQuery = new UserInfoQuery();
                BeanUtils.copyProperties(reaiUser, userInfoQuery);
                queryList.add(userInfoQuery);
            });
            queryList.forEach(user -> {
                // 获取统计的数量
                Statistics statistics = statisticsQuery.getUserStatistics(user.getUserId());
                user.setStatistics(statistics);
            });
        }
        return queryList;
    }

    @Override
    public List<ReaiUsers> getFollowListById(String id) {
        // 获取当前用户关注的用户列表
        List<ReaiFollow> followList = followService.getFollowList(id, null, null);
        List<ReaiUsers> list = new ArrayList<>();

        for (ReaiFollow reaiFollow : followList) {
            // 获取关注用户的用户信息
            ReaiUsers user = this.getUserById(reaiFollow.getFollowUserId());
            list.add(user);
        }
        // 对比reaiUsers 中userid 和 followList 中 followUserId是否一致
        list.forEach(user -> {
            // 获取统计的数量
            Statistics statistics = statisticsQuery.getUserStatistics(user.getUserId());
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
        ReaiUsers reaiUsers = getUserInfo(String.valueOf(id));
        // 获取当前登录的用户信息
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        if (tokenInfo.loginId != null) {
            String userId = tokenInfo.loginId.toString();
            if (!id.equals(userId)) {
                // 获取当前用户关注的用户列表
                List<ReaiFollow> followList = followService.getFollowList(userId, null, null);
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
    public ReaiUsersVo getUser(Serializable id) {
        ReaiUsers reaiUsers = getUserInfo(String.valueOf(id));
        // 获取当前登录的用户信息
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        if (tokenInfo.loginId != null) {
            String userId = tokenInfo.loginId.toString();
            if (!id.equals(userId)) {
                // 获取当前用户关注的用户列表
                List<ReaiFollow> followList = followService.getFollowList(userId, null, null);
                // 对比reaiUsers 中userid 和 followList 中 followUserId是否一致
                // 判断当前用户是否关注了该用户
                boolean isFollow = followList.stream()
                    .anyMatch(follow -> follow.getFollowUserId().equals(id));
                // 设置关注状态
                reaiUsers.setIsFollow(isFollow);
            }
        }
        ReaiUsersVo userInfoResp = new ReaiUsersVo();
        if (reaiUsers != null) {
            BeanUtils.copyProperties(reaiUsers, userInfoResp);
        }
        return userInfoResp;
    }

}

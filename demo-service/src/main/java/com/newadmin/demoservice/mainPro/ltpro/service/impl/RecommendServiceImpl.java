package com.newadmin.demoservice.mainPro.ltpro.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiChannel;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiFollow;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiFollowService;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiUsersService;
import com.newadmin.demoservice.mainPro.ltpro.service.RecommendService;
import com.newadmin.demoservice.mainPro.ltpro.vo.ReaiUsersParamVo;
import com.newadmin.demoservice.mainPro.ltpro.vo.ReaiUsersVo;
import com.newadmin.demoservice.mainPro.ltpro.vo.Recommend;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendServiceImpl extends DefaultService implements RecommendService {

    private final ReaiFollowService followService;
    private final ReaiUsersService usersService;

    @Override
    public List<Recommend> showRecommend() {
        List<Recommend> recommendList = new ArrayList<>();
        List<ReaiUsersVo> reaiUsersVoList = new ArrayList<>();
        //是否登录
        boolean login = StpUtil.isLogin();

        //1. 根据用户注册时间查询最近10名创建的用户
        List<ReaiUsersParamVo> list = usersService.usersListByPage(null);
        if (login) {
            //获取当前登录的用户信息
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            String userId = tokenInfo.loginId.toString();

            // 获取当前用户关注的用户列表
            List<ReaiFollow> followByUserIdList = followService.getFollowList(userId, null, null);

            // 根据用户注册时间排序
            list.sort((o1, o2) -> o2.getRegistrationTime().compareTo(o1.getRegistrationTime()));
            // 只返回10条数据
            List<ReaiUsersParamVo> reaiUsersList = new ArrayList<>(list.stream()
                .filter(user -> user.getRegistrationTime() != null).limit(5).toList());
            Recommend recommend = new Recommend();
            recommend.setType("1");
            // 排除当前登录的自己
            reaiUsersList.removeIf(user -> user.getUserId().equals(userId));
            reaiUsersList.forEach(user -> {
                // 判断当前用户是否关注了该用户
                boolean isFollow = followByUserIdList.stream()
                    .anyMatch(follow -> follow.getFollowUserId().equals(user.getUserId()));
                // 设置关注状态
                user.setIsFollow(isFollow);
                ReaiUsersVo reaiUsersVo = BeanUtil.copyProperties(user, ReaiUsersVo.class);
                reaiUsersVoList.add(reaiUsersVo);
            });

            recommend.setUsersVoList(reaiUsersVoList);
            recommendList.add(recommend);

            List<ReaiUsersVo> zshyUsersVoList = new ArrayList<>();
            //2. 根据粉丝量展示最受欢迎的用户
            List<ReaiFollow> followList = followService.getFollowList(null, null, null);
            //根据 followUserId 计数，展示followUserId 和数量
            Map<String, Long> countMap = followList.stream()
                .collect(Collectors.groupingBy(ReaiFollow::getFollowUserId, Collectors.counting()));
            //根据总数 排序，展示最受欢迎的用户
            // 注意：这里假设list中的ReaiUsers的userId都在countMap的键中存在
            List<ReaiUsersParamVo> zshyUsersList = list.stream()
                .filter(user -> countMap.containsKey(user.getUserId())) // 过滤掉不在countMap中的用户
                .sorted(
                    (o1, o2) -> countMap.get(o2.getUserId())
                        .compareTo(countMap.get(o1.getUserId())))
                .limit(5)
                .toList();

            zshyUsersList.forEach(user -> {
                ReaiUsersVo reaiUsersVo = BeanUtil.copyProperties(user, ReaiUsersVo.class);
                reaiUsersVo.setFansCount(countMap.get(user.getUserId()).intValue());
                zshyUsersVoList.add(reaiUsersVo);
            });
            Recommend recommend2 = new Recommend();
            recommend2.setType("2");
            recommend2.setUsersVoList(zshyUsersVoList);
            recommendList.add(recommend2);
            // todo 3. 根据关注量展示最活跃的用户
        } else {
            // 根据用户注册时间排序
            list.sort((o1, o2) -> o2.getRegistrationTime().compareTo(o1.getRegistrationTime()));
            // 只返回10条数据
            List<ReaiUsersParamVo> reaiUsersList = new ArrayList<>(list.stream()
                .filter(user -> user.getRegistrationTime() != null).limit(5).toList());
            Recommend recommend = new Recommend();
            recommend.setType("1");

            reaiUsersList.forEach(user -> {
                // 设置关注状态
                user.setIsFollow(false);
                ReaiUsersVo reaiUsersVo = BeanUtil.copyProperties(user, ReaiUsersVo.class);
                reaiUsersVoList.add(reaiUsersVo);
            });

            recommend.setUsersVoList(reaiUsersVoList);
            recommendList.add(recommend);

            List<ReaiUsersVo> zshyUsersVoList = new ArrayList<>();
            //2. 根据粉丝量展示最受欢迎的用户
            List<ReaiFollow> followList = followService.getFollowList(null, null, null);
            //根据 followUserId 计数，展示followUserId 和数量
            Map<String, Long> countMap = followList.stream()
                .collect(Collectors.groupingBy(ReaiFollow::getFollowUserId, Collectors.counting()));
            //根据总数 排序，展示最受欢迎的用户
            // 注意：这里假设list中的ReaiUsers的userId都在countMap的键中存在
            List<ReaiUsersParamVo> zshyUsersList = list.stream()
                .filter(user -> countMap.containsKey(user.getUserId())) // 过滤掉不在countMap中的用户
                .sorted(
                    (o1, o2) -> countMap.get(o2.getUserId())
                        .compareTo(countMap.get(o1.getUserId())))
                .limit(5)
                .toList();

            zshyUsersList.forEach(user -> {
                ReaiUsersVo reaiUsersVo = BeanUtil.copyProperties(user, ReaiUsersVo.class);
                reaiUsersVo.setFansCount(countMap.get(user.getUserId()).intValue());
                zshyUsersVoList.add(reaiUsersVo);
            });
            Recommend recommend2 = new Recommend();
            recommend2.setType("2");
            recommend2.setUsersVoList(zshyUsersVoList);
            recommendList.add(recommend2);
        }

        return recommendList;
    }

    @Override
    public List<Recommend> showChannel() {
        List<Recommend> recommendList = new ArrayList<>();
        List<ReaiChannel> channelList = new ArrayList<>();
        //最新创建 根据频道创建时间查询最近5个频道
        SelectBuilder selectBuilder = new SelectBuilder();
        selectBuilder.from("", super.getEntityDef("reai_channel"))
            .where().orderBy().desc("create_time");
        List<ReaiChannel> clist = super.listForBean(selectBuilder.build(), ReaiChannel::new);
        List<ReaiChannel> list = clist.stream()
            .filter(c -> c.getCreateTime() != null).limit(5).toList();
        Recommend recommend = new Recommend();
        recommend.setType("1");
        recommend.setChannelList(list);
        recommendList.add(recommend);
        //最受欢迎
        SelectBuilder channelSelect = new SelectBuilder();
        channelSelect.from("", super.getEntityDef(ReaiFollowServiceImpl.TABLE_NAME))
            .where()
            .and("follow_channel_id", ConditionType.IS_NOT_NULL, ReaiFollow.FOLLOW_CHANNEL_ID);
        List<ReaiFollow> followList = super.listForBean(channelSelect.build(), ReaiFollow::new);
        //根据 getFollowChannelId 计数，展示followUserId 和数量
        Map<String, Long> countMap = followList.stream()
            .collect(Collectors.groupingBy(ReaiFollow::getFollowChannelId, Collectors.counting()));
        List<ReaiChannel> channelCountList = clist.stream()
            .filter(c -> countMap.containsKey(c.getChannelId())) // 过滤掉不在countMap中的
            .sorted(
                (o1, o2) -> countMap.get(o2.getChannelId())
                    .compareTo(countMap.get(o1.getChannelId())))
            .limit(5)
            .toList();
        Recommend recommend2 = new Recommend();
        recommend2.setType("2");
        recommend2.setChannelList(channelCountList);
        recommendList.add(recommend2);

        //最近活跃
        return recommendList;
    }
}

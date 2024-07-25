package com.newadmin.demoservice.mainPro.ltpro.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiFollow;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiUsers;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiFollowService;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiUsersService;
import com.newadmin.demoservice.mainPro.ltpro.service.RecommendService;
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
public class RecommendServiceImpl implements
    RecommendService {

    private final ReaiFollowService followService;
    private final ReaiUsersService usersService;

    @Override
    public List<Recommend> showRecommend() {
        List<Recommend> recommendList = new ArrayList<>();
        List<ReaiUsersVo> reaiUsersVoList = new ArrayList<>();
        //是否登录
        boolean login = StpUtil.isLogin();

        //1. 根据用户注册时间查询最近10名创建的用户
        List<ReaiUsers> list = usersService.usersList(null);
        if (login) {
            //获取当前登录的用户信息
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            String userId = tokenInfo.loginId.toString();

            // 获取当前用户关注的用户列表
            List<ReaiFollow> followByUserIdList = followService.getFollowList(userId, null);

            // 根据用户注册时间排序
            list.sort((o1, o2) -> o2.getRegistrationTime().compareTo(o1.getRegistrationTime()));
            // 只返回10条数据
            List<ReaiUsers> reaiUsersList = new ArrayList<>(list.stream()
                .filter(user -> user.getRegistrationTime() != null).limit(10).toList());
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
            List<ReaiFollow> followList = followService.getFollowList(null, null);
            //根据 followUserId 计数，展示followUserId 和数量
            Map<String, Long> countMap = followList.stream()
                .collect(Collectors.groupingBy(ReaiFollow::getFollowUserId, Collectors.counting()));
            //根据总数 排序，展示最受欢迎的用户
            // 注意：这里假设list中的ReaiUsers的userId都在countMap的键中存在
            List<ReaiUsers> zshyUsersList = list.stream()
                .filter(user -> countMap.containsKey(user.getUserId())) // 过滤掉不在countMap中的用户
                .sorted(
                    (o1, o2) -> countMap.get(o2.getUserId())
                        .compareTo(countMap.get(o1.getUserId())))
                .limit(10)
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
            List<ReaiUsers> reaiUsersList = new ArrayList<>(list.stream()
                .filter(user -> user.getRegistrationTime() != null).limit(10).toList());
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
            List<ReaiFollow> followList = followService.getFollowList(null, null);
            //根据 followUserId 计数，展示followUserId 和数量
            Map<String, Long> countMap = followList.stream()
                .collect(Collectors.groupingBy(ReaiFollow::getFollowUserId, Collectors.counting()));
            //根据总数 排序，展示最受欢迎的用户
            // 注意：这里假设list中的ReaiUsers的userId都在countMap的键中存在
            List<ReaiUsers> zshyUsersList = list.stream()
                .filter(user -> countMap.containsKey(user.getUserId())) // 过滤掉不在countMap中的用户
                .sorted(
                    (o1, o2) -> countMap.get(o2.getUserId())
                        .compareTo(countMap.get(o1.getUserId())))
                .limit(10)
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
}

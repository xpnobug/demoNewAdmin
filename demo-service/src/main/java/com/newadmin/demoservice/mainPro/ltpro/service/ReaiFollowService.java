package com.newadmin.demoservice.mainPro.ltpro.service;

import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiFollow;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author couei
 * @since 2024-05-25
 */
public interface ReaiFollowService {

    String add(ReaiFollow reaiFollow);

    String delById(String id);

    String quitFollow(String channelId);

    List<ReaiFollow> getFollowList(String userId, String followId);
}

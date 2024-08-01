package com.newadmin.demoservice.mainPro.ltpro.service;

import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiChannel;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.query.ChannelQuery;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author couei
 * @since 2024-07-27
 */
public interface ReaiChannelService {

    /**
     * 列表
     *
     * @return
     */
    List<ReaiChannel> list(Page page);

    List<ChannelQuery> listQuery(Integer isOfficial);

    Object getById(String id);

    ReaiChannel save(ReaiChannel reaiChannel);

    boolean joinChannel(ReaiChannel channel);

    boolean quitChannel(String id);
}

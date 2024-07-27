package com.newadmin.demoservice.mainPro.ltpro.service;

import com.newadmin.democommon.utils.Page;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiChannel;
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

    Object getById(String id);

    ReaiChannel save(ReaiChannel reaiChannel);
}

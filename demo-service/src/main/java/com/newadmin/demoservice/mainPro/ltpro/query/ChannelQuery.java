package com.newadmin.demoservice.mainPro.ltpro.query;

import com.newadmin.democore.kduck.definition.BeanEntityDef;
import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiChannel;
import com.newadmin.demoservice.mainPro.ltpro.service.impl.ReaiChannelServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ChannelQuery extends DefaultService {

    /**
     * 根据渠道id查询渠道信息
     *
     * @param channelId
     * @return
     */
    public ReaiChannel queryChannel(String channelId) {
        ValueMap params = new ValueMap();
        params.put(ReaiChannel.CHANNEL_ID, channelId);
        // 构建查询构造器
        SelectBuilder selectBuilder = new SelectBuilder(params);
        BeanEntityDef channelBean = super.getEntityDef(ReaiChannelServiceImpl.TABLE_NAME);
        selectBuilder.from("", channelBean)
            .where()
            .and("channel_id", ConditionType.IN, ReaiChannel.CHANNEL_ID)
            .orderBy().desc("create_time");
        return super.getForBean(selectBuilder.build(), ReaiChannel::new);
    }
}

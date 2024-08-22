package com.newadmin.demoservice.mainPro.ltpro.vo;

import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiChannel;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Recommend {

    private String type;

    private List<ReaiUsersVo> usersVoList;

    private List<ReaiChannel> channelList;
}

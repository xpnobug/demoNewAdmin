package com.newadmin.demoservice.mainPro.ltpro.vo;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Recommend {

    private String type;

    private List<ReaiUsersVo> usersVoList;
}

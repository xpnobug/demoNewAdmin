package com.newadmin.demoservice.mainPro.ltpro.entity.model.query;

import com.newadmin.demoservice.mainPro.ltpro.auth.model.resp.UserInfoResp;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class ChannelQuery {

    @Schema(description = "版块id", example = "1")
    private String id;
    @Schema(description = "版块名称", example = "name")
    private String name;

    @Schema(description = "简述", example = "summary")
    private String summary;

    @Schema(description = "标题", example = "title")
    private String title;

    @Schema(description = "logo", example = "logo")
    private String logo;

    @Schema(description = "背景图", example = "background")
    private String background;

    @Schema(description = "成员数", example = "1")
    private Integer memberCount;

    @Schema(description = "内容数", example = "1")
    private Integer postCount;

    @Schema(description = "精华数", example = "1")
    private Integer essenceCount;

    @Schema(description = "是否官方", example = "1")
    private Integer isOfficial;

    @Schema(description = "创建时间", example = "createTime")
    private Date createTime;

    private List<UserInfoResp> userList;

}

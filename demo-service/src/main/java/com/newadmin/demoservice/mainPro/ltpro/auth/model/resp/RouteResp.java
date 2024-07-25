
package com.newadmin.demoservice.mainPro.ltpro.auth.model.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 路由信息
 *
 * @author couei
 * @since 2023/2/26 22:51
 */
@Data
@Schema(description = "路由信息")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouteResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    @Schema(description = "标题", example = "用户管理")
    private String title;

    /**
     * 类型
     */
    @Schema(description = "类型", example = "2")
    private Integer type;

    /**
     * 路由地址
     */
    @Schema(description = "路由地址", example = "/system/user")
    private String path;

    /**
     * 组件名称
     */
    @Schema(description = "组件名称", example = "User")
    private String name;

    /**
     * 组件路径
     */
    @Schema(description = "组件路径", example = "/system/user/index")
    private String component;

    /**
     * 重定向地址
     */
    @Schema(description = "重定向地址")
    private String redirect;

    /**
     * 图标
     */
    @Schema(description = "图标", example = "user")
    private String icon;

    /**
     * 是否外链
     */
    @Schema(description = "是否外链", example = "false")
    private Boolean isExternal;

    /**
     * 是否缓存
     */
    @Schema(description = "是否缓存", example = "false")
    private Boolean isCache;

    /**
     * 是否隐藏
     */
    @Schema(description = "是否隐藏", example = "false")
    private Boolean isHidden;

    /**
     * 权限标识
     */
    @Schema(description = "权限标识", example = "system:user:list")
    private String permission;

    /**
     * 排序
     */
    @Schema(description = "排序", example = "1")
    private Integer sort;

    /**
     * 子路由列表
     */
    @Schema(description = "子路由列表")
    private List<RouteResp> children;
}

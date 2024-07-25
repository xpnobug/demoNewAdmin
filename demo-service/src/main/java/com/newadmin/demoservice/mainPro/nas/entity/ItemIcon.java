package com.newadmin.demoservice.mainPro.nas.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author couei
 * @since 2024-03-31
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("item_icon")
public class ItemIcon implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private String iconJson;

    private String title;

    private String url;

    private String lanUrl;

    private String description;

    private Integer openMethod;

    private Integer sort;

    private Long itemIconGroupId;

    private Long userId;

}

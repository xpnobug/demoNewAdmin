package com.newadmin.demoservice.mainPro.nas.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("user_config")
public class UserConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long userId;

    private String panelJson;

    private String searchEngineJson;

}

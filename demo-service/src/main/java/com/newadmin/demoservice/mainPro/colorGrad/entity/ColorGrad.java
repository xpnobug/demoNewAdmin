package com.newadmin.demoservice.mainPro.colorGrad.entity;

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
 * @since 2024-04-14
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("color_grad")
public class ColorGrad implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "idU")
    private String idU;

    private String content;

    private String css;

    private String freeze;

    private LocalDateTime initDate;

    private String like;

    private String name;

    private String tags;

    private LocalDateTime updateDate;

}

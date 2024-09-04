package com.newadmin.demoservice.mainPro.mai.entity;

import com.newadmin.democonfig.util.excelExport.ExcelName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (ImgMai)表实体类
 *
 * @author couei
 * @since 2024-03-16 23:30:59
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImgMai {

    private String id;

    @ExcelName(name = "头像")
    private String avatar;

    @ExcelName(name = "背景")
    private String card;

    @ExcelName(name = "图片类型")
    private String cateName;

    private Integer cid;

    private String sid;

    private Integer collectionCount;

    private Integer collectionStatus;

    private Integer commentCount;

    private Long createTime;

    private Integer followCount;

    private Integer followStatus;

    private Integer followerCount;

    private Integer followerStatus;

    private String label;

    private Integer likeCount;

    private Integer likeStatus;

    private String memo;

    private String name;

    private Date createdAt;

    private Date updatedAt;

}

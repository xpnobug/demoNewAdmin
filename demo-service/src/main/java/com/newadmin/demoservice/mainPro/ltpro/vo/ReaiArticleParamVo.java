package com.newadmin.demoservice.mainPro.ltpro.vo;

import com.newadmin.demoservice.mainPro.filepro.resp.FileUploadResp;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class ReaiArticleParamVo {

    /**
     * 文章ID
     */
    private String articleId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 版块id
     */
    private String channelId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章作者
     */
    private String author;

    /**
     * 发布日期
     */
    private Date publishDate;

    /**
     * 文章标签
     */
    private String tag;

    /**
     * 主图
     */
    private String coverImage;

    /**
     * 文件列表
     */
    private String fileId;

    private List<FileUploadResp> imgList;

    /**
     * 文章状态 : '草稿','已发布','待审核'
     */
    private String articleStatus;

    /**
     *
     */
    private String publishPlatform;

    /**
     * 修改时间
     */
    private Date updateTime;
}

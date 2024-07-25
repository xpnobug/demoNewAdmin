package com.newadmin.demoservice.mainPro.nas.entity;

import com.newadmin.democommon.service.ValueMap;
import java.util.Date;
import java.util.Map;


/**
 * <p>
 *
 * </p>
 *
 * @author couei
 * @since 2024-03-31
 */

public class File extends ValueMap {

    /***/
    public static final String ID = "id";

    /***/
    public static final String CREATED_AT = "createdAt";

    /***/
    public static final String UPDATED_AT = "updatedAt";

    /***/
    public static final String DELETED_AT = "deletedAt";

    /***/
    public static final String SRC = "src";

    /***/
    public static final String USER_ID = "userId";

    /***/
    public static final String FILE_NAME = "fileName";

    /***/
    public static final String METHOD = "method";

    /***/
    public static final String EXT = "ext";
    /***/
    public static final String ARTICLE_ID = "articleId";

    /**
     * 文件类型
     */
    public static final String FILE_TYPE = "fileType";

    public File() {
    }

    public File(Map<String, Object> map) {
        super(map);
    }

    /**
     * 设置
     *
     * @param id
     */
    public void setId(Long id) {
        super.setValue(ID, id);
    }

    /**
     * 获取
     *
     * @return
     */
    public Long getId() {
        return super.getValueAsLong(ID);
    }

    /**
     * 设置
     *
     * @param createdAt
     */
    public void setCreatedAt(Date createdAt) {
        super.setValue(CREATED_AT, createdAt);
    }

    /**
     * 获取
     *
     * @return
     */
    public Date getCreatedAt() {
        return super.getValueAsDate(CREATED_AT);
    }

    /**
     * 设置
     *
     * @param updatedAt
     */
    public void setUpdatedAt(Date updatedAt) {
        super.setValue(UPDATED_AT, updatedAt);
    }

    /**
     * 获取
     *
     * @return
     */
    public Date getUpdatedAt() {
        return super.getValueAsDate(UPDATED_AT);
    }

    /**
     * 设置
     *
     * @param deletedAt
     */
    public void setDeletedAt(Date deletedAt) {
        super.setValue(DELETED_AT, deletedAt);
    }

    /**
     * 获取
     *
     * @return
     */
    public Date getDeletedAt() {
        return super.getValueAsDate(DELETED_AT);
    }

    /**
     * 设置
     *
     * @param src
     */
    public void setSrc(String src) {
        super.setValue(SRC, src);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getSrc() {
        return super.getValueAsString(SRC);
    }

    /**
     * 设置
     *
     * @param userId
     */
    public void setUserId(Long userId) {
        super.setValue(USER_ID, userId);
    }

    /**
     * 获取
     *
     * @return
     */
    public Long getUserId() {
        return super.getValueAsLong(USER_ID);
    }

    /**
     * 设置
     *
     * @param fileName
     */
    public void setFileName(String fileName) {
        super.setValue(FILE_NAME, fileName);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getFileName() {
        return super.getValueAsString(FILE_NAME);
    }

    /**
     * 设置
     *
     * @param method
     */
    public void setMethod(Long method) {
        super.setValue(METHOD, method);
    }

    /**
     * 获取
     *
     * @return
     */
    public Long getMethod() {
        return super.getValueAsLong(METHOD);
    }

    /**
     * 设置
     *
     * @param ext
     */
    public void setExt(String ext) {
        super.setValue(EXT, ext);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getExt() {
        return super.getValueAsString(EXT);
    }

    /**
     * 设置
     *
     * @param articleId
     */
    public void setArticleId(String articleId) {
        super.setValue(ARTICLE_ID, articleId);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getArticleId() {
        return super.getValueAsString(ARTICLE_ID);
    }

    /**
     * 设置 文件类型
     *
     * @param fileType 文件类型
     */
    public void setFileType(String fileType) {
        super.setValue(FILE_TYPE, fileType);
    }

    /**
     * 获取 文件类型
     *
     * @return 文件类型
     */
    public String getFileType() {
        return super.getValueAsString(FILE_TYPE);
    }
}

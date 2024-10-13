package com.newadmin.demoservice.mainPro.filepro.entity;

import cn.hutool.core.util.StrUtil;
import com.newadmin.democonfig.constant.StringConstants;
import com.newadmin.democonfig.util.StrUtils;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.demoservice.mainPro.filepro.enums.FileTypeEnum;
import java.util.Date;
import java.util.Map;
import org.dromara.x.file.storage.core.FileInfo;

/**
 * 文件实体
 */

public class FileDO extends ValueMap {

    /***/
    public static final String FILE_ID = "fileId";
    /**
     * 名称
     */
    public static final String NAME = "name";

    /**
     * 大小（字节）
     */
    public static final String SIZE = "size";

    /**
     * URL
     */
    public static final String URL = "url";

    /**
     * 扩展名
     */
    public static final String EXTENSION = "extension";

    /**
     * 类型
     */
    public static final String TYPE = "type";

    /**
     * 缩略图大小（字节)
     */
    public static final String THUMBNAIL_SIZE = "thumbnailSize";

    /**
     * 缩略图URL
     */
    public static final String THUMBNAIL_URL = "thumbnailUrl";

    /**
     * 存储 ID
     */
    public static final String STORAGE_ID = "storageId";

    /***/
    public static final String CREATE_TIME = "createTime";
    /***/
    public static final String UPDATE_TIME = "updateTime";
    /***/
    public static final String CREATE_USER = "createUser";

    /***/
    public static final String UPDATE_USER = "updateUser";

    public FileDO() {
    }

    public FileDO(Map<String, Object> map) {
        super(map);
    }

    /**
     * 转换为 X-File-Storage 文件信息对象
     *
     * @param storageCode 存储编码
     * @return X-File-Storage 文件信息对象
     */
    public FileInfo toFileInfo(String storageCode) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setUrl(this.getUrl());
        fileInfo.setSize(this.getSize());
        fileInfo.setFilename(StrUtil.contains(this.getUrl(), StringConstants.SLASH)
            ? StrUtil.subAfter(this.getUrl(), StringConstants.SLASH, true)
            : this.getUrl());
        fileInfo.setOriginalFilename(StrUtils
            .blankToDefault(this.getExtension(), this.getName(),
                ex -> this.getName() + StringConstants.DOT + ex));
        fileInfo.setBasePath(StringConstants.EMPTY);
        fileInfo.setPath(
            StrUtil.subBefore(this.getUrl(), StringConstants.SLASH, true) + StringConstants.SLASH);
        fileInfo.setExt(this.getExtension());
        fileInfo.setPlatform(storageCode);
        fileInfo.setThUrl(this.getThumbnailUrl());
        fileInfo.setThFilename(StrUtil.contains(this.getThumbnailUrl(), StringConstants.SLASH)
            ? StrUtil.subAfter(this.getThumbnailUrl(), StringConstants.SLASH, true)
            : this.getThumbnailUrl());
        fileInfo.setThSize(this.getThumbnailSize());
        return fileInfo;
    }

    /**
     * 设置
     *
     * @param flieId
     */
    public void setFlieId(String flieId) {
        super.setValue(FILE_ID, flieId);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getFlieId() {
        return super.getValueAsString(FILE_ID);
    }

    /**
     * 设置 名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        super.setValue(NAME, name);
    }

    /**
     * 获取 名称
     *
     * @return 名称
     */
    public String getName() {
        return super.getValueAsString(NAME);
    }

    /**
     * 设置 大小（字节）
     *
     * @param size 大小（字节）
     */
    public void setSize(Long size) {
        super.setValue(SIZE, size);
    }

    /**
     * 获取 大小（字节）
     *
     * @return 大小（字节）
     */
    public Long getSize() {
        return super.getValueAsLong(SIZE);
    }

    /**
     * 设置 URL
     *
     * @param url URL
     */
    public void setUrl(String url) {
        super.setValue(URL, url);
    }

    /**
     * 获取 URL
     *
     * @return URL
     */
    public String getUrl() {
        return super.getValueAsString(URL);
    }

    /**
     * 设置 扩展名
     *
     * @param extension 扩展名
     */
    public void setExtension(String extension) {
        super.setValue(EXTENSION, extension);
    }

    /**
     * 获取 扩展名
     *
     * @return 扩展名
     */
    public String getExtension() {
        return super.getValueAsString(EXTENSION);
    }

    /**
     * 设置 类型
     *
     * @param type 类型
     */
    public void setType(FileTypeEnum type) {
        super.setValue(TYPE, type);
    }

    /**
     * 获取 类型
     *
     * @return 类型
     */
    public String getType() {
        return super.getValueAsString(TYPE);
    }

    /**
     * 设置 缩略图大小（字节)
     *
     * @param thumbnailSize 缩略图大小（字节)
     */
    public void setThumbnailSize(Long thumbnailSize) {
        super.setValue(THUMBNAIL_SIZE, thumbnailSize);
    }

    /**
     * 获取 缩略图大小（字节)
     *
     * @return 缩略图大小（字节)
     */
    public Long getThumbnailSize() {
        return super.getValueAsLong(THUMBNAIL_SIZE);
    }

    /**
     * 设置 缩略图URL
     *
     * @param thumbnailUrl 缩略图URL
     */
    public void setThumbnailUrl(String thumbnailUrl) {
        super.setValue(THUMBNAIL_URL, thumbnailUrl);
    }

    /**
     * 获取 缩略图URL
     *
     * @return 缩略图URL
     */
    public String getThumbnailUrl() {
        return super.getValueAsString(THUMBNAIL_URL);
    }

    /**
     * 设置 存储 ID
     *
     * @param storageId 存储 ID
     */
    public void setStorageId(String storageId) {
        super.setValue(STORAGE_ID, storageId);
    }

    /**
     * 获取 存储 ID
     *
     * @return 存储 ID
     */
    public String getStorageId() {
        return super.getValueAsString(STORAGE_ID);
    }

    /**
     * 设置
     *
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        super.setValue(CREATE_TIME, createTime);
    }

    /**
     * 获取
     *
     * @return
     */
    public Date getCreateTime() {
        return super.getValueAsDate(CREATE_TIME);
    }

    /**
     * 设置
     *
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        super.setValue(UPDATE_TIME, updateTime);
    }

    /**
     * 获取
     *
     * @return
     */
    public Date getUpdateTime() {
        return super.getValueAsDate(UPDATE_TIME);
    }

    /**
     * 设置
     *
     * @param createUser
     */
    public void setCreateUser(String createUser) {
        super.setValue(CREATE_USER, createUser);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getCreateUser() {
        return super.getValueAsString(CREATE_USER);
    }

    /**
     * 设置
     *
     * @param updateUser
     */
    public void setUpdateUser(String updateUser) {
        super.setValue(UPDATE_USER, updateUser);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getUpdateUser() {
        return super.getValueAsString(UPDATE_USER);
    }
}

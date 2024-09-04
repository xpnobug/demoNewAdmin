package com.newadmin.demoservice.mainPro.filepro.config.file;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.EscapeUtil;
import cn.hutool.core.util.StrUtil;
import com.newadmin.democonfig.constant.StringConstants;
import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.demoservice.mainPro.filepro.entity.FileDO;
import com.newadmin.demoservice.mainPro.filepro.entity.StorageDO;
import com.newadmin.demoservice.mainPro.filepro.enums.FileTypeEnum;
import com.newadmin.demoservice.mainPro.filepro.service.impl.FileServiceImpl;
import com.newadmin.demoservice.mainPro.filepro.service.impl.StorageServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.recorder.FileRecorder;
import org.dromara.x.file.storage.core.upload.FilePartInfo;
import org.springframework.stereotype.Component;

/**
 * 文件记录实现类
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class FileRecorderImpl extends DefaultService implements FileRecorder {

    @Override
    public boolean save(FileInfo fileInfo) {
        // 获取当前登录用户的ID
        String userId = StpUtil.getLoginIdAsString();
        FileDO file = new FileDO();
        String originalFilename = EscapeUtil.unescape(fileInfo.getOriginalFilename());
        file.setName(StrUtil.contains(originalFilename, StringConstants.DOT)
            ? StrUtil.subBefore(originalFilename, StringConstants.DOT, true)
            : originalFilename);
        file.setUrl(fileInfo.getUrl());
        file.setSize(fileInfo.getSize());
        file.setExtension(fileInfo.getExt());
        file.setType(FileTypeEnum.getByExtension(file.getExtension()));
        file.setThumbnailUrl(fileInfo.getThUrl());
        file.setThumbnailSize(fileInfo.getThSize());
        StorageDO storage = (StorageDO) fileInfo.getAttr()
            .get(ClassUtil.getClassName(StorageDO.class, false));
        file.setStorageId(storage.getStorageId());
        file.setCreateUser(userId);
        file.setCreateTime(fileInfo.getCreateTime());
        file.setUpdateUser(userId);
        file.setUpdateTime(file.getCreateTime());
        super.add(FileServiceImpl.TABLE_NAME, file);
        return true;
    }

    @Override
    public FileInfo getByUrl(String url) {
        FileDO file = this.getFileByUrl(url);
        if (null == file) {
            return null;
        }
        ValueMap param = new ValueMap();
        param.put(StorageDO.STORAGE_ID, file.getStorageId());
        SelectBuilder selectBuilder = new SelectBuilder(param);
        selectBuilder.from("", super.getEntityDef(StorageServiceImpl.TABLE_NAME))
            .where()
            .and("storage_id", ConditionType.EQUALS, StorageDO.STORAGE_ID);
        StorageDO storageDO = super.getForBean(selectBuilder.build(), StorageDO::new);

        return file.toFileInfo(storageDO.getCode());
    }

    @Override
    public boolean delete(String url) {
        FileDO file = this.getFileByUrl(url);
        super.delete(FileServiceImpl.TABLE_NAME, new String[]{file.getFlieId()});
        return true;
    }

    @Override
    public void update(FileInfo fileInfo) {
        /* 不使用分片功能则无需重写 */
    }

    @Override
    public void saveFilePart(FilePartInfo filePartInfo) {
        /* 不使用分片功能则无需重写 */
    }

    @Override
    public void deleteFilePartByUploadId(String s) {
        /* 不使用分片功能则无需重写 */
    }

    /**
     * 根据 URL 查询文件
     *
     * @param url URL
     * @return 文件信息
     */
    private FileDO getFileByUrl(String url) {
        // 创建参数映射对象
        ValueMap param = new ValueMap();

        // 直接根据完全匹配的 URL 进行查询
        param.put(FileDO.URL, url);
        SelectBuilder selectBuilder = new SelectBuilder(param);
        selectBuilder.from("", super.getEntityDef(FileServiceImpl.TABLE_NAME))
            .where()
            .and("url", ConditionType.EQUALS, FileDO.URL);

        // 尝试获取完全匹配的记录
        FileDO file = super.getForBean(selectBuilder.build(), FileDO::new);

        // 如果完全匹配的记录不存在，进行模糊匹配
        if (file == null) {
            // 清空参数映射对象，用于模糊查询
            param.clear();

            // 获取 URL 中最后一个斜杠后的部分
            String urlSuffix = StrUtil.subAfter(url, StringConstants.SLASH, true);

            // 更新参数为模糊查询条件
            param.put(FileDO.URL, urlSuffix);

            // 创建模糊查询的 SelectBuilder 对象
            selectBuilder = new SelectBuilder(param);
            selectBuilder.from("", super.getEntityDef(FileServiceImpl.TABLE_NAME))
                .where()
                .and("url", ConditionType.CONTAINS, FileDO.URL);

            // 尝试获取模糊匹配的记录
            file = super.getForBean(selectBuilder.build(), FileDO::new);
        }

        return file;
    }
}
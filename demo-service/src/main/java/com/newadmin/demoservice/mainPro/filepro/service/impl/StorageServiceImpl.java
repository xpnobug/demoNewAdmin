package com.newadmin.demoservice.mainPro.filepro.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.newadmin.democore.constant.StringConstants;
import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.democore.util.ExceptionUtils;
import com.newadmin.democore.util.validate.CheckUtils;
import com.newadmin.democore.util.validate.ValidationUtils;
import com.newadmin.demoservice.config.util.SecureUtils;
import com.newadmin.demoservice.mainPro.filepro.entity.StorageDO;
import com.newadmin.demoservice.mainPro.filepro.enums.StorageTypeEnum;
import com.newadmin.demoservice.mainPro.filepro.query.StorageQuery;
import com.newadmin.demoservice.mainPro.filepro.req.StorageReq;
import com.newadmin.demoservice.mainPro.filepro.resp.StorageResp;
import com.newadmin.demoservice.mainPro.filepro.service.FileService;
import com.newadmin.demoservice.mainPro.filepro.service.StorageService;
import com.newadmin.demoservice.mainPro.filepro.util.SpringWebUtils;
import com.newadmin.demoservice.mainPro.filepro.util.URLUtils;
import com.newadmin.demoservice.mainPro.ltpro.common.enums.DisEnableStatusEnum;
import jakarta.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.RequiredArgsConstructor;
import org.dromara.x.file.storage.core.FileStorageProperties;
import org.dromara.x.file.storage.core.FileStorageService;
import org.dromara.x.file.storage.core.FileStorageServiceBuilder;
import org.dromara.x.file.storage.core.platform.FileStorage;
import org.springframework.stereotype.Service;

/**
 * 存储业务实现
 */
@Service
@RequiredArgsConstructor
public class StorageServiceImpl extends DefaultService implements StorageService {

    public static final String TABLE_NAME = "reai_storage";

    private final FileStorageService fileStorageService;
    @Resource
    private FileService fileService;

    @Override
    public void beforeAdd(StorageReq req) {
        this.decodeSecretKey(req, null);
        CheckUtils.throwIf(Boolean.TRUE.equals(req.getIsDefault()) && this.isDefaultExists(null),
            "请先取消原有默认存储");
        String code = req.getCode();
        CheckUtils.throwIf(this.isCodeExists(code, null), "新增失败，[{}] 已存在", code);
        this.load(req);
    }

    /**
     * 根据id获取存储服务
     *
     * @param id
     */
    @Override
    public StorageDO getStorageById(String id) {
        ValueMap param = new ValueMap();
        param.put(StorageDO.STORAGE_ID, id);
        SelectBuilder selectBuilder = new SelectBuilder(param);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
            .and("storage_id", ConditionType.EQUALS, StorageDO.STORAGE_ID);
        return super.getForBean(selectBuilder.build(), StorageDO::new);
    }

    @Override
    public List<StorageResp> list(StorageQuery query, Object o) {
        ValueMap param = new ValueMap();
        param.put(StorageDO.DESCRIPTION, query.getDescription());
        param.put(StorageDO.STATUS, query.getStatus());
        SelectBuilder selectBuilder = new SelectBuilder(param);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
            .and("name", ConditionType.CONTAINS, StorageDO.DESCRIPTION)
            .and("code", ConditionType.CONTAINS, StorageDO.DESCRIPTION)
            .and("description", ConditionType.CONTAINS, StorageDO.DESCRIPTION)
            .and("status", ConditionType.EQUALS, StorageDO.STATUS);
        return super.listForBean(selectBuilder.build(), StorageResp::new);
    }

    @Override
    public void beforeUpdate(StorageReq req, String id) {
        StorageDO oldStorage = getStorageById(id);
        CheckUtils.throwIfNotEqual(req.getCode(), oldStorage.getCode(), "不允许修改存储编码");
        CheckUtils.throwIfNotEqual(req.getType(), oldStorage.getType(), "不允许修改存储类型");
        DisEnableStatusEnum newStatus = req.getStatus();
        CheckUtils.throwIf(
            Boolean.TRUE.equals(oldStorage.getIsDefault()) && DisEnableStatusEnum.DISABLE
                .equals(newStatus), "[{}] 是默认存储，不允许禁用", oldStorage.getName());
        this.decodeSecretKey(req, oldStorage);
        DisEnableStatusEnum oldStatus = DisEnableStatusEnum.valueOf(oldStorage.getStatus());
        // 先卸载
        if (DisEnableStatusEnum.ENABLE.equals(oldStatus)) {
            this.unload(BeanUtil.copyProperties(oldStorage, StorageReq.class));
        }
        // 再加载
        if (DisEnableStatusEnum.ENABLE.equals(newStatus)) {
            this.load(req);
        }
        if (Boolean.TRUE.equals(req.getIsDefault())) {
            CheckUtils.throwIf(!DisEnableStatusEnum.ENABLE.equals(oldStatus), "请先启用该存储");
            CheckUtils.throwIf(this.isDefaultExists(id), "请先取消原有默认存储");
        }
    }

    @Override
    public void beforeDelete(List<String> ids) {
        CheckUtils.throwIf(fileService.countByStorageIds(ids) > 0,
            "所选存储存在文件关联，请删除文件后重试");
        ValueMap param = new ValueMap();
        param.put(StorageDO.STORAGE_ID, ids);
        SelectBuilder selectBuilder = new SelectBuilder(param);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
            .and("storage_id", ConditionType.IN, StorageDO.STORAGE_ID);
        List<StorageDO> storageList = super.listForBean(selectBuilder.build(), StorageDO::new);

        storageList.forEach(s -> {
            CheckUtils.throwIfEqual(Boolean.TRUE, s.getIsDefault(), "[{}] 是默认存储，不允许禁用",
                s.getName());
            // 卸载启用状态的存储
            if (DisEnableStatusEnum.ENABLE.equals(s.getStatus())) {
                this.unload(BeanUtil.copyProperties(s, StorageReq.class));
            }
        });
    }

    @Override
    public StorageDO getDefaultStorage() {
        ValueMap param = new ValueMap();
        param.put(StorageDO.IS_DEFAULT, true);
        SelectBuilder selectBuilder = new SelectBuilder(param);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
            .and("is_default", ConditionType.EQUALS, StorageDO.IS_DEFAULT);
        return super.getForBean(selectBuilder.build(), StorageDO::new);
    }

    @Override
    public StorageDO getByCode(String code) {
        ValueMap param = new ValueMap();
        param.put(StorageDO.CODE, code);
        SelectBuilder selectBuilder = new SelectBuilder(param);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
            .and("code", ConditionType.EQUALS, StorageDO.CODE);
        return super.getForBean(selectBuilder.build(), StorageDO::new);
    }

    @Override
    public void load(StorageReq req) {
        CopyOnWriteArrayList<FileStorage> fileStorageList = fileStorageService.getFileStorageList();
        String domain = req.getDomain();
        ValidationUtils.throwIf(!URLUtils.isHttpUrl(domain), "域名格式错误");
        String bucketName = req.getBucketName();
        Integer type = req.getType();
        if (StorageTypeEnum.LOCAL.getValue().equals(type)) {
//            ValidationUtils.validate(req, ValidateGroup.Storage.Local.class);
            req.setBucketName(StrUtil.appendIfMissing(bucketName
                .replace(StringConstants.BACKSLASH, StringConstants.SLASH), StringConstants.SLASH));
            FileStorageProperties.LocalPlusConfig config = new FileStorageProperties.LocalPlusConfig();
            config.setPlatform(req.getCode());
            config.setStoragePath(bucketName);
            fileStorageList.addAll(FileStorageServiceBuilder.buildLocalPlusFileStorage(Collections
                .singletonList(config)));
            SpringWebUtils.registerResourceHandler(
                MapUtil.of(URLUtil.url(req.getDomain()).getPath(), bucketName));
        } else if (StorageTypeEnum.S3.getValue().equals(type)) {
//            ValidationUtils.validate(req, ValidateGroup.Storage.S3.class);
            FileStorageProperties.AmazonS3Config config = new FileStorageProperties.AmazonS3Config();
            config.setPlatform(req.getCode());
            config.setAccessKey(req.getAccessKey());
            config.setSecretKey(req.getSecretKey());
            config.setEndPoint(req.getEndpoint());
            config.setBucketName(bucketName);
            config.setDomain(domain);
            fileStorageList.addAll(FileStorageServiceBuilder.buildAmazonS3FileStorage(Collections
                .singletonList(config), null));
        }
    }

    @Override
    public void unload(StorageReq req) {
        CopyOnWriteArrayList<FileStorage> fileStorageList = fileStorageService.getFileStorageList();
        FileStorage fileStorage = fileStorageService.getFileStorage(req.getCode());
        fileStorageList.remove(fileStorage);
        fileStorage.close();
        SpringWebUtils.deRegisterResourceHandler(
            MapUtil.of(URLUtil.url(req.getDomain()).getPath(), req
                .getBucketName()));
    }

    /**
     * 解密 SecretKey
     *
     * @param req     请求参数
     * @param storage 存储信息
     */
    private void decodeSecretKey(StorageReq req, StorageDO storage) {
        if (!StorageTypeEnum.S3.equals(req.getType())) {
            return;
        }
        // 修改时，如果 SecretKey 不修改，需要手动修正
        String newSecretKey = req.getSecretKey();
        boolean isSecretKeyNotUpdate =
            StrUtil.isBlank(newSecretKey) || newSecretKey.contains(StringConstants.ASTERISK);
        if (null != storage && isSecretKeyNotUpdate) {
            req.setSecretKey(storage.getSecretKey());
            return;
        }
        // 新增时或修改了 SecretKey
        String secretKey = ExceptionUtils.exToNull(
            () -> SecureUtils.decryptByRsaPrivateKey(newSecretKey));
        ValidationUtils.throwIfNull(secretKey, "私有密钥解密失败");
        ValidationUtils.throwIf(secretKey.length() > 255, "私有密钥长度不能超过 255 个字符");
        req.setSecretKey(secretKey);
    }

    /**
     * 默认存储是否存在
     *
     * @param id ID
     * @return 是否存在
     */
    private boolean isDefaultExists(String id) {
        if (null != id) {
            ValueMap param = new ValueMap();
            param.put(StorageDO.STORAGE_ID, id);
            SelectBuilder selectBuilder = new SelectBuilder(param);
            selectBuilder.from("", super.getEntityDef(TABLE_NAME))
                .where()
                .and("", ConditionType.EQUALS, StorageDO.STORAGE_ID);

            StorageDO storageDO = super.getForBean(selectBuilder.build(), StorageDO::new);
            return storageDO.getIsDefault();
        }
        return false;
    }

    /**
     * 编码是否存在
     *
     * @param code 编码
     * @param id   ID
     * @return 是否存在
     */
    private boolean isCodeExists(String code, String id) {
        if (null != id) {
            ValueMap param = new ValueMap();
            param.put(StorageDO.STORAGE_ID, id);
            SelectBuilder selectBuilder = new SelectBuilder(param);
            selectBuilder.from("", super.getEntityDef(TABLE_NAME))
                .where()
                .and("", ConditionType.EQUALS, StorageDO.STORAGE_ID);
            return super.getForBean(selectBuilder.build(), StorageDO::new).getCode().equals(code);
        }
        return false;
    }
}
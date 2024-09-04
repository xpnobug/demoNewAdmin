package com.newadmin.demoservice.mainPro.filepro.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.newadmin.democonfig.constant.StringConstants;
import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.democonfig.util.StrUtils;
import com.newadmin.democonfig.util.validate.CheckUtils;
import com.newadmin.demoservice.mainPro.filepro.entity.FileDO;
import com.newadmin.demoservice.mainPro.filepro.entity.StorageDO;
import com.newadmin.demoservice.mainPro.filepro.enums.FileTypeEnum;
import com.newadmin.demoservice.mainPro.filepro.resp.FileResp;
import com.newadmin.demoservice.mainPro.filepro.resp.FileStatisticsResp;
import com.newadmin.demoservice.mainPro.filepro.service.FileService;
import com.newadmin.demoservice.mainPro.filepro.service.StorageService;
import com.newadmin.demoservice.mainPro.filepro.util.URLUtils;
import jakarta.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.dromara.x.file.storage.core.ProgressListener;
import org.dromara.x.file.storage.core.upload.UploadPretreatment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件业务实现
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl extends DefaultService implements FileService {

    public static final String TABLE_NAME = "reai_file";

    private final FileStorageService fileStorageService;
    @Resource
    private StorageService storageService;

    @Override
    public void beforeDelete(List<String> ids) {
        ValueMap param = new ValueMap();
        param.put(StorageDO.STORAGE_ID, ids);
        SelectBuilder selectBuilder = new SelectBuilder(param);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
            .and("fileId", ConditionType.IN, FileDO.STORAGE_ID);

        List<FileDO> fileList = super.listForBean(selectBuilder.build(), FileDO::new);
        Map<String, List<FileDO>> fileListGroup = fileList.stream()
            .collect(Collectors.groupingBy(FileDO::getStorageId));
        for (Map.Entry<String, List<FileDO>> entry : fileListGroup.entrySet()) {
            StorageDO storage = storageService.getStorageById(entry.getKey());
            for (FileDO file : entry.getValue()) {
                FileInfo fileInfo = file.toFileInfo(storage.getCode());
                fileStorageService.delete(fileInfo);
            }
        }
    }

    @Override
    public FileInfo upload(MultipartFile file, String storageCode) {
        StorageDO storage;
        if (StrUtil.isBlank(storageCode)) {
            storage = storageService.getDefaultStorage();
            CheckUtils.throwIfNull(storage, "请先指定默认存储");
        } else {
            storage = storageService.getByCode(storageCode);
            CheckUtils.throwIfNotExists(storage, "StorageDO", "Code", storageCode);
        }
//        LocalDate today = LocalDate.now();
        String fileType = StrUtil.appendIfMissing(file.getContentType(), StringConstants.SLASH);
//        String suffix = FileNameUtil.getSuffix(file.getOriginalFilename());
        String yearAndMonth = DateUtil.format(new Date(), DatePattern.NORM_DATE_FORMAT);
        String path = StringConstants.SLASH + fileType + yearAndMonth + StringConstants.SLASH;
        UploadPretreatment uploadPretreatment = fileStorageService.of(file)
            .setPlatform(storage.getCode())
            .putAttr(ClassUtil.getClassName(StorageDO.class, false), storage)
            .setPath(path);

        // 图片文件生成缩略图
        if (FileTypeEnum.IMAGE.getExtensions()
            .contains(FileNameUtil.extName(file.getOriginalFilename()))) {
            uploadPretreatment.thumbnail(img -> img.size(100, 100));
        }
        uploadPretreatment.setProgressMonitor(new ProgressListener() {
            @Override
            public void start() {
                log.info("开始上传");
            }

            @Override
            public void progress(long progressSize, Long allSize) {
                log.info("已上传 [{}]，总大小 [{}]", progressSize, allSize);
            }

            @Override
            public void finish() {
                log.info("上传结束");
            }
        });
        // 处理本地存储文件 URL
        FileInfo fileInfo = uploadPretreatment.upload();
//        String buckName = StrUtil.appendIfMissing(storage.getBucketName(), StringConstants.SLASH);
        fileInfo.setUrl(
            URLUtil.normalize(storage.getDomain() + fileInfo.getPath() + fileInfo.getFilename()));
        return fileInfo;
    }

    @Override
    public long countByStorageIds(List<String> storageIds) {
        ValueMap param = new ValueMap();
        param.put(StorageDO.STORAGE_ID, storageIds);
        SelectBuilder selectBuilder = new SelectBuilder(param);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
            .and("fileId", ConditionType.IN, FileDO.STORAGE_ID);
        return super.count(selectBuilder.build());
    }

    @Override
    public FileStatisticsResp statistics() {
        FileStatisticsResp resp = new FileStatisticsResp();
        String sql = "SELECT type, COUNT(1) number, SUM(size) size FROM reai_file GROUP BY type";
        SelectBuilder selectBuilder = new SelectBuilder(sql);
        List<FileStatisticsResp> statisticsList = super.listForBean(selectBuilder.build(),
            FileStatisticsResp::new);
        if (CollUtil.isEmpty(statisticsList)) {
            return resp;
        }
        resp.setData(statisticsList);
        resp.setSize(statisticsList.stream().mapToLong(FileStatisticsResp::getSize).sum());
        resp.setNumber(statisticsList.stream().mapToLong(FileStatisticsResp::getNumber).sum());
        return resp;
    }

    @Override
    public void fill(Object obj) {
//        super.fill(obj);
        if (obj instanceof FileResp fileResp && !URLUtils.isHttpUrl(fileResp.getUrl())) {
            StorageDO storage = storageService.getStorageById(fileResp.getStorageId());
            String prefix = StrUtil.appendIfMissing(storage.getDomain(), StringConstants.SLASH);
            String url = URLUtil.normalize(prefix + fileResp.getUrl());
            fileResp.setUrl(url);
            String thumbnailUrl = StrUtils.blankToDefault(fileResp.getThumbnailUrl(), url,
                thUrl -> URLUtil
                    .normalize(prefix + thUrl));
            fileResp.setThumbnailUrl(thumbnailUrl);
            fileResp.setStorageName("%s (%s)".formatted(storage.getName(), storage.getCode()));
        }
    }
}
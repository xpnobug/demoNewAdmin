package com.newadmin.demoservice.mainPro.filepro.config.file;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.newadmin.demoservice.mainPro.filepro.query.StorageQuery;
import com.newadmin.demoservice.mainPro.filepro.req.StorageReq;
import com.newadmin.demoservice.mainPro.filepro.resp.StorageResp;
import com.newadmin.demoservice.mainPro.filepro.service.StorageService;
import com.newadmin.demoservice.mainPro.ltpro.common.enums.DisEnableStatusEnum;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 文件存储配置加载器
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class FileStorageConfigLoader implements ApplicationRunner {

    private final StorageService storageService;

    @Override
    public void run(ApplicationArguments args) {
        StorageQuery query = new StorageQuery();
        query.setStatus(DisEnableStatusEnum.ENABLE.getValue());
        List<StorageResp> storageList = storageService.list(query, null);
        if (CollUtil.isEmpty(storageList)) {
            return;
        }
        storageList.forEach(s -> storageService.load(BeanUtil.copyProperties(s, StorageReq.class)));
    }
}

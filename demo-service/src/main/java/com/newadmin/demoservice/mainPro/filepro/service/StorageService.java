package com.newadmin.demoservice.mainPro.filepro.service;

import com.newadmin.demoservice.mainPro.filepro.entity.StorageDO;
import com.newadmin.demoservice.mainPro.filepro.query.StorageQuery;
import com.newadmin.demoservice.mainPro.filepro.req.StorageReq;
import com.newadmin.demoservice.mainPro.filepro.resp.StorageResp;
import java.util.List;

/**
 * 存储业务接口
 */
public interface StorageService {

    void beforeUpdate(StorageReq req, String id);

    void beforeDelete(List<String> ids);

    /**
     * 查询默认存储
     *
     * @return 存储信息
     */
    StorageDO getDefaultStorage();

    /**
     * 根据编码查询
     *
     * @param code 编码
     * @return 存储信息
     */
    StorageDO getByCode(String code);

    /**
     * 加载存储
     *
     * @param req 存储信息
     */
    void load(StorageReq req);

    /**
     * 卸载存储
     *
     * @param req 存储信息
     */
    void unload(StorageReq req);

    void beforeAdd(StorageReq req);

    StorageDO getStorageById(String key);

    List<StorageResp> list(StorageQuery query, Object o);
}
package com.newadmin.demoservice.mainPro.ltpro.controller;

import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.democore.kduck.web.json.JsonPageObject;
import com.newadmin.demoservice.mainPro.filepro.entity.StorageDO;
import com.newadmin.demoservice.mainPro.filepro.service.impl.StorageServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "存储管理 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/storage")
public class StorageController extends DefaultService {

    @GetMapping
    @Operation(summary = "分页查询存储管理", description = "分页查询存储管理")
    public JsonPageObject storage(Page page) {
        SelectBuilder selectBuilder = new SelectBuilder();
        selectBuilder.from("", super.getEntityDef(StorageServiceImpl.TABLE_NAME))
            .where();
        List<StorageDO> storageDOList = super.listForBean(selectBuilder.build(), page,
            StorageDO::new);
        return new JsonPageObject(page, storageDOList);
    }

    @GetMapping("{id}")
    @Operation(summary = "查询单个存储管理", description = "查询单个存储管理")
    public JsonObject getStorage(@PathVariable String id) {
        ValueMap param = new ValueMap();
        param.put("storageId", id);
        SelectBuilder selectBuilder = new SelectBuilder(param);
        selectBuilder.from("", super.getEntityDef(StorageServiceImpl.TABLE_NAME))
            .where()
            .and("storage_id", ConditionType.EQUALS, "storageId");
        StorageDO storageDO = super.getForBean(selectBuilder.build(), StorageDO::new);
        return new JsonObject(storageDO);
    }

    @PutMapping("{id}")
    @Operation(summary = "修改单个存储管理", description = "修改单个存储管理")
    public JsonObject updStorage(@RequestBody StorageDO storageDO) {
        super.update(StorageServiceImpl.TABLE_NAME, storageDO);
        return new JsonObject(storageDO);
    }
}

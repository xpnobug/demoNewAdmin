package com.newadmin.demoservice.mainPro.ltpro.query;

import com.newadmin.democore.kduck.definition.BeanEntityDef;
import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.demoservice.mainPro.filepro.entity.FileDO;
import com.newadmin.demoservice.mainPro.filepro.service.impl.FileServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FileQuery extends DefaultService {

    /**
     * 根据fileid 获取文件
     *
     * @param fileIds
     * @return
     */
    public List<FileDO> fileDOList(String[] fileIds) {
        ValueMap params = new ValueMap();
        params.put(FileDO.FILE_ID, fileIds);
        // 构建查询构造器
        SelectBuilder selectBuilder = new SelectBuilder(params);
        BeanEntityDef fileBean = super.getEntityDef(FileServiceImpl.TABLE_NAME);
        selectBuilder.from("", fileBean)
            .where()
            .and("file_id", ConditionType.IN, FileDO.FILE_ID)
            .orderBy().desc("create_time");
        return super.listForBean(selectBuilder.build(), FileDO::new);
    }
}

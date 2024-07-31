package com.newadmin.demoservice.mainPro.ltpro.query;

import com.newadmin.democore.kduck.definition.BeanDefDepository;
import com.newadmin.democore.kduck.definition.BeanEntityDef;
import com.newadmin.democore.kduck.query.QueryCreator;
import com.newadmin.democore.kduck.query.QuerySupport;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.democore.kduck.utils.BeanDefUtils;
import com.newadmin.demoservice.mainPro.ltpro.service.impl.ReaiArticleServiceImpl;
import com.newadmin.demoservice.mainPro.nas.service.impl.FileServiceImpl;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class FileQuery implements QueryCreator {

    @Override
    public QuerySupport createQuery(Map<String, Object> paramMap, BeanDefDepository depository) {
        BeanEntityDef article = depository.getEntityDef(
            ReaiArticleServiceImpl.TABLE_NAME);//获取学生表的对象的实体表示对象，CODE_STUDENT为接口中定义的实体编码
        BeanEntityDef file = depository.getEntityDef(
            FileServiceImpl.TABLE_NAME);//获取班级表的对象的实体表示对象，CODE_CLASS为接口中定义的实体编码

        SelectBuilder selectBuilder = new SelectBuilder(paramMap);
        selectBuilder.bindFields("a",
            BeanDefUtils.includeField(article.getFieldList(), "articleId"));
        selectBuilder.bindFields("b", file.getFieldList());

        selectBuilder.from("a", article).innerJoin("b", file)
            .where()
            .and("b.article_id", ConditionType.EQUALS, "articleId");
        return selectBuilder.build();
    }
}

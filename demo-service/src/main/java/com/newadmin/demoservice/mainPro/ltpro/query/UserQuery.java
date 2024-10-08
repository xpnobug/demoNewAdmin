package com.newadmin.demoservice.mainPro.ltpro.query;

import com.newadmin.democore.kduck.definition.BeanDefDepository;
import com.newadmin.democore.kduck.definition.BeanEntityDef;
import com.newadmin.democore.kduck.query.QueryCreator;
import com.newadmin.democore.kduck.query.QuerySupport;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.demoservice.mainPro.ltpro.service.impl.ReaiUsersServiceImpl;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class UserQuery implements QueryCreator {

    @Override
    public QuerySupport createQuery(Map<String, Object> paramMap, BeanDefDepository depository) {
        BeanEntityDef article = depository.getEntityDef(
            ReaiUsersServiceImpl.TABLE_NAME);//获取学生表的对象的实体表示对象，CODE_STUDENT为接口中定义的实体编码

        SelectBuilder selectBuilder = new SelectBuilder(paramMap);

        selectBuilder.bindFields("", article.getFieldList());

        selectBuilder.from("", article)
            .where()
            .and("user_id", ConditionType.IN, "userId")
            .orderBy().desc("registration_time");
        return selectBuilder.build();
    }
}

package ${packageName}.${subPackageName};

import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.democore.kduck.utils.Page;
import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import ${packageName}.mapper.${classNamePrefix}Mapper;
import ${packageName}.model.entity.${classNamePrefix}DO;
import ${packageName}.model.query.${classNamePrefix}Query;
import ${packageName}.model.req.${classNamePrefix}Req;
import ${packageName}.model.resp.${classNamePrefix}DetailResp;
import ${packageName}.model.resp.${classNamePrefix}Resp;
import ${packageName}.service.${classNamePrefix}Service;
import com.newadmin.democore.kduck.service.DefaultService;

/**
* ${businessName}业务实现
*
* @author ${author}
* @since ${date}
*/
@Service
@RequiredArgsConstructor
public class ${className} extends DefaultService implements ${classNamePrefix}Service {
public static final String TABLE_NAME = "${tableName}";

@Override
public List
<${classNamePrefix}Resp> page (${classNamePrefix}Query query, Page page) {
  ValueMap param = new ValueMap();
  SelectBuilder selectBuilder = new SelectBuilder();
  selectBuilder.from("", super.getEntityDef(TABLE_NAME))
  .where()
  .orderBy().desc("create_time");
  return super.listForBean(selectBuilder.build(), page, ${classNamePrefix}Resp::new);
  }

  @Override
  public ${classNamePrefix}DetailResp getDetail(String id) {
  return super.getForBean(TABLE_NAME, id, ${classNamePrefix}DetailResp::new);
  }

  @Override
  public String add(${classNamePrefix}Req req) {
  return super.add(TABLE_NAME, req).toString();
  }

  @Override
  public void update(${classNamePrefix}Req req) {
  super.update(TABLE_NAME, req);
  }

  @Override
  public void delete(String id) {
  super.delete(TABLE_NAME, new String[]{id});
  }

  }
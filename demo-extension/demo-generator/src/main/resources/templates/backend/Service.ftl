package ${packageName}.${subPackageName};

import ${packageName}.model.query.${classNamePrefix}Query;
import ${packageName}.model.req.${classNamePrefix}Req;
import ${packageName}.model.resp.${classNamePrefix}DetailResp;
import ${packageName}.model.resp.${classNamePrefix}Resp;

import com.newadmin.democore.kduck.utils.Page;
import java.util.List;

/**
* ${businessName}业务接口
*
* @author ${author}
* @since ${date}
*/
public interface ${className} {


/**
* 分页查询${businessName}列表
*
* @param query 查询条件
* @param page  分页查询条件
* @return ${businessName}分页列表信息
*/
List
<${classNamePrefix}Resp> page(${classNamePrefix}Query query, Page page);


  /**
  * 查询${businessName}详情
  *
  * @param id ${businessName}id
  * @return ${businessName}详情
  */
    ${classNamePrefix}DetailResp getDetail(String id);

  /**
  * 新增${businessName}
  *
  * @param req 新增参数
  * @return 新增
  */
  String add(${classNamePrefix}Req req);

  /**
  * 修改${businessName}
  *
  * @param req 修改参数
  * @return 修改
  */
  void update(${classNamePrefix}Req req);

  /**
  * 删除${businessName}
  *
  * @param id ${businessName}id
  * @return 删除
  */
  void delete(String id);
  }
package ${packageName}.${subPackageName};

import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.democore.kduck.web.json.JsonPageObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import ${packageName}.model.query.${classNamePrefix}Query;
import ${packageName}.model.req.${classNamePrefix}Req;
import ${packageName}.model.resp.${classNamePrefix}DetailResp;
import ${packageName}.model.resp.${classNamePrefix}Resp;
import ${packageName}.service.${classNamePrefix}Service;

/**
* ${businessName}管理 API
*
* @author ${author}
* @since ${date}
*/
@Tag(name = "${businessName}管理 API")
@RestController
@RequiredArgsConstructor
public class ${className} {

private final ${classNamePrefix}Service ${classNamePrefix?uncap_first}Service;

@Operation(summary = "分页查询${businessName}列表", description = "分页查询${businessName}列表")
@GetMapping("/pages")
public JsonPageObject page(
@Parameter(in = ParameterIn.QUERY, description = "分页参数")
Page page,
@Parameter(in = ParameterIn.QUERY, description = "查询参数")
${classNamePrefix}Query query) {
return new JsonPageObject(page, ${classNamePrefix?uncap_first}Service.page(query, page));
}

@Operation(summary = "获取${businessName}详情", description = "获取${businessName}详情")
@GetMapping("{id}")
public JsonObject get(
@Parameter(in = ParameterIn.PATH, description = "主键")
@PathVariable String id) {
return new JsonObject(${classNamePrefix?uncap_first}Service.getDetail(id));
}

@Operation(summary = "创建${businessName}", description = "创建${businessName}")
@PostMapping("/insert")
public JsonObject create(
@Parameter(in = ParameterIn.QUERY, description = "创建参数")
${classNamePrefix}Req req) {
return new JsonObject(${classNamePrefix?uncap_first}Service.add(req));
}

@Operation(summary = "更新${businessName}", description = "更新${businessName}")
@PutMapping("/upd/{id}")
public JsonObject update(
@Parameter(in = ParameterIn.PATH, description = "主键")
@PathVariable String id,
@Parameter(in = ParameterIn.QUERY, description = "更新参数")
${classNamePrefix}Req req) {
${classNamePrefix?uncap_first}Service.update(req);
return new JsonObject();
}
@Operation(summary = "删除${businessName}", description = "删除${businessName}")
@DeleteMapping("/del/{id}")
public JsonObject delete(
@Parameter(in = ParameterIn.PATH, description = "主键")
@PathVariable String id) {
${classNamePrefix?uncap_first}Service.delete(id);
return new JsonObject();
}


}
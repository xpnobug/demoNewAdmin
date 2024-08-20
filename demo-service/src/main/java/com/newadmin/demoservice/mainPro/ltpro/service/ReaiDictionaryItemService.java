package com.newadmin.demoservice.mainPro.ltpro.service;

import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiDictionaryItem;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author couei
 * @since 2024-06-10
 */
public interface ReaiDictionaryItemService {

    List<ReaiDictionaryItem> getDictionaryItemList(String dictCode);

    Map<String, String> dictMap(String dictCode);

    String getDictByItemCode(String itemCode);
}

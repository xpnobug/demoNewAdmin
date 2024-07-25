package com.newadmin.demoservice.mainPro.ltpro.service;

import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiDictionaryItem;
import java.util.List;

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

    String getDictByItemCode(String itemCode);
}

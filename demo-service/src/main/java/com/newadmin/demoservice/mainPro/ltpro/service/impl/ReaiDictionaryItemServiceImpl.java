package com.newadmin.demoservice.mainPro.ltpro.service.impl;

import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiDictionary;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiDictionaryItem;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiDictionaryItemService;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiDictionaryService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author couei
 * @since 2024-06-10
 */
@Service
@RequiredArgsConstructor
public class ReaiDictionaryItemServiceImpl extends DefaultService implements
    ReaiDictionaryItemService {

    public static final String TABLE_NAME = "reai_dictionary_item";

    private final ReaiDictionaryService dictionaryService;

    private List<ReaiDictionaryItem> getDictionItemById(String dictId) {
        ValueMap params = new ValueMap();
//        params.put("itemCode", code);
//        params.put("itemName", name);
        params.put("dictId", dictId);
        SelectBuilder selectBuilder = new SelectBuilder(params);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
//            .and("item_code", ConditionType.EQUALS, ReaiDictionaryItem.ITEM_CODE)
//            .and("item_name", ConditionType.EQUALS, ReaiDictionaryItem.ITEM_NAME)
            .and("dict_id", ConditionType.IN, ReaiDictionaryItem.DICT_ID)
            .orderBy().desc("order_num");
        return super.listForBean(selectBuilder.build(), ReaiDictionaryItem::new);
    }

    /**
     * 根据字典项code获取字典项名称
     *
     * @param code
     * @return
     */
    public String getDictionaryItemName(String code) {
        // 查询字典项

        // 判断字典项是否存在

        // 返回字典项名称

        return null;
    }

    /**
     * 根据字典code获取字典项列表
     *
     * @param dictCode 字典code
     * @return 字典项列表，如果字典不存在或没有字典项则返回空列表
     */
    @Override
    public List<ReaiDictionaryItem> getDictionaryItemList(String dictCode) {
        // 根据字典code获取字典
        ReaiDictionary dictionary = dictionaryService.getDictByCode(dictCode);
        // 如果字典存在，则获取字典项列表
        if (dictionary != null) {
            return getDictionItemById(dictionary.getDictId());
        } else {
            throw new RuntimeException("字典不存在");
        }
    }

    @Override
    public Map<String, String> dictMap(String dictCode) {
        // 根据字典code获取字典
        ReaiDictionary dictionary = dictionaryService.getDictByCode(dictCode);
        // 如果字典存在，则获取字典项列表
        if (dictionary != null) {
            Map<String, String> dictionaryItemMap = new HashMap<>();
            List<ReaiDictionaryItem> dictionaryItems = getDictionItemById(dictionary.getDictId());

            for (ReaiDictionaryItem item : dictionaryItems) {
                dictionaryItemMap.put(item.getItemCode(), item.getItemName());
            }

            return dictionaryItemMap;
        } else {
            return new HashMap<>();
        }
    }

    /**
     * 根据字典code获取字典项列表，以Map<code, name>格式返回
     *
     * @param dictCode 字典code
     * @return 字典项列表的Map，如果字典不存在或没有字典项则返回空Map
     */
    public Map<String, String> getDictionaryItemMap(String dictCode) {
        Map<String, String> dictionaryItemMap = new HashMap<>();
        List<ReaiDictionaryItem> dictionaryItems = getDictionaryItemList(dictCode);

        for (ReaiDictionaryItem item : dictionaryItems) {
            dictionaryItemMap.put(item.getItemCode(), item.getItemName());
        }

        return dictionaryItemMap;
    }

    /**
     * 根据字典项code获取字典项名称，从指定字典中查找
     *
     * @param dictCode 字典code
     * @param itemCode 字典项code
     * @return 字典项名称，如果不存在则返回null
     */
    public String getDictionaryItemName(String dictCode, String itemCode) {
        Map<String, String> dictionaryItemMap = getDictionaryItemMap(dictCode);
        return dictionaryItemMap.get(itemCode);
    }

    @Override
    public String getDictByItemCode(String itemCode) {
        ReaiDictionaryItem item = getForBean(TABLE_NAME, "itemCode", itemCode,
            ReaiDictionaryItem::new);
        if (item != null) {
            return item.getItemName();
        } else {
            throw new RuntimeException("字典项不存在");
        }
    }

}

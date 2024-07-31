package com.newadmin.demoservice.mainPro.ltpro.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.newadmin.democore.kduck.service.ValueMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author couei
 * @since 2024-06-10
 */

@TableName("reai_dictionary_item")
public class ReaiDictionaryItem extends ValueMap {

    /***/
    public static final String ITEM_ID = "itemId";

    /***/
    public static final String ITEM_NAME = "itemName";

    /***/
    public static final String ITEM_CODE = "itemCode";

    /***/
    public static final String ITEM_VALUE = "itemValue";

    /***/
    public static final String ORDER_NUM = "orderNum";

    /***/
    public static final String STATE = "state";

    /***/
    public static final String DICT_ID = "dictId";

    public ReaiDictionaryItem() {
    }

    public ReaiDictionaryItem(Map<String, Object> map) {
        super(map);
    }

    /**
     * 设置
     *
     * @param itemId
     */
    public void setItemId(String itemId) {
        super.setValue(ITEM_ID, itemId);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getItemId() {
        return super.getValueAsString(ITEM_ID);
    }

    /**
     * 设置
     *
     * @param itemName
     */
    public void setItemName(String itemName) {
        super.setValue(ITEM_NAME, itemName);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getItemName() {
        return super.getValueAsString(ITEM_NAME);
    }

    /**
     * 设置
     *
     * @param itemCode
     */
    public void setItemCode(String itemCode) {
        super.setValue(ITEM_CODE, itemCode);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getItemCode() {
        return super.getValueAsString(ITEM_CODE);
    }

    /**
     * 设置
     *
     * @param itemValue
     */
    public void setItemValue(String itemValue) {
        super.setValue(ITEM_VALUE, itemValue);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getItemValue() {
        return super.getValueAsString(ITEM_VALUE);
    }

    /**
     * 设置
     *
     * @param orderNum
     */
    public void setOrderNum(Integer orderNum) {
        super.setValue(ORDER_NUM, orderNum);
    }

    /**
     * 获取
     *
     * @return
     */
    public Integer getOrderNum() {
        return super.getValueAsInteger(ORDER_NUM);
    }

    /**
     * 设置
     *
     * @param state
     */
    public void setState(Integer state) {
        super.setValue(STATE, state);
    }

    /**
     * 获取
     *
     * @return
     */
    public Integer getState() {
        return super.getValueAsInteger(STATE);
    }

    /**
     * 设置
     *
     * @param dictId
     */
    public void setDictId(String dictId) {
        super.setValue(DICT_ID, dictId);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getDictId() {
        return super.getValueAsString(DICT_ID);
    }
}

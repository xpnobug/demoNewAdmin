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
@TableName("reai_dictionary")
public class ReaiDictionary extends ValueMap {

    /***/
    public static final String DICT_ID = "dictId";

    /***/
    public static final String DICT_NAME = "dictName";

    /***/
    public static final String DICT_CODE = "dictCode";

    /***/
    public static final String GROUP_NAME = "groupName";

    /***/
    public static final String ORDER_NUM = "orderNum";

    public ReaiDictionary() {
    }

    public ReaiDictionary(Map<String, Object> map) {
        super(map);
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

    /**
     * 设置
     *
     * @param dictName
     */
    public void setDictName(String dictName) {
        super.setValue(DICT_NAME, dictName);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getDictName() {
        return super.getValueAsString(DICT_NAME);
    }

    /**
     * 设置
     *
     * @param dictCode
     */
    public void setDictCode(String dictCode) {
        super.setValue(DICT_CODE, dictCode);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getDictCode() {
        return super.getValueAsString(DICT_CODE);
    }

    /**
     * 设置
     *
     * @param groupName
     */
    public void setGroupName(String groupName) {
        super.setValue(GROUP_NAME, groupName);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getGroupName() {
        return super.getValueAsString(GROUP_NAME);
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
}

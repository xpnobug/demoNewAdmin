package com.newadmin.demoservice.mainPro.ltpro.service.impl;

import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiDictionary;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiDictionaryService;
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
public class ReaiDictionaryServiceImpl extends DefaultService implements ReaiDictionaryService {

    public static final String TABLE_NAME = "reai_dictionary";
    /**
     * 根据code获取字典
     *
     * @param code
     * @return
     */
    @Override
    public ReaiDictionary getDictByCode(String code) {
        return super.getForBean(TABLE_NAME, "dictCode", code,
            ReaiDictionary::new);
    }

}

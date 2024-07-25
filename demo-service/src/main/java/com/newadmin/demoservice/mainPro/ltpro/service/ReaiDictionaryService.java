package com.newadmin.demoservice.mainPro.ltpro.service;

import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiDictionary;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author couei
 * @since 2024-06-10
 */
public interface ReaiDictionaryService {

    ReaiDictionary getDictByCode(String code);
}

package com.newadmin.demoservice.mainPro.nas.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newadmin.demoservice.mainPro.nas.dao.ModuleConfigMapper;
import com.newadmin.demoservice.mainPro.nas.entity.ModuleConfig;
import com.newadmin.demoservice.mainPro.nas.service.ModuleConfigService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author couei
 * @since 2024-03-31
 */
@Service
public class ModuleConfigServiceImpl extends
    ServiceImpl<ModuleConfigMapper, ModuleConfig> implements ModuleConfigService {

}

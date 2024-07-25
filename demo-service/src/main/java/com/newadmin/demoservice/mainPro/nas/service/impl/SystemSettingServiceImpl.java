package com.newadmin.demoservice.mainPro.nas.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newadmin.demoservice.mainPro.nas.dao.SystemSettingMapper;
import com.newadmin.demoservice.mainPro.nas.entity.SystemSetting;
import com.newadmin.demoservice.mainPro.nas.service.SystemSettingService;
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
public class SystemSettingServiceImpl extends
    ServiceImpl<SystemSettingMapper, SystemSetting> implements SystemSettingService {

}

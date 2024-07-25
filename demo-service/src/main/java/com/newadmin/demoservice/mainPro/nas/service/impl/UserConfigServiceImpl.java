package com.newadmin.demoservice.mainPro.nas.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newadmin.demoservice.mainPro.nas.dao.UserConfigMapper;
import com.newadmin.demoservice.mainPro.nas.entity.UserConfig;
import com.newadmin.demoservice.mainPro.nas.service.UserConfigService;
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
public class UserConfigServiceImpl extends ServiceImpl<UserConfigMapper, UserConfig> implements
    UserConfigService {

}

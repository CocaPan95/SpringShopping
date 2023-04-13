package com.coca.shoppinguserservice.impl;

import com.coca.shoppingmodel.domain.user.UmsIntegrationConsumeSetting;
import com.coca.shoppinguserapi.UmsIntegrationConsumeSettingService;
import com.coca.shoppinguserservice.mapper.UmsIntegrationConsumeSettingMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class UmsIntegrationConsumeSettingImpl implements UmsIntegrationConsumeSettingService {

    @Autowired
    private UmsIntegrationConsumeSettingMapper integrationConsumeSettingMapper;

    public UmsIntegrationConsumeSetting GetIntegrationConsumeSetting(){
        return integrationConsumeSettingMapper.selectByPrimaryKey(1L);
    }
}

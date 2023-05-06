package com.coca.shoppinguserservice.service.impl;

import com.coca.shoppingmodel.domain.user.UmsIntegrationConsumeSetting;
import com.coca.shoppinguserservice.mapper.UmsIntegrationConsumeSettingMapper;
import com.coca.shoppinguserservice.service.IntegrationConsumeSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntegrationConsumeSettingImpl implements IntegrationConsumeSettingService {
    @Autowired
    private UmsIntegrationConsumeSettingMapper integrationConsumeSettingMapper;

    public UmsIntegrationConsumeSetting GetIntegrationConsumeSetting(){
        return integrationConsumeSettingMapper.selectByPrimaryKey(1L);
    }
}

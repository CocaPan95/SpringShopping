package com.coca.shoppinguserservice.rpc;

import com.coca.shoppingmodel.domain.user.UmsIntegrationConsumeSetting;
import com.coca.shoppinguserapi.UmsIntegrationConsumeSettingService;
import com.coca.shoppinguserservice.service.IntegrationConsumeSettingService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class UmsIntegrationConsumeSettingRpc implements UmsIntegrationConsumeSettingService {
    @Autowired
    private IntegrationConsumeSettingService integrationConsumeSettingService;
    @Override
    public UmsIntegrationConsumeSetting GetIntegrationConsumeSetting(){
        return integrationConsumeSettingService.GetIntegrationConsumeSetting();
    }
}

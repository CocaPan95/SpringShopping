package com.coca.shoppinguserservice.rpc;

import com.coca.shoppingmodel.entity.ums.UmsIntegrationConsumeSetting;
import com.coca.shoppinguserapi.IUmsIntegrationConsumeSettingRpcService;
import com.coca.shoppinguserservice.service.IUmsIntegrationConsumeSettingService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class UmsIntegrationConsumeSettingRpc implements IUmsIntegrationConsumeSettingRpcService {
    @Autowired
    private IUmsIntegrationConsumeSettingService integrationConsumeSettingService;
    @Override
    public UmsIntegrationConsumeSetting GetIntegrationConsumeSetting(){
        return integrationConsumeSettingService.GetIntegrationConsumeSetting();
    }
}

package com.coca.shoppinguserservice.service;

import com.coca.shoppingmodel.entity.ums.UmsIntegrationConsumeSetting;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 积分消费设置 服务类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
public interface IUmsIntegrationConsumeSettingService extends IService<UmsIntegrationConsumeSetting> {
    UmsIntegrationConsumeSetting GetIntegrationConsumeSetting();
}

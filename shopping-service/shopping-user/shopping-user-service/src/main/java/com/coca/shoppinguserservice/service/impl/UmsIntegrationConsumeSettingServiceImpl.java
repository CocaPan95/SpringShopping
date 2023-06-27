package com.coca.shoppinguserservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coca.shoppingmodel.entity.ums.UmsIntegrationConsumeSetting;
import com.coca.shoppinguserservice.mapper.UmsIntegrationConsumeSettingMapper;
import com.coca.shoppinguserservice.service.IUmsIntegrationConsumeSettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 积分消费设置 服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
@Service
public class UmsIntegrationConsumeSettingServiceImpl extends ServiceImpl<UmsIntegrationConsumeSettingMapper, UmsIntegrationConsumeSetting> implements IUmsIntegrationConsumeSettingService {
    public UmsIntegrationConsumeSetting GetIntegrationConsumeSetting(){
        return baseMapper.selectOne(new QueryWrapper<>());
    }
}

package com.coca.shoppingorderservice.service.impl;

import com.coca.shoppingmodel.entity.oms.OmsOrderSetting;
import com.coca.shoppingorderservice.mapper.OmsOrderSettingMapper;
import com.coca.shoppingorderservice.service.IOmsOrderSettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单设置表 服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-28
 */
@Service
public class OmsOrderSettingServiceImpl extends ServiceImpl<OmsOrderSettingMapper, OmsOrderSetting> implements IOmsOrderSettingService {

}

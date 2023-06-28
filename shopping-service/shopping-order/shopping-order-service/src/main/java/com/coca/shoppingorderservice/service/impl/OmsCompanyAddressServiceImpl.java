package com.coca.shoppingorderservice.service.impl;

import com.coca.shoppingmodel.entity.oms.OmsCompanyAddress;
import com.coca.shoppingorderservice.mapper.OmsCompanyAddressMapper;
import com.coca.shoppingorderservice.service.IOmsCompanyAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公司收发货地址表 服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-28
 */
@Service
public class OmsCompanyAddressServiceImpl extends ServiceImpl<OmsCompanyAddressMapper, OmsCompanyAddress> implements IOmsCompanyAddressService {

}

package com.coca.shoppingorderservice.service.impl;

import com.coca.shoppingmodel.entity.oms.OmsOrderItem;
import com.coca.shoppingorderservice.mapper.OmsOrderItemMapper;
import com.coca.shoppingorderservice.service.IOmsOrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单中所包含的商品 服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-28
 */
@Service
public class OmsOrderItemServiceImpl extends ServiceImpl<OmsOrderItemMapper, OmsOrderItem> implements IOmsOrderItemService {

}

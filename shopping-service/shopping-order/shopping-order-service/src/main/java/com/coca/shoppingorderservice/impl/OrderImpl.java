package com.coca.shoppingorderservice.impl;

import com.coca.shoppingorderapi.OrderService;
import com.coca.shoppingorderservice.mapper.OmsCartItemMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class OrderImpl implements OrderService {
    @Autowired
    private OmsCartItemMapper omsCartItemMapper;


    //购物车信息生成确认订单

    //确认单下单

    //支付回调
}

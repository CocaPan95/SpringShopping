package com.coca.shoppingorderservice.rpc;

import com.coca.shoppingmodel.api.CommonPage;
import com.coca.shoppingmodel.dto.*;
import com.coca.shoppingorderapi.OmsOrderService;
import com.coca.shoppingorderservice.service.OrderService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@DubboService
public class OmsOrderRpc implements OmsOrderService {
    @Autowired
    private OrderService orderService;

    @Override
    //购物车信息生成确认订单
    public ConfirmOrderResult generateConfirmOrder(Long MemberId, List<Long> ids) {
        return orderService.generateConfirmOrder(MemberId, ids);
    }

    @Override
    //确认单下单
    public Map<String, Object> generateOrder(Long MemberId, OrderParam orderParam) {
        return orderService.generateOrder(MemberId, orderParam);
    }

    //支付回调
    @Override
    public Integer paySuccess(Long orderId, Integer payType) {
        return orderService.paySuccess(orderId, payType);
    }

    @Override
    //取消订单
    public void cancelOrder(Long orderId) {
        orderService.cancelOrder(orderId);
    }

    //取消超时订单
    @Override
    public Integer cancelTimeOutOrder() {
        return orderService.cancelTimeOutOrder();
    }

    @Override
    public void confirmReceiveOrder(Long MemberId, Long orderId) {
        orderService.confirmReceiveOrder(MemberId, orderId);
    }

    @Override
    public CommonPage<OmsOrderDetail> list(Long MemberId, Integer status, Integer pageNum, Integer pageSize) {
        return orderService.list(MemberId, status, pageNum, pageSize);
    }

    @Override
    public OmsOrderDetail detail(Long orderId) {
        return orderService.detail(orderId);
    }

    @Override
    public void deleteOrder(Long MemberId, Long orderId) {
        orderService.deleteOrder(MemberId, orderId);
    }

    @Override
    public void sendDelayMessageCancelOrder(Long orderId) {
        orderService.sendDelayMessageCancelOrder(orderId);
    }
}

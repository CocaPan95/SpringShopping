package com.coca.shoppingorderapi;

import com.coca.shoppingmodel.dto.ConfirmOrderResult;
import com.coca.shoppingmodel.dto.OrderParam;

import java.util.List;
import java.util.Map;

public interface OmsOrderService {
    //购物车生成确认单
    ConfirmOrderResult generateConfirmOrder(List<Long> ids);

    //下单流程
    Map<String, Object> generateOrder(OrderParam orderParam);

    //支付成功回调
    Integer paySuccess(Long orderId, Integer payType);

    //取消订单
    void cancelOrder(Long orderId);

    //取消超时未支付订单
    Integer cancelTimeOutOrder();
}

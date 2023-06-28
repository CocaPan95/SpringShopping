package com.coca.shoppingorderservice.service;

import com.coca.shoppingmodel.api.CommonPage;
import com.coca.shoppingmodel.dto.ConfirmOrderResult;
import com.coca.shoppingmodel.dto.OmsOrderDetail;
import com.coca.shoppingmodel.dto.OrderParam;
import com.coca.shoppingmodel.entity.oms.OmsOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author coca
 * @since 2023-06-28
 */
public interface IOmsOrderService extends IService<OmsOrder> {
    //购物车生成确认单
    ConfirmOrderResult generateConfirmOrder(Long MemberId, List<Long> ids);

    //下单流程
    Map<String, Object> generateOrder(Long MemberId, OrderParam orderParam);

    //支付成功回调
    Integer paySuccess(Long orderId, Integer payType);

    //取消订单
    void cancelOrder(Long orderId);

    void sendDelayMessageCancelOrder(Long orderId);

    //取消超时未支付订单
    Integer cancelTimeOutOrder();

    //确认收货
    void confirmReceiveOrder(Long MemberId,Long orderId);

    //根据状态查询订单列表
    CommonPage<OmsOrderDetail> list(Long MemberId, Integer status, Integer pageNum, Integer pageSize);

    //查询订单详情
    OmsOrderDetail detail(Long orderId);

    //查询订单
    void deleteOrder(Long MemberId,Long orderId);
}

package com.coca.shoppingmodel.dto;


import com.coca.shoppingmodel.entity.oms.OmsOrder;
import com.coca.shoppingmodel.entity.oms.OmsOrderItem;

import java.io.Serializable;
import java.util.List;

/**
 * 包含订单商品信息的订单详情
 * Created by macro on 2018/9/4.
 */
public class OmsOrderDetail extends OmsOrder implements Serializable {
    private List<OmsOrderItem> orderItemList;

    public List<OmsOrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OmsOrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}

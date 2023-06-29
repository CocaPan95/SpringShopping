package com.coca.shoppingorderservice.mapper;

import com.coca.shoppingmodel.dto.OmsOrderDetail;
import com.coca.shoppingmodel.entity.oms.OmsOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coca.shoppingmodel.entity.oms.OmsOrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author coca
 * @since 2023-06-28
 */
public interface OmsOrderMapper extends BaseMapper<OmsOrder> {
    /**
     * 获取订单及下单商品详情
     */
    OmsOrderDetail getDetail(@Param("orderId") Long orderId);
    /**
     * 修改 pms_sku_stock表的锁定库存及真实库存
     */
    int updateSkuStock(@Param("itemList") List<OmsOrderItem> orderItemList);

    /**
     * 解除取消订单的库存锁定
     */
    int releaseSkuStockLock(@Param("itemList") List<OmsOrderItem> orderItemList);

    /**
     * 获取超时订单
     * @param minute 超时时间（分）
     */
    List<OmsOrderDetail> getTimeOutOrders(@Param("minute") Integer minute);

    /**
     * 批量修改订单状态
     */
    int updateOrderStatus(@Param("ids") List<Long> ids,@Param("status") Integer status);
}

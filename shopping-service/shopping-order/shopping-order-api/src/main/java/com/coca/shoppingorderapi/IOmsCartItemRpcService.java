package com.coca.shoppingorderapi;

import com.coca.shoppingmodel.dto.CartPromotionItem;
import com.coca.shoppingmodel.entity.oms.OmsCartItem;

import java.util.List;

public interface IOmsCartItemRpcService {
    //获取包含优惠活动的购物车列表
    List<CartPromotionItem> GetCartPromotionItemList(Long memberId, List<Long> cartItemIds);

    //获取某个用户的购物车列表
    List<OmsCartItem> GetOmsCartItemList(Long memberId);

    int add(Long memberId,OmsCartItem cartItem);
    /**
     * 批量删除购物车中的商品
     */
    int delete(Long memberId,List<Long> ids);
}

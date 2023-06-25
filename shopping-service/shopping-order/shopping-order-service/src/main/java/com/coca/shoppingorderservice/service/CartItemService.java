package com.coca.shoppingorderservice.service;

import com.coca.shoppingmodel.domain.order.OmsCartItem;
import com.coca.shoppingmodel.dto.CartPromotionItem;

import java.util.List;

public interface CartItemService {
    List<CartPromotionItem> GetCartPromotionItemList(Long memberId, List<Long> cartItemIds);

    //获取某个用户的购物车列表
    List<OmsCartItem> GetOmsCartItemList(Long memberId);

    int add(Long memberId,OmsCartItem cartItem);
    /**
     * 批量删除购物车中的商品
     */
    int delete(Long memberId,List<Long> ids);
}
package com.coca.shoppingorderapi;

import com.coca.shoppingmodel.domain.order.OmsCartItem;
import com.coca.shoppingmodel.dto.CartPromotionItem;

import java.util.List;

public interface CartItemService {
    //获取包含优惠活动的购物车列表
    List<CartPromotionItem> GetCartPromotionItemList(Long memberId, List<Long> cartItemIds);

    //获取某个用户的购物车列表
    List<OmsCartItem> GetOmsCartItemList(Long memberId);
}

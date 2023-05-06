package com.coca.shoppingsmsservice.service;

import com.coca.shoppingmodel.domain.order.OmsCartItem;
import com.coca.shoppingmodel.dto.CartPromotionItem;

import java.util.List;

public interface PromotionService {
    List<CartPromotionItem> calcCartPromotion(List<OmsCartItem> cartItemList);
}

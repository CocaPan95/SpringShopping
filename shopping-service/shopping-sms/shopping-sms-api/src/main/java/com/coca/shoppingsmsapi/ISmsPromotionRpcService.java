package com.coca.shoppingsmsapi;

import com.coca.shoppingmodel.dto.CartPromotionItem;
import com.coca.shoppingmodel.entity.oms.OmsCartItem;

import java.util.List;

public interface ISmsPromotionRpcService {
    List<CartPromotionItem> calcCartPromotion(List<OmsCartItem> cartItemList);
}

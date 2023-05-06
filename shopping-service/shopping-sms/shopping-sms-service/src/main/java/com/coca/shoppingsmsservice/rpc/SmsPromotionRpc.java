package com.coca.shoppingsmsservice.rpc;

import com.coca.shoppingmodel.domain.order.OmsCartItem;
import com.coca.shoppingmodel.dto.CartPromotionItem;
import com.coca.shoppingsmsapi.SmsPromotionService;
import com.coca.shoppingsmsservice.service.PromotionService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@DubboService
public class SmsPromotionRpc implements SmsPromotionService {
    @Autowired
    private PromotionService promotionService;

    @Override
    public List<CartPromotionItem> calcCartPromotion(List<OmsCartItem> cartItemList) {
       return promotionService.calcCartPromotion(cartItemList);
    }
}

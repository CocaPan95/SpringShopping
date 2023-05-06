package com.coca.shoppingorderservice.rpc;

import com.coca.shoppingmodel.domain.order.OmsCartItem;
import com.coca.shoppingmodel.dto.CartPromotionItem;
import com.coca.shoppingorderapi.OmsCartItemService;
import com.coca.shoppingorderservice.service.CartItemService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class OmsCartItemRpc implements OmsCartItemService {
    @Autowired
    private CartItemService cartItemService;

    @Override
    public List<CartPromotionItem> GetCartPromotionItemList(Long memberId, List<Long> cartItemIds) {
        return cartItemService.GetCartPromotionItemList(memberId,cartItemIds);
    }

    @Override
    public List<OmsCartItem> GetOmsCartItemList(Long memberId) {
        return cartItemService.GetOmsCartItemList(memberId);
    }

    @Override
    public int add(Long memberId, OmsCartItem cartItem) {
        return cartItemService.add(memberId,cartItem);
    }

    @Override
    public int delete(Long memberId, List<Long> ids) {
        return cartItemService.delete(memberId,ids);
    }
}

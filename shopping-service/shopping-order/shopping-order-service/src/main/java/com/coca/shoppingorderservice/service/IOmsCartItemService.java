package com.coca.shoppingorderservice.service;

import com.coca.shoppingmodel.dto.CartPromotionItem;
import com.coca.shoppingmodel.entity.oms.OmsCartItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 购物车表 服务类
 * </p>
 *
 * @author coca
 * @since 2023-06-28
 */
public interface IOmsCartItemService extends IService<OmsCartItem> {
    List<CartPromotionItem> GetCartPromotionItemList(Long memberId, List<Long> cartItemIds);

    //获取某个用户的购物车列表
    List<OmsCartItem> GetOmsCartItemList(Long memberId);

    int add(Long memberId,OmsCartItem cartItem);
    /**
     * 批量删除购物车中的商品
     */
    int delete(Long memberId,List<Long> ids);
}

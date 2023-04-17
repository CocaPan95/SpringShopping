package com.coca.shoppingsmsapi;

import com.coca.shoppingmodel.dto.CartPromotionItem;
import com.coca.shoppingmodel.dto.SmsCouponHistoryDetail;

import java.util.List;

public interface UmsMemberCouponService {
    /**
     * 根据购物车信息获取可用优惠券
     */
    List<SmsCouponHistoryDetail> listCart(Long MemberId,List<CartPromotionItem> cartItemList, Integer type);

    void UpdateCouponStatus(Long couponId, Long memberId, Integer useStatus);
}

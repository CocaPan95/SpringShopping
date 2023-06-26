package com.coca.shoppingsmsservice.service;

import com.coca.shoppingmodel.domain.sms.SmsCoupon;

import java.util.List;

public interface CouponHistoryService {
    void updateCouponStatus(Long couponId, Long memberId, Integer useStatus);
    List<SmsCoupon> getAvailableCouponList(Long productId, Long productCategoryId);
}

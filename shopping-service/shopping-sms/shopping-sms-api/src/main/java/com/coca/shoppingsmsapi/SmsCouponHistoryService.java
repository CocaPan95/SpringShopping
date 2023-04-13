package com.coca.shoppingsmsapi;

public interface SmsCouponHistoryService {
    void updateCouponStatus(Long couponId, Long memberId, Integer useStatus);
}

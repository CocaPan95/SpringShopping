package com.coca.shoppingsmsservice.service;

public interface CouponHistoryService {
    void updateCouponStatus(Long couponId, Long memberId, Integer useStatus);
}

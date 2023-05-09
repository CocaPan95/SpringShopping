package com.coca.shoppingsmsapi;

import com.coca.shoppingmodel.dto.SmsCouponParam;

public interface SmsCouponHistoryService {
    void updateCouponStatus(Long couponId, Long memberId, Integer useStatus);

    int createCoupon(SmsCouponParam param);

    //优惠券秒杀
    boolean getCouponByMember(String code, Long MemberId);
}

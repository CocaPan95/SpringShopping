package com.coca.shoppingsmsservice.service;

import com.coca.shoppingmodel.dto.SmsCouponParam;

public interface CouponService {
    //创建新的优惠券
    int createCoupon(SmsCouponParam param);

    //优惠券秒杀
    boolean getCouponByMember(String code, Long MemberId);
}

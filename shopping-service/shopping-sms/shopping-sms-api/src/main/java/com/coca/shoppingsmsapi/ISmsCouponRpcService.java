package com.coca.shoppingsmsapi;

import com.coca.shoppingmodel.dto.SmsCouponParam;
import com.coca.shoppingmodel.entity.sms.SmsCoupon;

import java.util.List;

public interface ISmsCouponRpcService {
    void updateCouponStatus(Long couponId, Long memberId, Integer useStatus);

    int createCoupon(SmsCouponParam param);

    //优惠券秒杀
    boolean getCouponByMember(String code, Long MemberId);

    List<SmsCoupon> getAvailableCouponList(Long productId, Long productCategoryId);
}

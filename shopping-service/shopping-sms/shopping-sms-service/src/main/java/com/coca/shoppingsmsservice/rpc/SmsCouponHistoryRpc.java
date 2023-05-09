package com.coca.shoppingsmsservice.rpc;

import com.coca.shoppingmodel.dto.SmsCouponParam;
import com.coca.shoppingsmsapi.SmsCouponHistoryService;
import com.coca.shoppingsmsservice.service.CouponHistoryService;
import com.coca.shoppingsmsservice.service.CouponService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;


@DubboService
public class SmsCouponHistoryRpc implements SmsCouponHistoryService {
    @Autowired
    private CouponHistoryService couponHistoryService;
    @Autowired
    private CouponService couponService;

    public void updateCouponStatus(Long couponId, Long memberId, Integer useStatus) {
        couponHistoryService.updateCouponStatus(couponId, memberId, useStatus);
    }

    public int createCoupon(SmsCouponParam param) {
        return couponService.createCoupon(param);
    }

    public boolean getCouponByMember(String code, Long MemberId) {
        return couponService.getCouponByMember(code, MemberId);
    }
}

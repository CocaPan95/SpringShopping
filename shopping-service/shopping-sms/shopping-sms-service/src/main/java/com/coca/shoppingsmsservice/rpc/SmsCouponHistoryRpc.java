package com.coca.shoppingsmsservice.rpc;

import com.coca.shoppingsmsapi.SmsCouponHistoryService;
import com.coca.shoppingsmsservice.service.CouponHistoryService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;


@DubboService
public class SmsCouponHistoryRpc implements SmsCouponHistoryService {
    @Autowired
    private CouponHistoryService couponHistoryService;
    public void updateCouponStatus(Long couponId, Long memberId, Integer useStatus) {
        couponHistoryService.updateCouponStatus(couponId,memberId,useStatus);
    }
}

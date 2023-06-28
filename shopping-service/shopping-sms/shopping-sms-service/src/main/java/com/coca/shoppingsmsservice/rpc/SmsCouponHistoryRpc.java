package com.coca.shoppingsmsservice.rpc;

import com.coca.shoppingmodel.dto.SmsCouponParam;
import com.coca.shoppingmodel.entity.sms.SmsCoupon;
import com.coca.shoppingsmsapi.ISmsCouponRpcService;
import com.coca.shoppingsmsservice.service.ISmsCouponService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@DubboService
public class SmsCouponHistoryRpc implements ISmsCouponRpcService {
    @Autowired
    private ISmsCouponService couponService;

    public void updateCouponStatus(Long couponId, Long memberId, Integer useStatus) {
        couponService.updateCouponStatus(couponId, memberId, useStatus);
    }

    public int createCoupon(SmsCouponParam param) {
        return couponService.createCoupon(param);
    }

    public boolean getCouponByMember(String code, Long MemberId) {
        return couponService.getCouponByMember(code, MemberId);
    }

    public List<SmsCoupon> getAvailableCouponList(Long productId, Long productCategoryId){
        return couponService.getAvailableCouponList(productId,productCategoryId);
    }
}

package com.coca.shoppingsmsservice.rpc;

import com.coca.shoppingmodel.dto.CartPromotionItem;
import com.coca.shoppingmodel.dto.SmsCouponHistoryDetail;
import com.coca.shoppingsmsapi.IUmsMemberCouponRpcService;
import com.coca.shoppingsmsservice.service.MemberCouponService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class SmsMemberCouponRpc implements IUmsMemberCouponRpcService {

    @Autowired
    private MemberCouponService memberCouponService;
    @Override
    public List<SmsCouponHistoryDetail> listCart(Long MemberId, List<CartPromotionItem> cartItemList, Integer type) {
        return memberCouponService.listCart(MemberId,cartItemList,type);
    }
    @Override
    public void UpdateCouponStatus(Long couponId, Long memberId, Integer useStatus){
         memberCouponService.UpdateCouponStatus(couponId,memberId,useStatus);
    }
}

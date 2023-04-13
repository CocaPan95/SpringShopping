package com.coca.shoppingorderservice.impl;

import com.coca.shoppingmodel.domain.order.OmsCartItem;
import com.coca.shoppingmodel.domain.order.OmsCartItemExample;
import com.coca.shoppingmodel.domain.user.UmsIntegrationConsumeSetting;
import com.coca.shoppingmodel.domain.user.UmsMember;
import com.coca.shoppingmodel.dto.CartPromotionItem;
import com.coca.shoppingmodel.dto.ConfirmOrderResult;
import com.coca.shoppingorderapi.CartItemService;
import com.coca.shoppingorderapi.OrderService;
import com.coca.shoppingorderservice.mapper.OmsCartItemMapper;
import com.coca.shoppingsmsapi.UmsMemberCouponService;
import com.coca.shoppinguserapi.UmsIntegrationConsumeSettingService;
import com.coca.shoppinguserapi.UmsMemberReceiveAddressService;
import com.coca.shoppinguserapi.UmsMemberService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Member;
import java.math.BigDecimal;
import java.util.List;

@DubboService
public class OrderImpl implements OrderService {
    @Autowired
    private CartItemService cartItemService;
    @DubboReference
    private UmsMemberService umsMemberService;
    @DubboReference
    private UmsMemberReceiveAddressService umsMemberReceiveAddressService;
    @DubboReference
    private UmsMemberCouponService umsMemberCouponService;
    @DubboReference
    private UmsIntegrationConsumeSettingService integrationConsumeSettingService;


    //购物车信息生成确认订单
    public ConfirmOrderResult generateConfirmOrder(List<Long> ids) {
        ConfirmOrderResult result = new ConfirmOrderResult();
        //获取当前登录用户
        UmsMember member=umsMemberService.getCurrentMember();
        //根据购物车信息取到购物车列表
        OmsCartItemExample cartItemExample = new OmsCartItemExample();
        cartItemExample.createCriteria().andIdIn(ids);
        List<CartPromotionItem> cartPromotionItemList=cartItemService.GetCartPromotionItemList(member.getId(), ids);
        result.setCartPromotionItemList(cartPromotionItemList);
        //获取收货地址信息
        result.setMemberReceiveAddressList(umsMemberReceiveAddressService.GetMemberReceiveAddressList(member.getId()));
        //获取用户可用优惠券
        result.setCouponHistoryDetailList(umsMemberCouponService.listCart(cartPromotionItemList,1));
        //获取用户积分
        result.setMemberIntegration(member.getIntegration());
        //获取积分使用规则
        UmsIntegrationConsumeSetting integrationConsumeSetting = integrationConsumeSettingService.GetIntegrationConsumeSetting();
        result.setIntegrationConsumeSetting(integrationConsumeSetting);
        //计算总金额、活动优惠，应付金额
        ConfirmOrderResult.CalcAmount calcAmount = calcCartAmount(cartPromotionItemList);
        result.setCalcAmount(calcAmount);
        return result;
    }



    //确认单下单

    //支付回调

    /**
     * 计算购物车中商品的价格
     */
    private ConfirmOrderResult.CalcAmount calcCartAmount(List<CartPromotionItem> cartPromotionItemList) {
        ConfirmOrderResult.CalcAmount calcAmount = new ConfirmOrderResult.CalcAmount();
        calcAmount.setFreightAmount(new BigDecimal(0));
        BigDecimal totalAmount = new BigDecimal("0");
        BigDecimal promotionAmount = new BigDecimal("0");
        for (CartPromotionItem cartPromotionItem : cartPromotionItemList) {
            totalAmount = totalAmount.add(cartPromotionItem.getPrice().multiply(new BigDecimal(cartPromotionItem.getQuantity())));
            promotionAmount = promotionAmount.add(cartPromotionItem.getReduceAmount().multiply(new BigDecimal(cartPromotionItem.getQuantity())));
        }
        calcAmount.setTotalAmount(totalAmount);
        calcAmount.setPromotionAmount(promotionAmount);
        calcAmount.setPayAmount(totalAmount.subtract(promotionAmount));
        return calcAmount;
    }
}

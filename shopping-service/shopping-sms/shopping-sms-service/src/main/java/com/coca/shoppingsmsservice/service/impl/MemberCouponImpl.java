package com.coca.shoppingsmsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coca.shoppingmodel.dto.CartPromotionItem;
import com.coca.shoppingmodel.dto.SmsCouponHistoryDetail;
import com.coca.shoppingmodel.entity.sms.SmsCoupon;
import com.coca.shoppingmodel.entity.sms.SmsCouponHistory;
import com.coca.shoppingmodel.entity.sms.SmsCouponProductCategoryRelation;
import com.coca.shoppingmodel.entity.sms.SmsCouponProductRelation;
import com.coca.shoppingsmsservice.mapper.SmsCouponHistoryMapper;
import com.coca.shoppingsmsservice.service.MemberCouponService;
import com.coca.shoppinguserapi.IUmsMemberRpcService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MemberCouponImpl implements MemberCouponService {
    @DubboReference
    private IUmsMemberRpcService umsMemberService;
    @Autowired
    private SmsCouponHistoryMapper couponHistoryMapper;


    @Override
    public List<SmsCouponHistoryDetail> listCart(Long MemberId, List<CartPromotionItem> cartItemList, Integer type) {
        LocalDateTime now=LocalDateTime.now();
        //获取该用户所有优惠券
        List<SmsCouponHistoryDetail> allList=couponHistoryMapper.getDetailList(MemberId);
        //根据优惠券使用类型来判断优惠券是否可用
        List<SmsCouponHistoryDetail> enableList = new ArrayList<>();
        List<SmsCouponHistoryDetail> disableList = new ArrayList<>();
        for(SmsCouponHistoryDetail couponHistoryDetail:allList){
            Integer useType=couponHistoryDetail.getCoupon().getUseType();
            BigDecimal minPoint=couponHistoryDetail.getCoupon().getMinPoint();
            LocalDateTime endTime=couponHistoryDetail.getCoupon().getEndTime();
            if(useType.equals(0)){
                //全场通用
                //判断是否满足优惠起点
                //计算购物车商品的总价
                BigDecimal totalAmount=calcTotalAmount(cartItemList);
                if(now.isBefore(endTime)&&totalAmount.subtract(minPoint).intValue()>=0){
                    enableList.add(couponHistoryDetail);
                }else{
                    disableList.add(couponHistoryDetail);
                }
            } else if (useType.equals(1)) {
                //指定商品分类
                //计算指定分类商品的总价
                List<Long> productCategoryIds = new ArrayList<>();
                for (SmsCouponProductCategoryRelation categoryRelation : couponHistoryDetail.getCategoryRelationList()) {
                    productCategoryIds.add(categoryRelation.getProductCategoryId());
                }
                BigDecimal totalAmount = calcTotalAmountByproductCategoryId(cartItemList,productCategoryIds);
                if(now.isBefore(endTime)&&totalAmount.intValue()>0&&totalAmount.subtract(minPoint).intValue()>=0){
                    enableList.add(couponHistoryDetail);
                }else{
                    disableList.add(couponHistoryDetail);
                }
            } else if (useType.equals(2)) {
                //指定商品
                //计算指定商品的总价
                List<Long> productIds = new ArrayList<>();
                for (SmsCouponProductRelation productRelation : couponHistoryDetail.getProductRelationList()) {
                    productIds.add(productRelation.getProductId());
                }
                BigDecimal totalAmount = calcTotalAmountByProductId(cartItemList,productIds);
                if(now.isBefore(endTime)&&totalAmount.intValue()>0&&totalAmount.subtract(minPoint).intValue()>=0){
                    enableList.add(couponHistoryDetail);
                }else{
                    disableList.add(couponHistoryDetail);
                }
            }
        }
        if(type.equals(1)){
            return enableList;
        }else{
            return disableList;
        }
    }

    public void UpdateCouponStatus(Long couponId, Long memberId, Integer useStatus){
        if (couponId == null) return;
        //查询第一张优惠券
        QueryWrapper<SmsCouponHistory> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("member_id",memberId);
        queryWrapper.eq("use_status",useStatus == 0 ? 1 : 0);
        List<SmsCouponHistory> couponHistoryList = couponHistoryMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(couponHistoryList)) {
            SmsCouponHistory couponHistory = couponHistoryList.get(0);
            couponHistory.setUseTime(LocalDateTime.now());
            couponHistory.setUseStatus(useStatus);
            couponHistoryMapper.updateById(couponHistory);
        }
    }
    private BigDecimal calcTotalAmount(List<CartPromotionItem> cartItemList){
        BigDecimal total = new BigDecimal("0");
        for (CartPromotionItem item : cartItemList) {
            BigDecimal realPrice = item.getPrice().subtract(item.getReduceAmount());
            total=total.add(realPrice.multiply(new BigDecimal(item.getQuantity())));
        }
        return total;
    }

    private BigDecimal calcTotalAmountByproductCategoryId(List<CartPromotionItem> cartItemList,List<Long> productCategoryIds) {
        BigDecimal total = new BigDecimal("0");
        for (CartPromotionItem item : cartItemList) {
            if(productCategoryIds.contains(item.getProductCategoryId())){
                BigDecimal realPrice = item.getPrice().subtract(item.getReduceAmount());
                total=total.add(realPrice.multiply(new BigDecimal(item.getQuantity())));
            }
        }
        return total;
    }

    private BigDecimal calcTotalAmountByProductId(List<CartPromotionItem> cartItemList,List<Long> productIds) {
        BigDecimal total = new BigDecimal("0");
        for (CartPromotionItem item : cartItemList) {
            if(productIds.contains(item.getProductId())){
                BigDecimal realPrice = item.getPrice().subtract(item.getReduceAmount());
                total=total.add(realPrice.multiply(new BigDecimal(item.getQuantity())));
            }
        }
        return total;
    }
}

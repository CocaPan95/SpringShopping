package com.coca.shoppingmodel.dto;


import com.coca.shoppingmodel.entity.sms.SmsCoupon;
import com.coca.shoppingmodel.entity.sms.SmsCouponHistory;
import com.coca.shoppingmodel.entity.sms.SmsCouponProductCategoryRelation;
import com.coca.shoppingmodel.entity.sms.SmsCouponProductRelation;

import java.io.Serializable;
import java.util.List;

/**
 * 优惠券领取历史详情封装
 * Created by macro on 2018/8/29.
 */
public class SmsCouponHistoryDetail extends SmsCouponHistory implements Serializable {
    //相关优惠券信息
    private SmsCoupon coupon;
    //优惠券关联商品
    private List<SmsCouponProductRelation> productRelationList;

    //优惠券关联商品分类
    private List<SmsCouponProductCategoryRelation> categoryRelationList;

    public SmsCoupon getCoupon() {
        return coupon;
    }

    public void setCoupon(SmsCoupon coupon) {
        this.coupon = coupon;
    }

    public List<SmsCouponProductRelation> getProductRelationList() {
        return productRelationList;
    }

    public void setProductRelationList(List<SmsCouponProductRelation> productRelationList) {
        this.productRelationList = productRelationList;
    }

    public List<SmsCouponProductCategoryRelation> getCategoryRelationList() {
        return categoryRelationList;
    }

    public void setCategoryRelationList(List<SmsCouponProductCategoryRelation> categoryRelationList) {
        this.categoryRelationList = categoryRelationList;
    }
}

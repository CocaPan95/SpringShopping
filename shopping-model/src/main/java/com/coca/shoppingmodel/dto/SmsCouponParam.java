package com.coca.shoppingmodel.dto;

import com.coca.shoppingmodel.entity.sms.SmsCoupon;
import com.coca.shoppingmodel.entity.sms.SmsCouponProductCategoryRelation;
import com.coca.shoppingmodel.entity.sms.SmsCouponProductRelation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class SmsCouponParam extends SmsCoupon implements Serializable {
    private List<SmsCouponProductCategoryRelation> couponProductCategoryRelations;
    private List<SmsCouponProductRelation> couponProductRelations;
}

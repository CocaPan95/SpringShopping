package com.coca.shoppingmodel.dto;

import com.coca.shoppingmodel.entity.sms.SmsCoupon;
import com.coca.shoppingmodel.entity.sms.SmsCouponProductCategoryRelation;
import com.coca.shoppingmodel.entity.sms.SmsCouponProductRelation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@ApiModel("优惠券信息类")
@Data
@EqualsAndHashCode(callSuper = false)
public class SmsCouponParam extends SmsCoupon implements Serializable {
    @ApiModelProperty("优惠券关联产品分类")
    private List<SmsCouponProductCategoryRelation> couponProductCategoryRelations;
    @ApiModelProperty("优惠券关联产品")
    private List<SmsCouponProductRelation> couponProductRelations;
}

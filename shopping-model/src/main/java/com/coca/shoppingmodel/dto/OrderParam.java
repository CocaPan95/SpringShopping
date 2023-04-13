package com.coca.shoppingmodel.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrderParam {
    //@ApiModelProperty("收货地址ID")
    private Long memberReceiveAddressId;
    //@ApiModelProperty("优惠券ID")
    private Long couponId;
    //@ApiModelProperty("使用的积分数")
    private Integer useIntegration;
    //@ApiModelProperty("支付方式")
    private Integer payType;
    //@ApiModelProperty("被选中的购物车商品ID")
    private List<Long> cartIds;
}

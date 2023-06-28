package com.coca.shoppingmodel.dto;

import com.coca.shoppingmodel.entity.pms.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 创建和修改商品时使用的参数
 * Created by macro on 2018/4/26.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PmsProductParam extends PmsProduct implements Serializable {
    //@ApiModelProperty("商品阶梯价格设置")
    private List<PmsProductLadder> productLadderList;
    //@ApiModelProperty("商品满减价格设置")
    private List<PmsProductFullReduction> productFullReductionList;
    //@ApiModelProperty("商品会员价格设置")
    private List<PmsMemberPrice> memberPriceList;
    //@ApiModelProperty("商品的sku库存信息")
    private List<PmsSkuStock> skuStockList;
    //@ApiModelProperty("商品参数及自定义规格属性")
    private List<PmsProductAttributeValue> productAttributeValueList;

}

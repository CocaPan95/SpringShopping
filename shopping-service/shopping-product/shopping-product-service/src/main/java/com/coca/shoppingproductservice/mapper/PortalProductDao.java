package com.coca.shoppingproductservice.mapper;

import com.coca.shoppingmodel.domain.sms.SmsCoupon;
import com.coca.shoppingmodel.dto.PromotionProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PortalProductDao {
    List<PromotionProduct> getPromotionProductList(@Param("ids") List<Long> ids);
    List<SmsCoupon> getAvailableCouponList(@Param("productId") Long productId, @Param("productCategoryId")Long productCategoryId);
}

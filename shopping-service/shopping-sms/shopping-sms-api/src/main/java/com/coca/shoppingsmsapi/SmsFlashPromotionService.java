package com.coca.shoppingsmsapi;

import com.coca.shoppingmodel.dto.PmsPortalProductDetail;

public interface SmsFlashPromotionService {
    PmsPortalProductDetail getFlashPromotionProduct(Long productId);
}

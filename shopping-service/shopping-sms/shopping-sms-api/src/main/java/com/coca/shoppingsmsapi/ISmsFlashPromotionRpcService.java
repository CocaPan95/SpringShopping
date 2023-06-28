package com.coca.shoppingsmsapi;

import com.coca.shoppingmodel.dto.PmsPortalProductDetail;

public interface ISmsFlashPromotionRpcService {
    PmsPortalProductDetail getFlashPromotionProduct(Long productId);
}

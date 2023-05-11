package com.coca.shoppingsmsservice.rpc;

import com.coca.shoppingmodel.dto.PmsPortalProductDetail;
import com.coca.shoppingsmsapi.SmsFlashPromotionService;
import com.coca.shoppingsmsservice.service.FlashPromotionService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@DubboService
public class SmsFlashPromotionRpc implements SmsFlashPromotionService {
    @Autowired
    private FlashPromotionService flashPromotionService;

    public PmsPortalProductDetail getFlashPromotionProduct(Long productId) {
        return flashPromotionService.getFlashPromotionProduct(productId);
    }
}

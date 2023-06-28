package com.coca.shoppingsmsservice.rpc;

import com.coca.shoppingmodel.dto.PmsPortalProductDetail;
import com.coca.shoppingsmsapi.ISmsFlashPromotionRpcService;
import com.coca.shoppingsmsservice.service.ISmsFlashPromotionService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@DubboService
public class SmsFlashPromotionRpc implements ISmsFlashPromotionRpcService {
    @Autowired
    private ISmsFlashPromotionService flashPromotionService;

    public PmsPortalProductDetail getFlashPromotionProduct(Long productId) {
        return flashPromotionService.getFlashPromotionProduct(productId);
    }
}

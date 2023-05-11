package com.coca.shoppingsmsservice.service;

import com.coca.shoppingmodel.domain.sms.SmsFlashPromotion;
import com.coca.shoppingmodel.domain.sms.SmsFlashPromotionProductRelation;
import com.coca.shoppingmodel.domain.sms.SmsFlashPromotionSession;
import com.coca.shoppingmodel.dto.PmsPortalProductDetail;

public interface FlashPromotionService {
    int createFlashPromotion(SmsFlashPromotion param);
    int createFlashPromotionSession(SmsFlashPromotionSession param);
    int createFlashPromotionProductRelation(SmsFlashPromotionProductRelation param);
    PmsPortalProductDetail getFlashPromotionProduct(Long productId);
}

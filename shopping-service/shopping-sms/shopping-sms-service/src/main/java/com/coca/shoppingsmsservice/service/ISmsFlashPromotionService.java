package com.coca.shoppingsmsservice.service;

import com.coca.shoppingmodel.dto.PmsPortalProductDetail;
import com.coca.shoppingmodel.entity.sms.SmsFlashPromotion;
import com.baomidou.mybatisplus.extension.service.IService;
import com.coca.shoppingmodel.entity.sms.SmsFlashPromotionProductRelation;
import com.coca.shoppingmodel.entity.sms.SmsFlashPromotionSession;

/**
 * <p>
 * 限时购表 服务类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
public interface ISmsFlashPromotionService extends IService<SmsFlashPromotion> {
    int createFlashPromotion(SmsFlashPromotion param);
    int createFlashPromotionSession(SmsFlashPromotionSession param);
    int createFlashPromotionProductRelation(SmsFlashPromotionProductRelation param);
    PmsPortalProductDetail getFlashPromotionProduct(Long productId);
}

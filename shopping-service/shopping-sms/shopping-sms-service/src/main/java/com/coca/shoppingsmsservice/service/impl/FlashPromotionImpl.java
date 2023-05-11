package com.coca.shoppingsmsservice.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.coca.shoppingcommon.service.RedisService;
import com.coca.shoppingmodel.domain.sms.SmsFlashPromotion;
import com.coca.shoppingmodel.domain.sms.SmsFlashPromotionProductRelation;
import com.coca.shoppingmodel.domain.sms.SmsFlashPromotionSession;
import com.coca.shoppingmodel.dto.PmsPortalProductDetail;
import com.coca.shoppingproductapi.PmsProductService;
import com.coca.shoppingsmsservice.mapper.SmsFlashPromotionMapper;
import com.coca.shoppingsmsservice.mapper.SmsFlashPromotionProductRelationMapper;
import com.coca.shoppingsmsservice.mapper.SmsFlashPromotionSessionMapper;
import com.coca.shoppingsmsservice.service.FlashPromotionService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FlashPromotionImpl implements FlashPromotionService {
    @Autowired
    private SmsFlashPromotionMapper flashPromotionMapper;
    @Autowired
    private SmsFlashPromotionSessionMapper flashPromotionSessionMapper;
    @Autowired
    private SmsFlashPromotionProductRelationMapper flashPromotionProductRelationMapper;
    @DubboReference
    private PmsProductService pmsProductService;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private RedisService redisService;

    @Override
    public int createFlashPromotion(SmsFlashPromotion param) {
        int count = flashPromotionMapper.insert(param);
        return count;
    }

    @Override
    public int createFlashPromotionSession(SmsFlashPromotionSession param) {
        int count = flashPromotionSessionMapper.insert(param);
        return count;
    }

    @Override
    public int createFlashPromotionProductRelation(SmsFlashPromotionProductRelation param) {
        int count = flashPromotionProductRelationMapper.insert(param);
        return count;
    }

    @Override
    public PmsPortalProductDetail getFlashPromotionProduct(Long productId) {
        String key = "flashpromotionproduct_" + productId;
        PmsPortalProductDetail detail = (PmsPortalProductDetail) redisService.get(key);
        if (detail != null) {
            return detail;
        }
        RLock lock = redissonClient.getLock(key + "_lock");
        if (lock.tryLock()) {
            try {
                detail = (PmsPortalProductDetail) redisService.get(key);
                if (detail == null) {
                    detail = pmsProductService.getProductDetail(productId);
                    redisService.set(key, detail, 3600);
                }
            }finally {
                if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }

        }
        return detail;
    }

}

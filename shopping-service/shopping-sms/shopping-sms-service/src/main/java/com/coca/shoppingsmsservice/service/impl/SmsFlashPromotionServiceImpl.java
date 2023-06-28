package com.coca.shoppingsmsservice.service.impl;

import com.coca.shoppingcommon.service.RedisService;
import com.coca.shoppingmodel.dto.PmsPortalProductDetail;
import com.coca.shoppingmodel.entity.sms.SmsFlashPromotion;
import com.coca.shoppingmodel.entity.sms.SmsFlashPromotionProductRelation;
import com.coca.shoppingmodel.entity.sms.SmsFlashPromotionSession;
import com.coca.shoppingproductapi.IPmsProductRpcService;
import com.coca.shoppingsmsservice.mapper.SmsFlashPromotionMapper;
import com.coca.shoppingsmsservice.mapper.SmsFlashPromotionProductRelationMapper;
import com.coca.shoppingsmsservice.mapper.SmsFlashPromotionSessionMapper;
import com.coca.shoppingsmsservice.service.ISmsFlashPromotionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboReference;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 限时购表 服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
@Service
public class SmsFlashPromotionServiceImpl extends ServiceImpl<SmsFlashPromotionMapper, SmsFlashPromotion> implements ISmsFlashPromotionService {
    @Autowired
    private SmsFlashPromotionSessionMapper flashPromotionSessionMapper;
    @Autowired
    private SmsFlashPromotionProductRelationMapper flashPromotionProductRelationMapper;
    @DubboReference
    private IPmsProductRpcService pmsProductService;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private RedisService redisService;

    @Override
    public int createFlashPromotion(SmsFlashPromotion param) {
        int count = baseMapper.insert(param);
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

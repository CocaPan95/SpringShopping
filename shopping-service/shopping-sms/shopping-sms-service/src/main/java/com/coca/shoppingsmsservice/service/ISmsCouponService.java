package com.coca.shoppingsmsservice.service;

import com.coca.shoppingmodel.dto.SmsCouponParam;
import com.coca.shoppingmodel.entity.sms.SmsCoupon;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 优惠卷表 服务类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
public interface ISmsCouponService extends IService<SmsCoupon> {
    //创建新的优惠券
    int createCoupon(SmsCouponParam param);

    //优惠券秒杀
    boolean getCouponByMember(String code, Long MemberId);

    void updateCouponStatus(Long couponId, Long memberId, Integer useStatus);
    List<SmsCoupon> getAvailableCouponList(Long productId, Long productCategoryId);
}

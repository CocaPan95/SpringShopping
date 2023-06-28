package com.coca.shoppingsmsservice.mapper;

import com.coca.shoppingmodel.dto.SmsCouponHistoryDetail;
import com.coca.shoppingmodel.entity.sms.SmsCoupon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会员优惠券领取历史自定义Dao
 * Created by macro on 2018/8/29.
 */
public interface SmsCouponHistoryDao {
    List<SmsCouponHistoryDetail> getDetailList(@Param("memberId") Long memberId);
    List<SmsCoupon> getCouponList(@Param("memberId") Long memberId, @Param("useStatus")Integer useStatus);

    Integer getCouponByMember(@Param("memberId") Long memberId,@Param("code")String code);
    List<SmsCoupon> getAvailableCouponList(@Param("productId") Long productId, @Param("productCategoryId")Long productCategoryId);
}

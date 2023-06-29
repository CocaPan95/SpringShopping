package com.coca.shoppingsmsservice.mapper;

import com.coca.shoppingmodel.dto.SmsCouponHistoryDetail;
import com.coca.shoppingmodel.entity.sms.SmsCoupon;
import com.coca.shoppingmodel.entity.sms.SmsCouponHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 优惠券使用、领取历史表 Mapper 接口
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
public interface SmsCouponHistoryMapper extends BaseMapper<SmsCouponHistory> {
    List<SmsCouponHistoryDetail> getDetailList(@Param("memberId") Long memberId);
    List<SmsCoupon> getCouponList(@Param("memberId") Long memberId, @Param("useStatus")Integer useStatus);

    Integer getCouponByMember(@Param("memberId") Long memberId,@Param("code")String code);
    List<SmsCoupon> getAvailableCouponList(@Param("productId") Long productId, @Param("productCategoryId")Long productCategoryId);
}

package com.coca.shoppingsmsservice.service.impl;

import com.coca.shoppingmodel.domain.sms.SmsCoupon;
import com.coca.shoppingmodel.domain.sms.SmsCouponHistory;
import com.coca.shoppingmodel.domain.sms.SmsCouponHistoryExample;
import com.coca.shoppingsmsservice.mapper.SmsCouponHistoryDao;
import com.coca.shoppingsmsservice.mapper.SmsCouponHistoryMapper;
import com.coca.shoppingsmsservice.service.CouponHistoryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class CouponHistoryImpl implements CouponHistoryService {
    @Autowired
    private SmsCouponHistoryMapper couponHistoryMapper;

    @Autowired
    private SmsCouponHistoryDao couponHistoryDao;
    /**
     * 将优惠券信息更改为指定状态
     *
     * @param couponId  优惠券id
     * @param memberId  会员id
     * @param useStatus 0->未使用；1->已使用
     */
    public void updateCouponStatus(Long couponId, Long memberId, Integer useStatus) {
        if (couponId == null) return;
        //查询第一张优惠券
        SmsCouponHistoryExample example = new SmsCouponHistoryExample();
        example.createCriteria().andMemberIdEqualTo(memberId)
                .andCouponIdEqualTo(couponId).andUseStatusEqualTo(useStatus == 0 ? 1 : 0);
        List<SmsCouponHistory> couponHistoryList = couponHistoryMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(couponHistoryList)) {
            SmsCouponHistory couponHistory = couponHistoryList.get(0);
            couponHistory.setUseTime(new Date());
            couponHistory.setUseStatus(useStatus);
            couponHistoryMapper.updateByPrimaryKeySelective(couponHistory);
        }
    }

    public List<SmsCoupon> getAvailableCouponList(Long productId, Long productCategoryId) {
        return couponHistoryDao.getAvailableCouponList(productId,productCategoryId);
    }
}

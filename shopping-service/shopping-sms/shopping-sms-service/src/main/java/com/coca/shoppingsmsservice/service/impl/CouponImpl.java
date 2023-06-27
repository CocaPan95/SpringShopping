package com.coca.shoppingsmsservice.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.coca.shoppingcommon.exception.Asserts;
import com.coca.shoppingmodel.domain.sms.*;
import com.coca.shoppingmodel.dto.SmsCouponParam;
import com.coca.shoppingmodel.entity.ums.UmsMember;
import com.coca.shoppingsmsservice.mapper.*;
import com.coca.shoppingsmsservice.service.CouponService;
import com.coca.shoppinguserapi.IUmsMemberRpcService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Service
public class CouponImpl implements CouponService {

    @Autowired
    private SmsCouponMapper smsCouponMapper;
    @Autowired
    private SmsCouponHistoryMapper couponHistoryMapper;
    @Autowired
    private SmsCouponProductCategoryRelationMapper couponProductCategoryRelationMapper;
    @Autowired
    private SmsCouponProductRelationMapper couponProductRelationMapper;
    @Autowired
    private SmsCouponHistoryDao couponHistoryDao;
    @DubboReference
    private IUmsMemberRpcService umsMemberService;

    @Transactional
    public int createCoupon(SmsCouponParam param) {
        param.setCount(param.getPublishCount());
        param.setUseCount(0);
        param.setReceiveCount(0);
        int count = smsCouponMapper.insert(param);
        if (param.getUseType().equals(2)) {
            for(SmsCouponProductRelation item:param.getCouponProductRelations()){
                item.setCouponId(param.getId());
                couponProductRelationMapper.insert(item);
            }
        }
        if (param.getUseType().equals(1)) {
            for(SmsCouponProductCategoryRelation item:param.getCouponProductCategoryRelations()){
                item.setCouponId(param.getId());
                couponProductCategoryRelationMapper.insert(item);
            }
        }
        return count;
    }

    @Transactional
    public boolean getCouponByMember(String code, Long MemberId) {
        //获取当前登录用户
        UmsMember member = umsMemberService.getById(MemberId);
        SmsCouponExample couponExample = new SmsCouponExample();
        couponExample.createCriteria().andCodeEqualTo(code);
        List<SmsCoupon> couponList = smsCouponMapper.selectByExample(couponExample);
        if (CollUtil.isEmpty(couponList)) {
            Asserts.fail("优惠券不存在！");
        }
        SmsCoupon coupon = couponList.get(0);
        if (new Date().before(coupon.getEnableTime())) {
            Asserts.fail("优惠券还未开始发放！");
        }
        if (coupon.getCount() <= 0) {
            Asserts.fail("优惠券抢光了！");
        }
        SmsCouponHistoryExample couponHistoryExample = new SmsCouponHistoryExample();
        couponHistoryExample.createCriteria().andCouponCodeEqualTo(code).andMemberIdEqualTo(MemberId);
        List<SmsCouponHistory> couponHistoryList = couponHistoryMapper.selectByExample(couponHistoryExample);
        if (couponHistoryList.stream().count() >= coupon.getPerLimit()) {
            Asserts.fail("优惠券领取超出限制！");
        }
        if (couponHistoryDao.getCouponByMember(MemberId, code) > 0) {
            SmsCouponHistory smsCouponHistory = new SmsCouponHistory();
            smsCouponHistory.setCouponId(coupon.getId());
            smsCouponHistory.setCouponCode(code);
            smsCouponHistory.setMemberId(member.getId());
            smsCouponHistory.setMemberNickname(member.getNickname());
            smsCouponHistory.setGetType(1);
            smsCouponHistory.setCreateTime(new Date());
            couponHistoryMapper.insert(smsCouponHistory);
        } else {
            Asserts.fail("优惠券不存在！");
        }
        return true;
    }

}

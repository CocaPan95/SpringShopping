package com.coca.shoppingsmsservice.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coca.shoppingcommon.exception.Asserts;
import com.coca.shoppingmodel.dto.SmsCouponParam;
import com.coca.shoppingmodel.entity.sms.SmsCoupon;
import com.coca.shoppingmodel.entity.sms.SmsCouponHistory;
import com.coca.shoppingmodel.entity.sms.SmsCouponProductCategoryRelation;
import com.coca.shoppingmodel.entity.sms.SmsCouponProductRelation;
import com.coca.shoppingmodel.entity.ums.UmsMember;
import com.coca.shoppingsmsservice.mapper.*;
import com.coca.shoppingsmsservice.service.ISmsCouponService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coca.shoppinguserapi.IUmsMemberRpcService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.management.Query;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 优惠卷表 服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
@Service
public class SmsCouponServiceImpl extends ServiceImpl<SmsCouponMapper, SmsCoupon> implements ISmsCouponService {

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
        int count = baseMapper.insert(param);
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
        QueryWrapper<SmsCoupon> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("code",code);
        List<SmsCoupon> couponList = baseMapper.selectList(queryWrapper);
        if (CollUtil.isEmpty(couponList)) {
            Asserts.fail("优惠券不存在！");
        }
        SmsCoupon coupon = couponList.get(0);
        if (LocalDateTime.now().isBefore(coupon.getEnableTime())) {
            Asserts.fail("优惠券还未开始发放！");
        }
        if (coupon.getCount() <= 0) {
            Asserts.fail("优惠券抢光了！");
        }

        QueryWrapper<SmsCouponHistory> couponHistoryQueryWrapper=new QueryWrapper<>();
        couponHistoryQueryWrapper.eq("code",code);
        couponHistoryQueryWrapper.eq("member_id",MemberId);
        List<SmsCouponHistory> couponHistoryList = couponHistoryMapper.selectList(couponHistoryQueryWrapper);
        if (couponHistoryList.stream().count() >= coupon.getPerLimit()) {
            Asserts.fail("优惠券领取超出限制！");
        }
        LocalDateTime now=LocalDateTime.now();
        if (couponHistoryDao.getCouponByMember(MemberId, code) > 0) {
            SmsCouponHistory smsCouponHistory = new SmsCouponHistory();
            smsCouponHistory.setCouponId(coupon.getId());
            smsCouponHistory.setCouponCode(code);
            smsCouponHistory.setMemberId(member.getId());
            smsCouponHistory.setMemberNickname(member.getNickname());
            smsCouponHistory.setGetType(1);
            smsCouponHistory.setCreateTime(now);
            couponHistoryMapper.insert(smsCouponHistory);
        } else {
            Asserts.fail("优惠券不存在！");
        }
        return true;
    }

    public void updateCouponStatus(Long couponId, Long memberId, Integer useStatus) {
        if (couponId == null) return;
        //查询第一张优惠券
        QueryWrapper<SmsCouponHistory> couponHistoryQueryWrapper = new QueryWrapper<>();
        couponHistoryQueryWrapper.eq("member_id", memberId);
        couponHistoryQueryWrapper.eq("use_status", useStatus == 0 ? 1 : 0);
        List<SmsCouponHistory> couponHistoryList = couponHistoryMapper.selectList(couponHistoryQueryWrapper);
        LocalDateTime now=LocalDateTime.now();
        if (!CollectionUtils.isEmpty(couponHistoryList)) {
            SmsCouponHistory couponHistory = couponHistoryList.get(0);
            couponHistory.setUseTime(now);
            couponHistory.setUseStatus(useStatus);
            couponHistoryMapper.updateById(couponHistory);
        }
    }

    public List<SmsCoupon> getAvailableCouponList(Long productId, Long productCategoryId) {
        return couponHistoryDao.getAvailableCouponList(productId,productCategoryId);
    }
}

package com.coca.shoppingorderservice.impl;

import cn.hutool.core.collection.CollUtil;
import com.coca.shoppingmodel.domain.order.OmsCartItem;
import com.coca.shoppingmodel.domain.order.OmsCartItemExample;
import com.coca.shoppingmodel.domain.user.UmsMember;
import com.coca.shoppingmodel.dto.CartPromotionItem;
import com.coca.shoppingorderapi.OmsCartItemService;
import com.coca.shoppingorderservice.mapper.OmsCartItemMapper;
import com.coca.shoppingsmsapi.SmsPromotionService;
import com.coca.shoppinguserapi.UmsMemberService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@DubboService
@Service
public class OmsOmsCartItemImpl implements OmsCartItemService {
    @Autowired
    private OmsCartItemMapper omsCartItemMapper;
    @Autowired
    private OmsCartItemMapper cartItemMapper;
    @DubboReference
    private UmsMemberService umsMemberService;
    @DubboReference
    private SmsPromotionService smsPromotionService;

    //获取包含优惠活动的购物车列表
    @Override
    public List<CartPromotionItem> GetCartPromotionItemList(Long memberId, List<Long> cartItemIds) {
        //获取购物车列表
        List<OmsCartItem> cartItemList = GetOmsCartItemList(memberId);
        if (CollUtil.isNotEmpty(cartItemIds)) {
            cartItemList = cartItemList.stream().filter(item -> cartItemIds.contains(item.getId())).collect(Collectors.toList());
        }
        //计算购物车优惠
        List<CartPromotionItem> result = new ArrayList<>();
        if (CollUtil.isNotEmpty(cartItemList)) {
            result = smsPromotionService.calcCartPromotion(cartItemList);
        }
        return result;
    }

    @Override
    public List<OmsCartItem> GetOmsCartItemList(Long memberId) {
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria().andMemberIdEqualTo(memberId);
        return omsCartItemMapper.selectByExample(example);
    }
    @Override
    public int add(Long memberId,OmsCartItem cartItem) {
        int count;
        UmsMember currentMember =umsMemberService.getById(memberId);
        cartItem.setMemberId(currentMember.getId());
        cartItem.setMemberNickname(currentMember.getNickname());
        cartItem.setDeleteStatus(0);
        OmsCartItem existCartItem = getCartItem(cartItem);
        if (existCartItem == null) {
            cartItem.setCreateDate(new Date());
            count = cartItemMapper.insert(cartItem);
        } else {
            cartItem.setModifyDate(new Date());
            existCartItem.setQuantity(existCartItem.getQuantity() + cartItem.getQuantity());
            count = cartItemMapper.updateByPrimaryKey(existCartItem);
        }
        return count;
    }


    /**
     * 根据会员id,商品id和规格获取购物车中商品
     */
    private OmsCartItem getCartItem(OmsCartItem cartItem) {
        OmsCartItemExample example = new OmsCartItemExample();
        OmsCartItemExample.Criteria criteria = example.createCriteria().andMemberIdEqualTo(cartItem.getMemberId())
                .andProductIdEqualTo(cartItem.getProductId()).andDeleteStatusEqualTo(0);
        if (cartItem.getProductSkuId()!=null) {
            criteria.andProductSkuIdEqualTo(cartItem.getProductSkuId());
        }
        List<OmsCartItem> cartItemList = cartItemMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(cartItemList)) {
            return cartItemList.get(0);
        }
        return null;
    }
    @Override
    public int delete(Long memberId, List<Long> ids) {
        OmsCartItem record = new OmsCartItem();
        record.setDeleteStatus(1);
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria().andIdIn(ids).andMemberIdEqualTo(memberId);
        return omsCartItemMapper.updateByExampleSelective(record, example);
    }
}

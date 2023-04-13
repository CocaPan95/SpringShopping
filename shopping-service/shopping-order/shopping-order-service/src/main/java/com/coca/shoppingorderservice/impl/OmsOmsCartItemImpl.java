package com.coca.shoppingorderservice.impl;

import cn.hutool.core.collection.CollUtil;
import com.coca.shoppingmodel.domain.order.OmsCartItem;
import com.coca.shoppingmodel.domain.order.OmsCartItemExample;
import com.coca.shoppingmodel.dto.CartPromotionItem;
import com.coca.shoppingorderapi.OmsCartItemService;
import com.coca.shoppingorderservice.mapper.OmsCartItemMapper;
import com.coca.shoppingsmsapi.SmsPromotionService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@DubboService
@Service
public class OmsOmsCartItemImpl implements OmsCartItemService {
    @Autowired
    private OmsCartItemMapper omsCartItemMapper;

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
    public int delete(Long memberId, List<Long> ids) {
        OmsCartItem record = new OmsCartItem();
        record.setDeleteStatus(1);
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria().andIdIn(ids).andMemberIdEqualTo(memberId);
        return omsCartItemMapper.updateByExampleSelective(record, example);
    }
}

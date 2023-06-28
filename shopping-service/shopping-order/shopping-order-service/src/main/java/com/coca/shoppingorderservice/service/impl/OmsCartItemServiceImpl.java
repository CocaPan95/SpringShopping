package com.coca.shoppingorderservice.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coca.shoppingmodel.dto.CartPromotionItem;
import com.coca.shoppingmodel.entity.oms.OmsCartItem;
import com.coca.shoppingmodel.entity.ums.UmsMember;
import com.coca.shoppingorderservice.mapper.OmsCartItemMapper;
import com.coca.shoppingorderservice.service.IOmsCartItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coca.shoppingsmsapi.ISmsPromotionRpcService;
import com.coca.shoppinguserapi.IUmsMemberRpcService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.management.Query;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-28
 */
@Service
public class OmsCartItemServiceImpl extends ServiceImpl<OmsCartItemMapper, OmsCartItem> implements IOmsCartItemService {

    @DubboReference
    private IUmsMemberRpcService umsMemberService;
    @DubboReference
    private ISmsPromotionRpcService smsPromotionService;

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
        QueryWrapper<OmsCartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id", memberId);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public int add(Long memberId, OmsCartItem cartItem) {
        int count;
        UmsMember currentMember = umsMemberService.getById(memberId);
        cartItem.setMemberId(currentMember.getId());
        cartItem.setMemberNickname(currentMember.getNickname());
        cartItem.setDeleteStatus(0);
        OmsCartItem existCartItem = getCartItem(cartItem);
        if (existCartItem == null) {
            cartItem.setCreateDate(LocalDateTime.now());
            count = baseMapper.insert(cartItem);
        } else {
            cartItem.setModifyDate(LocalDateTime.now());
            existCartItem.setQuantity(existCartItem.getQuantity() + cartItem.getQuantity());
            count = baseMapper.updateById(existCartItem);
        }
        return count;
    }

    private OmsCartItem getCartItem(OmsCartItem cartItem) {
        QueryWrapper<OmsCartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id", cartItem.getMemberId());
        queryWrapper.eq("product_id", cartItem.getProductId());
        queryWrapper.eq("delete_status", 0);
        if (cartItem.getProductSkuId() != null) {
            queryWrapper.eq("product_sku_id", cartItem.getProductSkuId());
        }
        List<OmsCartItem> cartItemList = baseMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(cartItemList)) {
            return cartItemList.get(0);
        }
        return null;
    }

    @Override
    public int delete(Long memberId, List<Long> ids) {
        int count = 0;
        for (Long id : ids) {
            OmsCartItem record = baseMapper.selectById(id);
            record.setDeleteStatus(1);
            if(baseMapper.updateById(record)>0){
                count++;
            }
        }
        return count;
    }
}

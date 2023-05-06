package com.coca.shoppinguserservice.service;

import com.coca.shoppingmodel.domain.user.UmsMemberReceiveAddress;

import java.util.List;

public interface MemberReceiveAddressService {
    List<UmsMemberReceiveAddress> GetMemberReceiveAddressList(Long memberId);

    /**
     * 获取地址详情
     * @param id 地址id
     */
    UmsMemberReceiveAddress getItem(Long MemberId,Long id);
}

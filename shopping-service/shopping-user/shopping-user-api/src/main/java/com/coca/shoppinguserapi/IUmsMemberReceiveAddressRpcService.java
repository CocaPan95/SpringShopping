package com.coca.shoppinguserapi;


import com.coca.shoppingmodel.entity.ums.UmsMemberReceiveAddress;

import java.util.List;

public interface IUmsMemberReceiveAddressRpcService {
    List<UmsMemberReceiveAddress> GetMemberReceiveAddressList(Long memberId);

    /**
     * 获取地址详情
     * @param id 地址id
     */
    UmsMemberReceiveAddress getItem(Long MemberId,Long id);
}

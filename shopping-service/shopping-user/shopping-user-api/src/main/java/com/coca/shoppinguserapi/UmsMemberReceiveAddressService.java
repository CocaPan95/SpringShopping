package com.coca.shoppinguserapi;

import com.coca.shoppingmodel.domain.user.UmsMemberReceiveAddress;

import java.util.List;

public interface UmsMemberReceiveAddressService {
    List<UmsMemberReceiveAddress> GetMemberReceiveAddressList(Long memberId);
}

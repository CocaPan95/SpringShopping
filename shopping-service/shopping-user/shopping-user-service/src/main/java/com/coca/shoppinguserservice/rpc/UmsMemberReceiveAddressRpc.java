package com.coca.shoppinguserservice.rpc;

import com.coca.shoppingmodel.domain.user.UmsMemberReceiveAddress;
import com.coca.shoppinguserapi.UmsMemberReceiveAddressService;
import com.coca.shoppinguserservice.service.MemberReceiveAddressService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class UmsMemberReceiveAddressRpc implements UmsMemberReceiveAddressService {
    @Autowired
    private MemberReceiveAddressService memberReceiveAddressService;
    @Override
    public List<UmsMemberReceiveAddress> GetMemberReceiveAddressList(Long memberId){
        return memberReceiveAddressService.GetMemberReceiveAddressList(memberId);
    }

    @Override
    public UmsMemberReceiveAddress getItem(Long MemberId,Long id) {
        return memberReceiveAddressService.getItem(MemberId,id);
    }
}

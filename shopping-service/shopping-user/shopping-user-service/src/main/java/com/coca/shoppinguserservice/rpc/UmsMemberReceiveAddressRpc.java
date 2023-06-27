package com.coca.shoppinguserservice.rpc;

import com.coca.shoppingmodel.entity.ums.UmsMemberReceiveAddress;
import com.coca.shoppinguserapi.IUmsMemberReceiveAddressRpcService;
import com.coca.shoppinguserservice.service.IUmsMemberReceiveAddressService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class UmsMemberReceiveAddressRpc implements IUmsMemberReceiveAddressRpcService {
    @Autowired
    private IUmsMemberReceiveAddressService memberReceiveAddressService;

    @Override
    public List<UmsMemberReceiveAddress> GetMemberReceiveAddressList(Long memberId){
        return memberReceiveAddressService.GetMemberReceiveAddressList(memberId);
    }

    @Override
    public UmsMemberReceiveAddress getItem(Long MemberId,Long id) {
        return memberReceiveAddressService.getItem(MemberId,id);
    }
}

package com.coca.shoppinguserservice.impl;

import com.coca.shoppingmodel.domain.user.UmsMember;
import com.coca.shoppingmodel.domain.user.UmsMemberExample;
import com.coca.shoppingmodel.domain.user.UmsMemberReceiveAddress;
import com.coca.shoppingmodel.domain.user.UmsMemberReceiveAddressExample;
import com.coca.shoppinguserservice.mapper.UmsMemberReceiveAddressMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class UmsMemberReceiveAddressImpl {
    @Autowired
    private UmsMemberReceiveAddressMapper memberReceiveAddressMapper;

    public List<UmsMemberReceiveAddress> GetMemberReceiveAddressList(Long memberId){
        UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
        example.createCriteria().andMemberIdEqualTo(memberId);
        return memberReceiveAddressMapper.selectByExample(example);
    }
}

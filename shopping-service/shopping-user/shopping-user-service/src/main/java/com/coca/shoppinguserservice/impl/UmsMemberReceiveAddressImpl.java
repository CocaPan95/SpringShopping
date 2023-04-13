package com.coca.shoppinguserservice.impl;

import com.coca.shoppingmodel.domain.user.UmsMember;
import com.coca.shoppingmodel.domain.user.UmsMemberExample;
import com.coca.shoppingmodel.domain.user.UmsMemberReceiveAddress;
import com.coca.shoppingmodel.domain.user.UmsMemberReceiveAddressExample;
import com.coca.shoppinguserapi.UmsMemberReceiveAddressService;
import com.coca.shoppinguserapi.UmsMemberService;
import com.coca.shoppinguserservice.mapper.UmsMemberReceiveAddressMapper;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

@DubboService
public class UmsMemberReceiveAddressImpl implements UmsMemberReceiveAddressService {
    @Autowired
    private UmsMemberReceiveAddressMapper memberReceiveAddressMapper;
    @DubboReference
    private UmsMemberService umsMemberService;

    public List<UmsMemberReceiveAddress> GetMemberReceiveAddressList(Long memberId){
        UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
        example.createCriteria().andMemberIdEqualTo(memberId);
        return memberReceiveAddressMapper.selectByExample(example);
    }

    @Override
    public UmsMemberReceiveAddress getItem(Long id) {
        UmsMember currentMember = umsMemberService.getCurrentMember();
        UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
        example.createCriteria().andMemberIdEqualTo(currentMember.getId()).andIdEqualTo(id);
        List<UmsMemberReceiveAddress> addressList = memberReceiveAddressMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(addressList)){
            return addressList.get(0);
        }
        return null;
    }
}

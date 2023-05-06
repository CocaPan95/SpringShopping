package com.coca.shoppinguserservice.service.impl;

import com.coca.shoppingmodel.domain.user.UmsMemberReceiveAddress;
import com.coca.shoppingmodel.domain.user.UmsMemberReceiveAddressExample;
import com.coca.shoppinguserapi.UmsMemberService;
import com.coca.shoppinguserservice.mapper.UmsMemberReceiveAddressMapper;
import com.coca.shoppinguserservice.service.MemberReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class MemberReceiveAddressImpl implements MemberReceiveAddressService {
    @Autowired
    private UmsMemberReceiveAddressMapper memberReceiveAddressMapper;

    private UmsMemberService umsMemberService;

    public List<UmsMemberReceiveAddress> GetMemberReceiveAddressList(Long memberId){
        UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
        example.createCriteria().andMemberIdEqualTo(memberId);
        return memberReceiveAddressMapper.selectByExample(example);
    }

    @Override
    public UmsMemberReceiveAddress getItem(Long MemberId,Long id) {

        UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
        example.createCriteria().andMemberIdEqualTo(MemberId).andIdEqualTo(id);
        List<UmsMemberReceiveAddress> addressList = memberReceiveAddressMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(addressList)){
            return addressList.get(0);
        }
        return null;
    }
}

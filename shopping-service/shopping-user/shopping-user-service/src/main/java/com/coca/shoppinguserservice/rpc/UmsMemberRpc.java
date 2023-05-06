package com.coca.shoppinguserservice.rpc;

import com.coca.shoppingmodel.domain.user.UmsMember;
import com.coca.shoppingmodel.dto.UserDto;
import com.coca.shoppinguserapi.UmsMemberService;
import com.coca.shoppinguserservice.service.MemberService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@DubboService
public class UmsMemberRpc implements UmsMemberService {
    @Autowired
    private MemberService memberService;

    @Override
    public UserDto loadUserByUsername(String username) {
        return memberService.loadUserByUsername(username);
    }

    @Override
    public UmsMember getByUsername(String username) {
        return memberService.getByUsername(username);
    }

    @Override
    public UmsMember getById(Long id) {
        return memberService.getById(id);
    }

    @Override
    public void updateIntegration(Long id, Integer integration) {
        memberService.updateIntegration(id, integration);
    }
}

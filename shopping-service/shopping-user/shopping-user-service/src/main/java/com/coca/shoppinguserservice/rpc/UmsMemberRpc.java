package com.coca.shoppinguserservice.rpc;

import com.coca.shoppingmodel.dto.UserDto;
import com.coca.shoppingmodel.entity.ums.UmsMember;
import com.coca.shoppinguserapi.IUmsMemberRpcService;
import com.coca.shoppinguserservice.service.IUmsMemberService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@DubboService
public class UmsMemberRpc implements IUmsMemberRpcService {
    @Autowired
    private IUmsMemberService memberService;

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

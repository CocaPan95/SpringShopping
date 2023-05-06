package com.coca.shoppinguserservice.service;

import com.coca.shoppingmodel.domain.user.UmsMember;
import com.coca.shoppingmodel.dto.UserDto;

public interface MemberService {
    UserDto loadUserByUsername(String username);
    UmsMember getByUsername(String username);
    //根据Id获取用户
    UmsMember getById(Long id);
    /**
     * 根据会员id修改会员积分
     */
    void updateIntegration(Long id,Integer integration);
}

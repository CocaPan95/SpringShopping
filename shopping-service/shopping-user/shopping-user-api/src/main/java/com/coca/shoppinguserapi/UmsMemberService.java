package com.coca.shoppinguserapi;

import com.coca.shoppingmodel.domain.user.UmsMember;
import com.coca.shoppingmodel.dto.UserDto;

public interface UmsMemberService {
    UserDto loadUserByUsername(String username);
    UmsMember getByUsername(String username);
    //获取当前登录用户
    UmsMember getCurrentMember();
    //根据Id获取用户
    UmsMember getById(Long id);
    /**
     * 根据会员id修改会员积分
     */
    void updateIntegration(Long id,Integer integration);
}

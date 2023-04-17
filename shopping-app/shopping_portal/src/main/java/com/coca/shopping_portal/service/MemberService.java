package com.coca.shopping_portal.service;

import com.coca.shoppingmodel.api.CommonResult;
import com.coca.shoppingmodel.domain.user.UmsMember;

public interface MemberService {
    /**
     * 登录后获取token
     */
    CommonResult login(String username, String password);

    //获取当前登录用户
    UmsMember getCurrentMember();
}

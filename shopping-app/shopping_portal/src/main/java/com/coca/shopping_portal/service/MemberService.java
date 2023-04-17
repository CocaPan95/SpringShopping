package com.coca.shopping_portal.service;

import com.coca.shoppingmodel.api.CommonResult;

public interface MemberService {
    /**
     * 登录后获取token
     */
    CommonResult login(String username, String password);
}

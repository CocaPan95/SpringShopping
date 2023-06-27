package com.coca.shopping_portal.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.coca.shopping_portal.service.AuthService;
import com.coca.shopping_portal.service.MemberService;
import com.coca.shoppingcommon.constant.AuthConstant;
import com.coca.shoppingcommon.exception.Asserts;
import com.coca.shoppingmodel.api.CommonResult;
import com.coca.shoppingmodel.api.ResultCode;
import com.coca.shoppingmodel.dto.UserDto;
import com.coca.shoppingmodel.entity.ums.UmsMember;
import com.coca.shoppinguserapi.IUmsMemberRpcService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class MemberImpl implements MemberService {
    @Autowired
    private AuthService authService;
    @Autowired
    private HttpServletRequest request;

    @DubboReference
    private IUmsMemberRpcService umsMemberService;

    @Override
    public CommonResult login(String username, String password) {
        if (StrUtil.isEmpty(username) || StrUtil.isEmpty(password)) {
            Asserts.fail("用户名或密码不能为空！");
        }
        Map<String, String> params = new HashMap<>();
        params.put("client_id", AuthConstant.PORTAL_CLIENT_ID);
        params.put("client_secret", "123456");
        params.put("grant_type", "password");
        params.put("username", username);
        params.put("password", password);
        return authService.getAccessToken(params);
    }

    @Override
    public UmsMember getCurrentMember() {
        String userStr = request.getHeader(AuthConstant.USER_TOKEN_HEADER);
        if (StrUtil.isEmpty(userStr)) {
            Asserts.fail(ResultCode.UNAUTHORIZED);
        }
        UserDto userDto = JSONUtil.toBean(userStr, UserDto.class);
        UmsMember member = umsMemberService.getById(userDto.getId());
        return member;
    }
}

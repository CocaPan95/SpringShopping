package com.coca.shoppingadmin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.coca.shoppingadmin.service.AdminService;
import com.coca.shoppingadmin.service.AuthService;
import com.coca.shoppingcommon.constant.AuthConstant;
import com.coca.shoppingcommon.exception.Asserts;
import com.coca.shoppingmodel.api.CommonResult;
import com.coca.shoppingmodel.api.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminImpl implements AdminService {
    @Autowired
    private AuthService authService;

    @Override
    public CommonResult login(String username, String password) {
        if(StrUtil.isEmpty(username)||StrUtil.isEmpty(password)){
            Asserts.fail("用户名或密码不能为空！");
        }
        Map<String, String> params = new HashMap<>();
        params.put("client_id", AuthConstant.ADMIN_CLIENT_ID);
        params.put("client_secret","123456");
        params.put("grant_type","password");
        params.put("username",username);
        params.put("password",password);
        CommonResult restResult = authService.getAccessToken(params);
        if(ResultCode.SUCCESS.getCode()==restResult.getCode()&&restResult.getData()!=null){
//            updateLoginTimeByUsername(username);
          //  insertLoginLog(username);
        }
        return restResult;
    }
}

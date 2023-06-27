package com.coca.shoppingadmin.service;

import com.coca.shoppingmodel.api.CommonResult;
import com.coca.shoppingmodel.entity.ums.UmsAdmin;


public interface AdminService {
    CommonResult login(String username, String password);
    UmsAdmin getCurrentAdmin();
}

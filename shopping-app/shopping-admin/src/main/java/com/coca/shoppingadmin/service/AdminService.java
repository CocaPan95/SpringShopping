package com.coca.shoppingadmin.service;

import com.coca.shoppingmodel.api.CommonResult;

public interface AdminService {
    CommonResult login(String username, String password);
}

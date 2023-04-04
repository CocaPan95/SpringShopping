package com.coca.shoppingadmin.controller;

import com.coca.shoppingadmin.service.AdminService;
import com.coca.shoppingmodel.api.CommonResult;
import com.coca.shoppingmodel.dto.UmsAdminLoginParam;
import com.coca.shoppingmodel.dto.UserDto;
import com.coca.shoppinguserapi.UmsAdminService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class UmsAdminController {
    @DubboReference
    private UmsAdminService umsAdminService;

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/loadByUsername", method = RequestMethod.GET)
    @ResponseBody
    public UserDto loadUserByUsername(@RequestParam String username) {
        UserDto userDTO = umsAdminService.loadUserByUsername(username);
        return userDTO;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@Validated @RequestBody UmsAdminLoginParam umsAdminLoginParam) {
        return adminService.login(umsAdminLoginParam.getUsername(),umsAdminLoginParam.getPassword());
    }

}

package com.coca.shoppingadmin.controller;

import com.coca.shoppingadmin.service.AdminService;
import com.coca.shoppingmodel.api.CommonResult;
import com.coca.shoppingmodel.dto.UmsAdminLoginParam;
import com.coca.shoppingmodel.dto.UserDto;
import com.coca.shoppinguserapi.IUmsAdminRpcService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "后台管理员接口",description = "操作后台管理员分类")
@Controller
@RequestMapping("/admin")
public class UmsAdminController {
    @DubboReference
    private IUmsAdminRpcService umsAdminService;

    @Autowired
    private AdminService adminService;

    @ApiOperation("加载用户信息")
    @RequestMapping(value = "/loadByUsername", method = RequestMethod.GET)
    @ResponseBody
    public UserDto loadUserByUsername(@RequestParam String username) {
        UserDto userDTO = umsAdminService.loadUserByUsername(username);
        return userDTO;
    }

    @ApiOperation("登录接口")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@Validated @RequestBody UmsAdminLoginParam umsAdminLoginParam) {
        return adminService.login(umsAdminLoginParam.getUsername(),umsAdminLoginParam.getPassword());
    }

}

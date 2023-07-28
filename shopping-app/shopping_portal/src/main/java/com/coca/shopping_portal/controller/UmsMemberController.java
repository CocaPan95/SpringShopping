package com.coca.shopping_portal.controller;

import com.coca.shopping_portal.service.MemberService;
import com.coca.shoppingmodel.api.CommonResult;
import com.coca.shoppingmodel.dto.UserDto;
import com.coca.shoppinguserapi.IUmsMemberRpcService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "会员信息接口",description = "会员信息操作分类")
@Controller
@RequestMapping("/sso")
public class UmsMemberController {
    @DubboReference
    private IUmsMemberRpcService umsMemberService;

    @Autowired
    private MemberService memberService;

    @ApiOperation("获取会员信息")
    @RequestMapping(value = "/loadByUsername", method = RequestMethod.GET)
    @ResponseBody
    public UserDto loadUserByUsername(@RequestParam String username) {
        return umsMemberService.loadUserByUsername(username);
    }

    @ApiOperation("会员登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestParam String username,
                              @RequestParam String password) {
        return memberService.login(username, password);
    }

}

package com.coca.shopping_portal.controller;

import com.coca.shoppingmodel.dto.UserDto;
import com.coca.shoppinguserapi.UmsMemberService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sso")
public class UmsMemberController {

    @DubboReference
    private UmsMemberService umsMemberService;

    @RequestMapping(value = "/loadByUsername", method = RequestMethod.GET)
    @ResponseBody
    public UserDto loadUserByUsername(@RequestParam String username) {
        return umsMemberService.loadUserByUsername(username);
    }
}

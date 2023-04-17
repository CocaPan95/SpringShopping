package com.coca.shopping_portal.controller;

import com.coca.shopping_portal.service.MemberService;
import com.coca.shoppingmodel.api.CommonResult;
import com.coca.shoppingmodel.domain.order.OmsCartItem;
import com.coca.shoppingmodel.domain.user.UmsMember;
import com.coca.shoppingorderapi.OmsCartItemService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 购物车管理Controller
 * Created by macro on 2018/8/2.
 */
@Controller
@RequestMapping("/cart")
public class OmsCartItemController {
    @DubboReference
    private OmsCartItemService cartItemService;
    @Autowired
    private MemberService memberService;
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@RequestBody OmsCartItem cartItem) {
        UmsMember member = memberService.getCurrentMember();
        int count = cartItemService.add(member.getId(), cartItem);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}

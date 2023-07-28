package com.coca.shopping_portal.controller;

import com.coca.shopping_portal.service.MemberService;
import com.coca.shoppingmodel.api.CommonResult;
import com.coca.shoppingmodel.entity.oms.OmsCartItem;
import com.coca.shoppingmodel.entity.ums.UmsMember;
import com.coca.shoppingorderapi.IOmsCartItemRpcService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "购物车接口",description = "购物车操作分类")
@Controller
@RequestMapping("/cart")
public class OmsCartItemController {

    @DubboReference
    private IOmsCartItemRpcService cartItemService;
    @Autowired
    private MemberService memberService;

    @ApiOperation("购物车添加商品")
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

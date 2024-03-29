package com.coca.shopping_portal.controller;

import com.coca.shopping_portal.service.MemberService;
import com.coca.shoppingmodel.api.CommonResult;
import com.coca.shoppingmodel.entity.ums.UmsMember;
import com.coca.shoppingsmsapi.ISmsCouponRpcService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "优惠券操作接口",description = "优惠券操作分类")
@Controller
@RequestMapping("/coupon")
public class SmsCouponController {
    @DubboReference
    private ISmsCouponRpcService couponHistoryService;

    @Autowired
    private MemberService memberService;

    @ApiOperation("获取优惠券")
    @RequestMapping(value = "/getcoupon", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Boolean> getCouponByMember(@RequestParam(required = false) String code) {
        UmsMember member = memberService.getCurrentMember();
        boolean result = couponHistoryService.getCouponByMember(code,member.getId());
        return CommonResult.success(result);
    }
}

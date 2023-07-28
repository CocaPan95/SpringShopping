package com.coca.shoppingadmin.controller;

import com.coca.shoppingmodel.api.CommonResult;
import com.coca.shoppingmodel.dto.SmsCouponParam;
import com.coca.shoppingsmsapi.ISmsCouponRpcService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(tags = "优惠券接口",description = "优惠券分类")
@Controller
@RequestMapping("/coupon")
public class SmsCouponController {
    @DubboReference
    private ISmsCouponRpcService couponHistoryService;

    @ApiOperation("创建优惠券")
    @RequestMapping(value = "/createCoupon", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> createCoupon(@RequestBody SmsCouponParam param) {
        Integer result = couponHistoryService.createCoupon(param);
        return CommonResult.success(result);
    }
}
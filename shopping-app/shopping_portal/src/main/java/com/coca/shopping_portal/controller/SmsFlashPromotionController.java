package com.coca.shopping_portal.controller;

import com.coca.shoppingmodel.api.CommonResult;
import com.coca.shoppingmodel.dto.PmsPortalProductDetail;
import com.coca.shoppingsmsapi.ISmsFlashPromotionRpcService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "秒杀活动接口",description = "秒杀活动操作分类")
@Controller
@RequestMapping("/flashpromotion")
public class SmsFlashPromotionController {
    @DubboReference
    private ISmsFlashPromotionRpcService flashPromotionService;

    @ApiOperation("获取秒杀活动详情")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsPortalProductDetail> detail(@PathVariable Long id) {
        PmsPortalProductDetail productDetail = flashPromotionService.getFlashPromotionProduct(id);
        return CommonResult.success(productDetail);
    }
}

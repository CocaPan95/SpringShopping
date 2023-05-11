package com.coca.shopping_portal.controller;

import com.coca.shoppingmodel.api.CommonResult;
import com.coca.shoppingmodel.dto.PmsPortalProductDetail;
import com.coca.shoppingsmsapi.SmsFlashPromotionService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/flashpromotion")
public class SmsFlashPromotionController {
    @DubboReference
    private SmsFlashPromotionService flashPromotionService;

    // @ApiOperation("获取前台商品详情")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsPortalProductDetail> detail(@PathVariable Long id) {
        PmsPortalProductDetail productDetail = flashPromotionService.getFlashPromotionProduct(id);
        return CommonResult.success(productDetail);
    }
}

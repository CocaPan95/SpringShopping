package com.coca.shopping_portal.controller;

import com.coca.shoppingmodel.api.CommonPage;
import com.coca.shoppingmodel.api.CommonResult;
import com.coca.shoppingmodel.dto.ConfirmOrderResult;
import com.coca.shoppingmodel.dto.OmsOrderDetail;
import com.coca.shoppingmodel.dto.OrderParam;
import com.coca.shoppingmodel.es.EsProduct;
import com.coca.shoppingorderapi.OmsOrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OmsOrderController {
    @DubboReference
    private OmsOrderService omsOrderService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<OmsOrderDetail>> list(@RequestParam(required = false) Integer status,
                                                         @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                         @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        CommonPage<OmsOrderDetail> result = omsOrderService.list(status, pageNum, pageSize);
        return CommonResult.success(result);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<OmsOrderDetail> detail(@RequestParam(required = false) Long orderId) {
        OmsOrderDetail result = omsOrderService.detail(orderId);
        return CommonResult.success(result);
    }

    @RequestMapping(value = "/generateConfirmOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<ConfirmOrderResult> generateConfirmOrder(@RequestBody List<Long> ids) {
        ConfirmOrderResult result = omsOrderService.generateConfirmOrder(ids);
        return CommonResult.success(result);
    }

    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult generateOrder(@RequestBody OrderParam orderParam) {
        Map<String, Object> result = omsOrderService.generateOrder(orderParam);
        return CommonResult.success(result, "下单成功");
    }


    @RequestMapping(value = "/paySuccess", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult paySuccess(@RequestParam Long orderId,@RequestParam Integer payType) {
        Integer count = omsOrderService.paySuccess(orderId,payType);
        return CommonResult.success(count, "支付成功");
    }

    @RequestMapping(value = "/cancelUserOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult cancelUserOrder(Long orderId) {
        omsOrderService.cancelOrder(orderId);
        return CommonResult.success(null);
    }

    @RequestMapping(value = "/confirmReceiveOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult confirmReceiveOrder(Long orderId) {
        omsOrderService.confirmReceiveOrder(orderId);
        return CommonResult.success(null);
    }

    @RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteOrder(Long orderId) {
        omsOrderService.deleteOrder(orderId);
        return CommonResult.success(null);
    }
}

package com.coca.shopping_portal.controller;

import com.coca.shopping_portal.service.MemberService;
import com.coca.shoppingmodel.api.CommonPage;
import com.coca.shoppingmodel.api.CommonResult;
import com.coca.shoppingmodel.dto.ConfirmOrderResult;
import com.coca.shoppingmodel.dto.OmsOrderDetail;
import com.coca.shoppingmodel.dto.OrderParam;
import com.coca.shoppingmodel.entity.ums.UmsMember;
import com.coca.shoppingorderapi.IOmsOrderRpcService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OmsOrderController {
    @DubboReference(retries = -1)
    private IOmsOrderRpcService omsOrderService;

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<OmsOrderDetail>> list(@RequestParam(required = false) Integer status,
                                                         @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                         @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        UmsMember member = memberService.getCurrentMember();
        CommonPage<OmsOrderDetail> result = omsOrderService.list(member.getId(), status, pageNum, pageSize);
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
        UmsMember member = memberService.getCurrentMember();
        ConfirmOrderResult result = omsOrderService.generateConfirmOrder(member.getId(), ids);
        return CommonResult.success(result);
    }

    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult generateOrder(@RequestBody OrderParam orderParam) {
        UmsMember member = memberService.getCurrentMember();
        Map<String, Object> result = omsOrderService.generateOrder(member.getId(), orderParam);
        return CommonResult.success(result, "下单成功");
    }


    @RequestMapping(value = "/paySuccess", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult paySuccess(@RequestParam Long orderId, @RequestParam Integer payType) {
        Integer count = omsOrderService.paySuccess(orderId, payType);
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
        UmsMember member = memberService.getCurrentMember();
        omsOrderService.confirmReceiveOrder(member.getId(), orderId);
        return CommonResult.success(null);
    }

    @RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteOrder(Long orderId) {
        UmsMember member = memberService.getCurrentMember();
        omsOrderService.deleteOrder(member.getId(), orderId);
        return CommonResult.success(null);
    }
}

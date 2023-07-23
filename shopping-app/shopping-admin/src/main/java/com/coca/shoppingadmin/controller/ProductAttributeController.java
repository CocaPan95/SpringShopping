package com.coca.shoppingadmin.controller;

import com.coca.shoppingmodel.entity.pms.PmsProductAttribute;
import com.coca.shoppingproductapi.IPmsProductAttributeRpcService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/productattribute")
public class ProductAttributeController {
    @DubboReference
    private IPmsProductAttributeRpcService pmsProductAttributeRpcService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
   public boolean PmsProductAttributeSave(PmsProductAttribute param){
        return pmsProductAttributeRpcService.PmsProductAttributeSave(param);
    }
}

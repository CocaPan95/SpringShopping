package com.coca.shoppingproductservice.rpc;

import com.coca.shoppingmodel.entity.pms.PmsProductAttribute;
import com.coca.shoppingproductservice.service.IPmsProductAttributeService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class PmsProductAttributeRpc {
    @Autowired
    private IPmsProductAttributeService productAttributeService;

    public boolean PmsProductAttributeSave(PmsProductAttribute param){
        return productAttributeService.PmsProductAttributeSave(param);
    }
}

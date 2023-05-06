package com.coca.shoppingproductservice.rpc;

import com.coca.shoppingmodel.domain.product.PmsSkuStock;
import com.coca.shoppingproductapi.PmsSkuStockService;
import com.coca.shoppingproductservice.service.SkuStockService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class PmsSkuStockRpc implements PmsSkuStockService {
    @Autowired
    private SkuStockService skuStockService;


    public PmsSkuStock GetPmsSkuStockById(Long Id){
        return skuStockService.GetPmsSkuStockById(Id);
    }

    public int UpdatePmsSkuStock(PmsSkuStock model){
        return skuStockService.UpdatePmsSkuStock(model);
    }
}

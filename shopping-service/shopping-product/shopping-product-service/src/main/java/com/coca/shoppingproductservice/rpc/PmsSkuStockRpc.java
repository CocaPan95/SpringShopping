package com.coca.shoppingproductservice.rpc;

import com.coca.shoppingmodel.entity.pms.PmsSkuStock;
import com.coca.shoppingproductapi.IPmsSkuStockRpcService;
import com.coca.shoppingproductservice.service.IPmsSkuStockService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class PmsSkuStockRpc implements IPmsSkuStockRpcService {
    @Autowired
    private IPmsSkuStockService skuStockService;


    public PmsSkuStock GetPmsSkuStockById(Long Id) {
        return skuStockService.getById(Id);
    }

    public int UpdatePmsSkuStock(PmsSkuStock model) {
        if(skuStockService.updateById(model)){
            return 1;
        }
        return 0;
    }
}

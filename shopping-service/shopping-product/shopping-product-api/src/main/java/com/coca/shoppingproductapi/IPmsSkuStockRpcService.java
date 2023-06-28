package com.coca.shoppingproductapi;


import com.coca.shoppingmodel.entity.pms.PmsSkuStock;

public interface IPmsSkuStockRpcService {
    PmsSkuStock GetPmsSkuStockById(Long Id);

    int UpdatePmsSkuStock(PmsSkuStock model);
}

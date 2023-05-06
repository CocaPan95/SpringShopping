package com.coca.shoppingproductservice.service;

import com.coca.shoppingmodel.domain.product.PmsSkuStock;

public interface SkuStockService {
    PmsSkuStock GetPmsSkuStockById(Long Id);

    int UpdatePmsSkuStock(PmsSkuStock model);
}

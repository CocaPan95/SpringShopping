package com.coca.shoppingproductapi;

import com.coca.shoppingmodel.domain.product.PmsSkuStock;

public interface PmsSkuStockService {
    PmsSkuStock GetPmsSkuStockById(Long Id);

    int UpdatePmsSkuStock(PmsSkuStock model);
}

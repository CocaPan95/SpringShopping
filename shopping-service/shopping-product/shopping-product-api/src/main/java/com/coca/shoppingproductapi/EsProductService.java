package com.coca.shoppingproductapi;

import com.coca.shoppingmodel.api.CommonPage;
import com.coca.shoppingmodel.es.EsProduct;
import org.springframework.data.domain.Page;

public interface EsProductService {
    int importAll();
    CommonPage<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);
    CommonPage<EsProduct> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort);
}

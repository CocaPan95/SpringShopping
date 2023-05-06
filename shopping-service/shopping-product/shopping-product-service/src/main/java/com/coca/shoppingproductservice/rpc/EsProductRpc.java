package com.coca.shoppingproductservice.rpc;

import com.coca.shoppingmodel.api.CommonPage;
import com.coca.shoppingmodel.es.EsProduct;
import com.coca.shoppingproductapi.EsProductService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;


@DubboService
public class EsProductRpc implements EsProductService {
    @Autowired
    private EsProductService esProductService;

    @Override
    public int importAll() {
        return esProductService.importAll();
    }

    @Override
    public CommonPage<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        return esProductService.search(keyword, pageNum, pageSize);
    }

    @Override
    public CommonPage<EsProduct> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort) {
        return esProductService.search(keyword, brandId, productCategoryId,pageNum,pageSize,sort);
    }
}

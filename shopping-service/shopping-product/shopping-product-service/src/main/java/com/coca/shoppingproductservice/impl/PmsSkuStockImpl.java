package com.coca.shoppingproductservice.impl;

import com.coca.shoppingmodel.domain.product.PmsSkuStock;
import com.coca.shoppingproductapi.PmsSkuStockService;
import com.coca.shoppingproductservice.mapper.PmsSkuStockMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class PmsSkuStockImpl implements PmsSkuStockService {

    @Autowired
    private PmsSkuStockMapper skuStockMapper;


    public PmsSkuStock GetPmsSkuStockById(Long Id){
        return skuStockMapper.selectByPrimaryKey(Id);
    }

    public int UpdatePmsSkuStock(PmsSkuStock model){
        return skuStockMapper.updateByPrimaryKeySelective(model);
    }
}

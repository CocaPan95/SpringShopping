package com.coca.shoppingproductservice.service.impl;

import com.coca.shoppingmodel.domain.product.PmsSkuStock;
import com.coca.shoppingproductservice.mapper.PmsSkuStockMapper;
import com.coca.shoppingproductservice.service.SkuStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkuStockImpl implements SkuStockService {
    @Autowired
    private PmsSkuStockMapper skuStockMapper;

    @Override
    public PmsSkuStock GetPmsSkuStockById(Long Id){
        return skuStockMapper.selectByPrimaryKey(Id);
    }

    @Override
    public int UpdatePmsSkuStock(PmsSkuStock model){
        return skuStockMapper.updateByPrimaryKeySelective(model);
    }
}

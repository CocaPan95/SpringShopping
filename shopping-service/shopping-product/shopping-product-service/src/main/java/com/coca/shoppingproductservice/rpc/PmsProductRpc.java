package com.coca.shoppingproductservice.rpc;


import com.coca.shoppingmodel.dto.PmsPortalProductDetail;
import com.coca.shoppingmodel.dto.PmsProductParam;
import com.coca.shoppingmodel.dto.PromotionProduct;
import com.coca.shoppingproductapi.PmsProductService;
import com.coca.shoppingproductservice.service.ProductService;
import org.apache.dubbo.config.annotation.DubboService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@DubboService
public class PmsProductRpc implements PmsProductService {
    @Autowired
    private ProductService productService;

    @Override
    public int create(PmsProductParam productParam){
        return productService.create(productParam);
    }

    @Override
    public int update(Long id, PmsProductParam productParam) {
        return productService.update(id,productParam);
    }

    @Override
    public int updatePublishStatus(List<Long> ids, Integer publishStatus) {
        return productService.updatePublishStatus(ids,publishStatus);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        return productService.updateRecommendStatus(ids,recommendStatus);
    }

    @Override
    public int updateNewStatus(List<Long> ids, Integer newStatus) {
        return productService.updateNewStatus(ids,newStatus);
    }

    @Override
    public int updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
        return productService.updateDeleteStatus(ids,deleteStatus);
    }

    @Override
    public List<PromotionProduct> getPromotionProductList(List<Long> productIdList) {
        return productService.getPromotionProductList(productIdList);
    }
    @Override
    public PmsPortalProductDetail getProductDetail(Long productId){
        return productService.getProductDetail(productId);
    }
}

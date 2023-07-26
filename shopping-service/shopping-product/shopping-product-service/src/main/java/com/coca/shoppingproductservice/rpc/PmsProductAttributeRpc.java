package com.coca.shoppingproductservice.rpc;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coca.shoppingmodel.dto.PageParam;
import com.coca.shoppingmodel.entity.pms.PmsProductAttribute;
import com.coca.shoppingmodel.entity.pms.PmsProductAttributeCategory;
import com.coca.shoppingproductservice.service.IPmsProductAttributeCategoryService;
import com.coca.shoppingproductservice.service.IPmsProductAttributeService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class PmsProductAttributeRpc {

    @Autowired
    private IPmsProductAttributeCategoryService productAttributeCategoryService;
    @Autowired
    private IPmsProductAttributeService productAttributeService;

    public Page<PmsProductAttributeCategory> GetProductAttributeCategoryAllList(PageParam param){
        return productAttributeCategoryService.GetProductAttributeCategoryAllList(param);
    }

    public boolean SaveProductAttributeCategory(PmsProductAttributeCategory param){
        return productAttributeCategoryService.SaveProductAttributeCategory(param);
    }

    public boolean RemoveProductAttributeCategory(Long categoryId){
        return productAttributeCategoryService.RemoveProductAttributeCategory(categoryId);
    }

    public List<PmsProductAttribute> GetProductAttributeList(Long productAttributeCategoryId){
        return productAttributeService.GetProductAttributeList(productAttributeCategoryId);
    }

    public boolean PmsProductAttributeSave(PmsProductAttribute param){
        return productAttributeService.PmsProductAttributeSave(param);
    }

    public boolean PmsProductAttributeRemove(Long attributeId){
        return productAttributeService.removeById(attributeId);
    }

}

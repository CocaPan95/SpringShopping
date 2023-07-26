package com.coca.shoppingproductapi;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coca.shoppingmodel.dto.PageParam;
import com.coca.shoppingmodel.entity.pms.PmsProductAttribute;
import com.coca.shoppingmodel.entity.pms.PmsProductAttributeCategory;

import java.util.List;

public interface IPmsProductAttributeRpcService {
    Page<PmsProductAttributeCategory> GetProductAttributeCategoryAllList(PageParam param);
    boolean SaveProductAttributeCategory(PmsProductAttributeCategory param);
    boolean RemoveProductAttributeCategory(Long categoryId);
    List<PmsProductAttribute> GetProductAttributeList(Long productAttributeCategoryId);
    boolean PmsProductAttributeSave(PmsProductAttribute param);
    boolean PmsProductAttributeRemove(Long attributeId);
}

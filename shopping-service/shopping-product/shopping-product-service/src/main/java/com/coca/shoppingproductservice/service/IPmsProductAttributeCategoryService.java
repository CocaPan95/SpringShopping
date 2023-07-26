package com.coca.shoppingproductservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coca.shoppingmodel.dto.PageParam;
import com.coca.shoppingmodel.entity.pms.PmsProductAttributeCategory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 产品属性分类表 服务类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
public interface IPmsProductAttributeCategoryService extends IService<PmsProductAttributeCategory> {

    Page<PmsProductAttributeCategory> GetProductAttributeCategoryAllList(PageParam param);

    boolean SaveProductAttributeCategory(PmsProductAttributeCategory param);

    boolean RemoveProductAttributeCategory(Long categoryId);
}

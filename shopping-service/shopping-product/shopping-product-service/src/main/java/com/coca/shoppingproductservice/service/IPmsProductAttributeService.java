package com.coca.shoppingproductservice.service;

import com.coca.shoppingmodel.entity.pms.PmsProductAttribute;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品属性参数表 服务类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
public interface IPmsProductAttributeService extends IService<PmsProductAttribute> {
    boolean PmsProductAttributeSave(PmsProductAttribute param);
}

package com.coca.shoppingproductservice.service.impl;

import com.coca.shoppingmodel.entity.pms.PmsProductCategoryAttributeRelation;
import com.coca.shoppingproductservice.mapper.PmsProductCategoryAttributeRelationMapper;
import com.coca.shoppingproductservice.service.IPmsProductCategoryAttributeRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） 服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
@Service
public class PmsProductCategoryAttributeRelationServiceImpl extends ServiceImpl<PmsProductCategoryAttributeRelationMapper, PmsProductCategoryAttributeRelation> implements IPmsProductCategoryAttributeRelationService {

}

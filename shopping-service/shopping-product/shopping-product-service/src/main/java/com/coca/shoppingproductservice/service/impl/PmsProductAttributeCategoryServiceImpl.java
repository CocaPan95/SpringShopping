package com.coca.shoppingproductservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coca.shoppingcommon.exception.Asserts;
import com.coca.shoppingmodel.dto.PageParam;
import com.coca.shoppingmodel.entity.pms.PmsProductAttribute;
import com.coca.shoppingmodel.entity.pms.PmsProductAttributeCategory;
import com.coca.shoppingmodel.entity.pms.PmsProductAttributeValue;
import com.coca.shoppingproductservice.mapper.PmsProductAttributeCategoryMapper;
import com.coca.shoppingproductservice.mapper.PmsProductAttributeMapper;
import com.coca.shoppingproductservice.mapper.PmsProductAttributeValueMapper;
import com.coca.shoppingproductservice.service.IPmsProductAttributeCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 产品属性分类表 服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
@Service
public class PmsProductAttributeCategoryServiceImpl extends ServiceImpl<PmsProductAttributeCategoryMapper, PmsProductAttributeCategory> implements IPmsProductAttributeCategoryService {

    @Autowired
    private PmsProductAttributeMapper productAttributeMapper;

    @Autowired
    private PmsProductAttributeValueMapper productAttributeValueMapper;


    public Page<PmsProductAttributeCategory> GetProductAttributeCategoryAllList(PageParam param) {
        Page<PmsProductAttributeCategory> page = new Page<>(param.getPageNum(), param.getPageSize());
        return baseMapper.selectPage(page, Wrappers.emptyWrapper());
    }

    public boolean SaveProductAttributeCategory(PmsProductAttributeCategory param) {
        if (param.getId() == 0) {
            PmsProductAttributeCategory category = baseMapper.selectById(param.getId());
            category.setName(param.getName());
            if (baseMapper.updateById(category) > 0) {
                return true;
            }
            ;
        } else {
            param.setAttributeCount(0);
            param.setParamCount(0);
            if (baseMapper.insert(param) > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean RemoveProductAttributeCategory(Long categoryId) {
        LambdaQueryWrapper<PmsProductAttribute> productAttributeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        productAttributeLambdaQueryWrapper.eq(PmsProductAttribute::getProductAttributeCategoryId, categoryId);
        List<PmsProductAttribute> pmsProductAttributes = productAttributeMapper.selectList(productAttributeLambdaQueryWrapper);
        List<Long> attributes = pmsProductAttributes.stream().map(t -> t.getId()).collect(Collectors.toList());

        LambdaQueryWrapper<PmsProductAttributeValue> productAttributeValueLambdaQueryWrapper = new LambdaQueryWrapper<>();
        productAttributeValueLambdaQueryWrapper.in(PmsProductAttributeValue::getProductAttributeId, attributes);
        List<PmsProductAttributeValue> pmsProductAttributeValues = productAttributeValueMapper.selectList(productAttributeValueLambdaQueryWrapper);
        if (pmsProductAttributeValues.stream().count() > 0) {
            Asserts.fail("存在产品使用该属性，不可删除！");
        }
        productAttributeMapper.deleteBatchIds(attributes);
        baseMapper.deleteById(categoryId);
        return true;
    }
}

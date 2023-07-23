package com.coca.shoppingproductservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.coca.shoppingmodel.entity.pms.PmsProductAttribute;
import com.coca.shoppingmodel.entity.pms.PmsProductAttributeValue;
import com.coca.shoppingproductservice.mapper.PmsProductAttributeMapper;
import com.coca.shoppingproductservice.mapper.PmsProductAttributeValueMapper;
import com.coca.shoppingproductservice.service.IPmsProductAttributeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
@Service
public class PmsProductAttributeServiceImpl extends ServiceImpl<PmsProductAttributeMapper, PmsProductAttribute> implements IPmsProductAttributeService {

    @Autowired
    private PmsProductAttributeValueMapper productAttributeValueMapper;


    @Override
    public boolean PmsProductAttributeSave(PmsProductAttribute param){
        if(param.getId()>0){
            PmsProductAttribute productAttribute=baseMapper.selectById(param.getId());
            if(productAttribute==null){
                return false;
            }
            if(productAttribute.getName()!=param.getName()||productAttribute.getType()!=param.getType()){
                updateProductAttributeValue(param);
            }
            if(baseMapper.updateById(param)>0){
                return true;
            };
        }else {
           if(baseMapper.insert(param)>0) {
               return true;
           }
        }
        return false;
    }


    private void updateProductAttributeValue(PmsProductAttribute param) {
        LambdaQueryWrapper<PmsProductAttributeValue> productAttributeValueLambdaQueryWrapper = new LambdaQueryWrapper<>();
        productAttributeValueLambdaQueryWrapper.eq(PmsProductAttributeValue::getProductAttributeId,param.getId());
        List<PmsProductAttributeValue> pmsProductAttributeValues = productAttributeValueMapper.selectList(productAttributeValueLambdaQueryWrapper);
        for (PmsProductAttributeValue item:pmsProductAttributeValues){
            item.setProductAttributeType(param.getType());
            item.setProductAttributeName(param.getName());
            productAttributeValueMapper.updateById(item);
        }
    }
}

package com.coca.shoppingproductservice.mapper;

import com.coca.shoppingmodel.entity.pms.PmsProductAttributeValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 存储产品参数信息的表 Mapper 接口
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
public interface PmsProductAttributeValueMapper extends BaseMapper<PmsProductAttributeValue> {
    int insertList(@Param("list") List<PmsProductAttributeValue> productAttributeValueList);
}

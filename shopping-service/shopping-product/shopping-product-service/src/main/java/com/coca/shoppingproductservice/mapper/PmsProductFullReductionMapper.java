package com.coca.shoppingproductservice.mapper;

import com.coca.shoppingmodel.entity.pms.PmsProductFullReduction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 产品满减表(只针对同商品) Mapper 接口
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
public interface PmsProductFullReductionMapper extends BaseMapper<PmsProductFullReduction> {
    int insertList(@Param("list") List<PmsProductFullReduction> productFullReductionList);
}

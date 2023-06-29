package com.coca.shoppingproductservice.mapper;

import com.coca.shoppingmodel.entity.pms.PmsProductLadder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 产品阶梯价格表(只针对同商品) Mapper 接口
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
public interface PmsProductLadderMapper extends BaseMapper<PmsProductLadder> {
    int insertList(@Param("list") List<PmsProductLadder> productLadderList);
}

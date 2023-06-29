package com.coca.shoppingproductservice.mapper;

import com.coca.shoppingmodel.dto.PromotionProduct;
import com.coca.shoppingmodel.entity.pms.PmsProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品信息 Mapper 接口
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
public interface PmsProductMapper extends BaseMapper<PmsProduct> {
    List<PromotionProduct> getPromotionProductList(@Param("ids") List<Long> ids);
}

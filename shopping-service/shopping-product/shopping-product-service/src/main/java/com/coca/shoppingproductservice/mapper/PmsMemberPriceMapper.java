package com.coca.shoppingproductservice.mapper;

import com.coca.shoppingmodel.entity.pms.PmsMemberPrice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品会员价格表 Mapper 接口
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
public interface PmsMemberPriceMapper extends BaseMapper<PmsMemberPrice> {
    int insertList(@Param("list") List<PmsMemberPrice> memberPriceList);
}

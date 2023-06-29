package com.coca.shoppingorderservice.mapper;

import com.coca.shoppingmodel.entity.oms.OmsOrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单中所包含的商品 Mapper 接口
 * </p>
 *
 * @author coca
 * @since 2023-06-28
 */
public interface OmsOrderItemMapper extends BaseMapper<OmsOrderItem> {
    int insertList(@Param("list") List<OmsOrderItem> list);
}

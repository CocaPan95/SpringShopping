package com.coca.shoppingmbg.mapper;

import com.coca.shoppingmbg.model.OmsCartItem;
import com.coca.shoppingmbg.model.OmsCartItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsCartItemMapper {
    long countByExample(OmsCartItemExample example);

    int deleteByExample(OmsCartItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OmsCartItem row);

    int insertSelective(OmsCartItem row);

    List<OmsCartItem> selectByExample(OmsCartItemExample example);

    OmsCartItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") OmsCartItem row, @Param("example") OmsCartItemExample example);

    int updateByExample(@Param("row") OmsCartItem row, @Param("example") OmsCartItemExample example);

    int updateByPrimaryKeySelective(OmsCartItem row);

    int updateByPrimaryKey(OmsCartItem row);
}
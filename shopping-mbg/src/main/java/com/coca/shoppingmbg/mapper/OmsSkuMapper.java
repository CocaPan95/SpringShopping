package com.coca.shoppingmbg.mapper;

import com.coca.shoppingmbg.model.OmsSku;
import com.coca.shoppingmbg.model.OmsSkuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsSkuMapper {
    long countByExample(OmsSkuExample example);

    int deleteByExample(OmsSkuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OmsSku row);

    int insertSelective(OmsSku row);

    List<OmsSku> selectByExample(OmsSkuExample example);

    OmsSku selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") OmsSku row, @Param("example") OmsSkuExample example);

    int updateByExample(@Param("row") OmsSku row, @Param("example") OmsSkuExample example);

    int updateByPrimaryKeySelective(OmsSku row);

    int updateByPrimaryKey(OmsSku row);
}
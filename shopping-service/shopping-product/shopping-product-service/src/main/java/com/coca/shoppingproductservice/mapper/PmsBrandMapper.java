package com.coca.shoppingproductservice.mapper;

import com.coca.shoppingmodel.domain.product.PmsBrand;
import com.coca.shoppingmodel.domain.product.PmsBrandExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmsBrandMapper {
    long countByExample(PmsBrandExample example);

    int deleteByExample(PmsBrandExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsBrand row);

    int insertSelective(PmsBrand row);

    List<PmsBrand> selectByExampleWithBLOBs(PmsBrandExample example);

    List<PmsBrand> selectByExample(PmsBrandExample example);

    PmsBrand selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") PmsBrand row, @Param("example") PmsBrandExample example);

    int updateByExampleWithBLOBs(@Param("row") PmsBrand row, @Param("example") PmsBrandExample example);

    int updateByExample(@Param("row") PmsBrand row, @Param("example") PmsBrandExample example);

    int updateByPrimaryKeySelective(PmsBrand row);

    int updateByPrimaryKeyWithBLOBs(PmsBrand row);

    int updateByPrimaryKey(PmsBrand row);
}
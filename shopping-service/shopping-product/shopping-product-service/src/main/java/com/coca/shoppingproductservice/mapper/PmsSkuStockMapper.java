package com.coca.shoppingproductservice.mapper;

import com.coca.shoppingmodel.domain.product.PmsSkuStock;
import com.coca.shoppingmodel.domain.product.PmsSkuStockExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmsSkuStockMapper {
    long countByExample(PmsSkuStockExample example);

    int deleteByExample(PmsSkuStockExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsSkuStock row);

    int insertSelective(PmsSkuStock row);

    List<PmsSkuStock> selectByExample(PmsSkuStockExample example);

    PmsSkuStock selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") PmsSkuStock row, @Param("example") PmsSkuStockExample example);

    int updateByExample(@Param("row") PmsSkuStock row, @Param("example") PmsSkuStockExample example);

    int updateByPrimaryKeySelective(PmsSkuStock row);

    int updateByPrimaryKey(PmsSkuStock row);
}
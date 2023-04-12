package com.coca.shoppingsmsservice.mapper;


import com.coca.shoppingmodel.domain.sms.SmsCouponHistory;
import com.coca.shoppingmodel.domain.sms.SmsCouponHistoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SmsCouponHistoryMapper {
    long countByExample(SmsCouponHistoryExample example);

    int deleteByExample(SmsCouponHistoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsCouponHistory row);

    int insertSelective(SmsCouponHistory row);

    List<SmsCouponHistory> selectByExample(SmsCouponHistoryExample example);

    SmsCouponHistory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") SmsCouponHistory row, @Param("example") SmsCouponHistoryExample example);

    int updateByExample(@Param("row") SmsCouponHistory row, @Param("example") SmsCouponHistoryExample example);

    int updateByPrimaryKeySelective(SmsCouponHistory row);

    int updateByPrimaryKey(SmsCouponHistory row);
}
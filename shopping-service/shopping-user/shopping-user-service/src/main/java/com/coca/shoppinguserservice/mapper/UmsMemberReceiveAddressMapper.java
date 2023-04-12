package com.coca.shoppinguserservice.mapper;


import com.coca.shoppingmodel.domain.user.UmsMemberReceiveAddress;
import com.coca.shoppingmodel.domain.user.UmsMemberReceiveAddressExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsMemberReceiveAddressMapper {
    long countByExample(UmsMemberReceiveAddressExample example);

    int deleteByExample(UmsMemberReceiveAddressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberReceiveAddress row);

    int insertSelective(UmsMemberReceiveAddress row);

    List<UmsMemberReceiveAddress> selectByExample(UmsMemberReceiveAddressExample example);

    UmsMemberReceiveAddress selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") UmsMemberReceiveAddress row, @Param("example") UmsMemberReceiveAddressExample example);

    int updateByExample(@Param("row") UmsMemberReceiveAddress row, @Param("example") UmsMemberReceiveAddressExample example);

    int updateByPrimaryKeySelective(UmsMemberReceiveAddress row);

    int updateByPrimaryKey(UmsMemberReceiveAddress row);
}
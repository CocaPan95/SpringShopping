package com.coca.shoppinguserservice.mapper;



import com.coca.shoppingmodel.domain.user.UmsAdmin;
import com.coca.shoppingmodel.domain.user.UmsAdminExample;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface UmsAdminMapper {
    long countByExample(UmsAdminExample example);

    int deleteByExample(UmsAdminExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsAdmin row);

    int insertSelective(UmsAdmin row);

    List<UmsAdmin> selectByExample(UmsAdminExample example);

    UmsAdmin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") UmsAdmin row, @Param("example") UmsAdminExample example);

    int updateByExample(@Param("row") UmsAdmin row, @Param("example") UmsAdminExample example);

    int updateByPrimaryKeySelective(UmsAdmin row);

    int updateByPrimaryKey(UmsAdmin row);
}
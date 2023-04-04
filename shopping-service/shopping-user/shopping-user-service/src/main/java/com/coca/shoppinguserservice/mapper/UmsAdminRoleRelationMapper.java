package com.coca.shoppinguserservice.mapper;

import com.coca.shoppingmodel.domain.user.UmsAdminRoleRelation;
import com.coca.shoppingmodel.domain.user.UmsAdminRoleRelationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsAdminRoleRelationMapper {
    long countByExample(UmsAdminRoleRelationExample example);

    int deleteByExample(UmsAdminRoleRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsAdminRoleRelation row);

    int insertSelective(UmsAdminRoleRelation row);

    List<UmsAdminRoleRelation> selectByExample(UmsAdminRoleRelationExample example);

    UmsAdminRoleRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") UmsAdminRoleRelation row, @Param("example") UmsAdminRoleRelationExample example);

    int updateByExample(@Param("row") UmsAdminRoleRelation row, @Param("example") UmsAdminRoleRelationExample example);

    int updateByPrimaryKeySelective(UmsAdminRoleRelation row);

    int updateByPrimaryKey(UmsAdminRoleRelation row);
}
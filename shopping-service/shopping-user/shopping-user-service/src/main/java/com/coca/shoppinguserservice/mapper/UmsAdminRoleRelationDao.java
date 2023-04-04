package com.coca.shoppinguserservice.mapper;

import com.coca.shoppingmodel.domain.user.UmsRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义后台用户与角色管理Dao
 * Created by macro on 2018/10/8.
 */
public interface UmsAdminRoleRelationDao {


    /**
     * 获取用于所有角色
     */
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);


}

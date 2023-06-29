package com.coca.shoppinguserservice.mapper;

import com.coca.shoppingmodel.entity.ums.UmsAdminRoleRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coca.shoppingmodel.entity.ums.UmsResource;
import com.coca.shoppingmodel.entity.ums.UmsRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台用户和角色关系表 Mapper 接口
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
public interface UmsAdminRoleRelationMapper extends BaseMapper<UmsAdminRoleRelation> {

}

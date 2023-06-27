package com.coca.shoppinguserservice.service.impl;

import com.coca.shoppingmodel.entity.ums.UmsAdminPermissionRelation;
import com.coca.shoppinguserservice.mapper.UmsAdminPermissionRelationMapper;
import com.coca.shoppinguserservice.service.IUmsAdminPermissionRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限) 服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
@Service
public class UmsAdminPermissionRelationServiceImpl extends ServiceImpl<UmsAdminPermissionRelationMapper, UmsAdminPermissionRelation> implements IUmsAdminPermissionRelationService {

}

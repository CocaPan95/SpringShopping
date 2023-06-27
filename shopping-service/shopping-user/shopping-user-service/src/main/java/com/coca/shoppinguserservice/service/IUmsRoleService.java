package com.coca.shoppinguserservice.service;

import com.coca.shoppingmodel.entity.ums.UmsRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
public interface IUmsRoleService extends IService<UmsRole> {
    List<UmsRole> getRoleList(Long adminId);
    int updateRole(Long adminId, List<Long> roleIds);
}

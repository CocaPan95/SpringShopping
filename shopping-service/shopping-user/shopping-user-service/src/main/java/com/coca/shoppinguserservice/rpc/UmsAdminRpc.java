package com.coca.shoppinguserservice.rpc;

import com.coca.shoppingmodel.dto.UmsAdminParam;
import com.coca.shoppingmodel.dto.UpdateAdminPasswordParam;
import com.coca.shoppingmodel.dto.UserDto;
import com.coca.shoppingmodel.entity.ums.UmsAdmin;
import com.coca.shoppingmodel.entity.ums.UmsResource;
import com.coca.shoppingmodel.entity.ums.UmsRole;
import com.coca.shoppinguserapi.IUmsAdminRpcService;
import com.coca.shoppinguserservice.service.*;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class UmsAdminRpc implements IUmsAdminRpcService {
    @Autowired
    private IUmsAdminService adminService;
    @Autowired
    private IUmsAdminLoginLogService adminLoginLogService;
    @Autowired
    private IUmsResourceService resourceService;
    @Autowired
    private IUmsRoleService roleService;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        return adminService.getAdminByUsername(username);
    }

    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        return adminService.register(umsAdminParam);
    }

    @Override
    public void insertLoginLog(String username) {
        adminLoginLogService.insertLoginLog(username);
    }

    @Override
    public UmsAdmin getItem(Long id) {
        return adminService.getItem(id);
    }

    @Override
    public List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum) {
        return adminService.list(keyword,pageSize,pageNum);
    }
    @Override
    public int update(Long id, UmsAdmin admin) {
        return adminService.update(id,admin);
    }

    @Override
    public int delete(Long id) {
        return adminService.delete(id);
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        return roleService.updateRole(adminId,roleIds);
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return roleService.getRoleList(adminId);
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        return resourceService.getResourceList(adminId);
    }

    @Override
    public int updatePassword(UpdateAdminPasswordParam param) {
        return adminService.updatePassword(param);
    }

    @Override
    public UserDto loadUserByUsername(String username) {
        return adminService.loadUserByUsername(username);
    }
}

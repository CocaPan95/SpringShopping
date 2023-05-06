package com.coca.shoppinguserservice.rpc;

import com.coca.shoppingmodel.domain.user.*;
import com.coca.shoppingmodel.dto.UmsAdminParam;
import com.coca.shoppingmodel.dto.UpdateAdminPasswordParam;
import com.coca.shoppingmodel.dto.UserDto;
import com.coca.shoppinguserapi.UmsAdminService;
import com.coca.shoppinguserservice.service.AdminService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class UmsAdminRpc implements UmsAdminService {
    @Autowired
    private AdminService adminService;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        return adminService.getAdminByUsername(username);
    }

    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        return adminService.register(umsAdminParam);
    }

    /**
     * 添加登录记录
     *
     * @param username 用户名
     */
    @Override
    public void insertLoginLog(String username) {
        adminService.insertLoginLog(username);
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
        return adminService.updateRole(adminId,roleIds);
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return adminService.getRoleList(adminId);
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        return adminService.getResourceList(adminId);
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

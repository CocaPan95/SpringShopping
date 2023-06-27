package com.coca.shoppinguserapi;


import com.coca.shoppingmodel.dto.UmsAdminParam;
import com.coca.shoppingmodel.dto.UpdateAdminPasswordParam;
import com.coca.shoppingmodel.dto.UserDto;
import com.coca.shoppingmodel.entity.ums.UmsAdmin;
import com.coca.shoppingmodel.entity.ums.UmsResource;
import com.coca.shoppingmodel.entity.ums.UmsRole;

import java.util.List;

public interface IUmsAdminRpcService {
    UmsAdmin getAdminByUsername(String username);
    UmsAdmin register(UmsAdminParam umsAdminParam);
    UserDto loadUserByUsername(String username);
    List<UmsRole> getRoleList(Long adminId);
    void insertLoginLog(String username);
    UmsAdmin getItem(Long id);
    List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum);
    int update(Long id, UmsAdmin admin);
    int delete(Long id);
    int updateRole(Long adminId, List<Long> roleIds);
    List<UmsResource> getResourceList(Long adminId);
    int updatePassword(UpdateAdminPasswordParam param);
}

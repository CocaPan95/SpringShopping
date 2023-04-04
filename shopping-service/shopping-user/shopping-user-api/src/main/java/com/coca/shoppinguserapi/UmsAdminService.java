package com.coca.shoppinguserapi;

import com.coca.shoppingmodel.api.CommonResult;
import com.coca.shoppingmodel.domain.user.UmsAdmin;
import com.coca.shoppingmodel.domain.user.UmsRole;
import com.coca.shoppingmodel.dto.UserDto;

import java.util.List;

public interface UmsAdminService {
    UmsAdmin getAdminByUsername(String username);

    UserDto loadUserByUsername(String username);
    List<UmsRole> getRoleList(Long adminId);

}

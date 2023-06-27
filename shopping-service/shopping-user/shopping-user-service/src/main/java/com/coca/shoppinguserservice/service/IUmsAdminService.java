package com.coca.shoppinguserservice.service;

import com.coca.shoppingmodel.dto.UmsAdminParam;
import com.coca.shoppingmodel.dto.UpdateAdminPasswordParam;
import com.coca.shoppingmodel.dto.UserDto;
import com.baomidou.mybatisplus.extension.service.IService;
import com.coca.shoppingmodel.entity.ums.UmsAdmin;
import com.coca.shoppingmodel.entity.ums.UmsResource;
import com.coca.shoppingmodel.entity.ums.UmsRole;

import java.util.List;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
public interface IUmsAdminService extends IService<UmsAdmin> {
    UmsAdmin getAdminByUsername(String username);

    UmsAdmin register(UmsAdminParam umsAdminParam);

    UserDto loadUserByUsername(String username);


    UmsAdmin getItem(Long id);

    List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum);

    int update(Long id, UmsAdmin admin);

    int delete(Long id);


    int updatePassword(UpdateAdminPasswordParam param);
}

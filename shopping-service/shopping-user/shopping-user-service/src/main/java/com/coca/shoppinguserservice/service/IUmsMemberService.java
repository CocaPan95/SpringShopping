package com.coca.shoppinguserservice.service;

import com.coca.shoppingmodel.dto.UserDto;
import com.coca.shoppingmodel.entity.ums.UmsMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
public interface IUmsMemberService extends IService<UmsMember> {
    UserDto loadUserByUsername(String username);

    UmsMember getByUsername(String username);

    //根据Id获取用户
    UmsMember getById(Long id);

    /**
     * 根据会员id修改会员积分
     */
    void updateIntegration(Long id, Integer integration);
}

package com.coca.shoppinguserservice.service;

import com.coca.shoppingmodel.entity.ums.UmsAdminLoginLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 后台用户登录日志表 服务类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
public interface IUmsAdminLoginLogService extends IService<UmsAdminLoginLog> {
    void insertLoginLog(String username);
}

package com.coca.shoppinguserservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coca.shoppingmodel.entity.ums.UmsAdmin;
import com.coca.shoppingmodel.entity.ums.UmsAdminLoginLog;
import com.coca.shoppinguserservice.mapper.UmsAdminLoginLogMapper;
import com.coca.shoppinguserservice.service.IUmsAdminLoginLogService;
import com.coca.shoppinguserservice.service.IUmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 后台用户登录日志表 服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
@Service
public class UmsAdminLoginLogServiceImpl extends ServiceImpl<UmsAdminLoginLogMapper, UmsAdminLoginLog> implements IUmsAdminLoginLogService {
    @Autowired
    private IUmsAdminService adminService;

    @Override
    public void insertLoginLog(String username) {
        UmsAdmin admin = adminService.getAdminByUsername(username);
        if (admin == null) return;
        UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(java.time.LocalDateTime.now());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        baseMapper.insert(loginLog);
    }
}

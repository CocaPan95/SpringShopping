package com.coca.shoppinguserservice.service;

import com.coca.shoppingmodel.entity.ums.UmsResource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 后台资源表 服务类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
public interface IUmsResourceService extends IService<UmsResource> {
    Map<String, List<String>> initResourceRolesMap();
    List<UmsResource> getResourceList(Long adminId);
}

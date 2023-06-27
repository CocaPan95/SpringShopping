package com.coca.shoppinguserservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coca.shoppingcommon.constant.AuthConstant;
import com.coca.shoppingcommon.service.RedisService;
import com.coca.shoppingmodel.entity.ums.UmsResource;
import com.coca.shoppingmodel.entity.ums.UmsRole;
import com.coca.shoppingmodel.entity.ums.UmsRoleResourceRelation;
import com.coca.shoppinguserservice.mapper.UmsAdminRoleRelationMapper;
import com.coca.shoppinguserservice.mapper.UmsResourceMapper;
import com.coca.shoppinguserservice.mapper.UmsRoleMapper;
import com.coca.shoppinguserservice.service.IUmsResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * <p>
 * 后台资源表 服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
@Service
public class UmsResourceServiceImpl extends ServiceImpl<UmsResourceMapper, UmsResource> implements IUmsResourceService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private UmsRoleMapper roleMapper;
    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        QueryWrapper<UmsResource> wrapper = new QueryWrapper<>();
        wrapper.eq("admin_id", adminId);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public Map<String, List<String>> initResourceRolesMap() {
        Map<String,List<String>> resourceRoleMap = new TreeMap<>();
        List<UmsResource> resourceList = baseMapper.selectList(new QueryWrapper<>());
        List<UmsRole> roleList = roleMapper.selectList(new QueryWrapper<>());
        List<UmsRoleResourceRelation> relationList = adminRoleRelationMapper.selectList(new QueryWrapper());
        for (UmsResource resource : resourceList) {
            Set<Long> roleIds = relationList.stream().filter(item -> item.getResourceId().equals(resource.getId())).map(UmsRoleResourceRelation::getRoleId).collect(Collectors.toSet());
            List<String> roleNames = roleList.stream().filter(item -> roleIds.contains(item.getId())).map(item -> item.getId() + "_" + item.getName()).collect(Collectors.toList());
            resourceRoleMap.put("/**/"+resource.getUrl(),roleNames);
        }
        redisService.del(AuthConstant.RESOURCE_ROLES_MAP_KEY);
        redisService.hSetAll(AuthConstant.RESOURCE_ROLES_MAP_KEY, resourceRoleMap);
        return resourceRoleMap;

    }
}

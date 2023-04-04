package com.coca.shoppinguserservice.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.coca.shoppingcommon.constant.AuthConstant;
import com.coca.shoppingcommon.exception.Asserts;
import com.coca.shoppingmodel.api.CommonResult;
import com.coca.shoppingmodel.domain.user.UmsAdmin;
import com.coca.shoppingmodel.domain.user.UmsAdminExample;
import com.coca.shoppingmodel.domain.user.UmsRole;
import com.coca.shoppingmodel.dto.UserDto;
import com.coca.shoppinguserapi.UmsAdminService;
import com.coca.shoppinguserservice.mapper.UmsAdminMapper;
import com.coca.shoppinguserservice.mapper.UmsAdminRoleRelationDao;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@DubboService
public class UmsAdminImpl implements UmsAdminService {
    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Autowired
    private UmsAdminRoleRelationDao umsAdminRoleRelationDao;
    @Override
    public UserDto loadUserByUsername(String username){
        //获取用户信息
        UmsAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            List<UmsRole> roleList = getRoleList(admin.getId());
            UserDto userDTO = new UserDto();
            BeanUtils.copyProperties(admin,userDTO);
            if(CollUtil.isNotEmpty(roleList)){
                List<String> roleStrList = roleList.stream().map(item -> item.getId() + "_" + item.getName()).collect(Collectors.toList());
                userDTO.setRoles(roleStrList);
            }
            return userDTO;
        }
        return null;
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return umsAdminRoleRelationDao.getRoleList(adminId);
    }
    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = umsAdminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }

}

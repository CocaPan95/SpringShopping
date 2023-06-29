package com.coca.shoppinguserservice.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coca.shoppingcommon.service.RedisService;
import com.coca.shoppingmodel.dto.UmsAdminParam;
import com.coca.shoppingmodel.dto.UpdateAdminPasswordParam;
import com.coca.shoppingmodel.dto.UserDto;
import com.coca.shoppingmodel.entity.ums.UmsAdmin;
import com.coca.shoppingmodel.entity.ums.UmsRole;
import com.coca.shoppinguserservice.mapper.UmsAdminMapper;
import com.coca.shoppinguserservice.service.IUmsAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coca.shoppinguserservice.service.IUmsRoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements IUmsAdminService {
    @Autowired
    private IUmsRoleService roleService;

    @Autowired
    private RedisService redisService;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;


    @Override
    public UmsAdmin getAdminByUsername(String username) {
        QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<UmsAdmin>();
        wrapper.eq("username", username);
        List<UmsAdmin> adminList = baseMapper.selectList(wrapper);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }

    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(java.time.LocalDateTime.now());
        umsAdmin.setStatus(1);
        //查询是否有相同用户名的用户
        QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<UmsAdmin>();
        wrapper.eq("username", umsAdminParam.getUsername());
        List<UmsAdmin> umsAdminList = baseMapper.selectList(wrapper);
        if (umsAdminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = BCrypt.hashpw(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        baseMapper.insert(umsAdmin);
        //存缓存
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + umsAdmin.getId();
        redisService.set(key, umsAdmin, REDIS_EXPIRE);
        return umsAdmin;
    }


    @Override
    public UmsAdmin getItem(Long id) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + id;
        UmsAdmin admin = (UmsAdmin) redisService.get(key);
        if (admin == null) {
            admin = baseMapper.selectById(id);
            if (admin != null) {
                redisService.set(key, admin, REDIS_EXPIRE);
            }
        }
        return admin;
    }

    @Override
    public List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<UmsAdmin>();
        if (!StringUtils.isEmpty(keyword)) {
            wrapper.like("username",keyword)
                    .or()
                    .like("nike_name",keyword);
        }
        return baseMapper.selectList(wrapper);
    }

    @Override
    public int update(Long id, UmsAdmin admin) {
        admin.setId(id);
        UmsAdmin rawAdmin = baseMapper.selectById(id);
        if (rawAdmin.getPassword().equals(admin.getPassword())) {
            //与原加密密码相同的不需要修改
            admin.setPassword(null);
        } else {
            //与原加密密码不同的需要加密修改
            if (StrUtil.isEmpty(admin.getPassword())) {
                admin.setPassword(null);
            } else {
                admin.setPassword(BCrypt.hashpw(admin.getPassword()));
            }
        }
        int count = baseMapper.updateById(admin);
        //删缓存
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getId();
        redisService.del(key);
        return count;
    }

    @Override
    public int delete(Long id) {
        int count = baseMapper.deleteById(id);
        //删缓存
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + id;
        redisService.del(key);
        return count;
    }


    @Override
    public int updatePassword(UpdateAdminPasswordParam param) {
        if (StrUtil.isEmpty(param.getUsername())
                || StrUtil.isEmpty(param.getOldPassword())
                || StrUtil.isEmpty(param.getNewPassword())) {
            return -1;
        }
        QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<>();
        wrapper.eq("username",param.getUsername());
        List<UmsAdmin> adminList = baseMapper.selectList(wrapper);
        if (CollUtil.isEmpty(adminList)) {
            return -2;
        }
        UmsAdmin umsAdmin = adminList.get(0);
        if (!BCrypt.checkpw(param.getOldPassword(), umsAdmin.getPassword())) {
            return -3;
        }
        umsAdmin.setPassword(BCrypt.hashpw(param.getNewPassword()));
        baseMapper.updateById(umsAdmin);
        return 1;
    }


    @Override
    public UserDto loadUserByUsername(String username) {
        //获取用户信息
        UmsAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            List<UmsRole> roleList = roleService.getRoleList(admin.getId());
            UserDto userDTO = new UserDto();
            BeanUtils.copyProperties(admin, userDTO);
            if (CollUtil.isNotEmpty(roleList)) {
                List<String> roleStrList = roleList.stream().map(item -> item.getId() + "_" + item.getName()).collect(Collectors.toList());
                userDTO.setRoles(roleStrList);
            }
            return userDTO;
        }
        return null;
    }
}

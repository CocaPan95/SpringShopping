package com.coca.shoppinguserservice.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coca.shoppingcommon.service.RedisService;
import com.coca.shoppingmodel.dto.UserDto;
import com.coca.shoppingmodel.entity.ums.UmsMember;
import com.coca.shoppingmodel.entity.ums.UmsResource;
import com.coca.shoppinguserservice.mapper.UmsMemberMapper;
import com.coca.shoppinguserservice.service.IUmsMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements IUmsMemberService {
    @Autowired
    private RedisService redisService;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.member}")
    private String REDIS_KEY_MEMBER;

    @Override
    public UserDto loadUserByUsername(String username) {
        UmsMember member = getByUsername(username);
        if (member != null) {
            UserDto userDto = new UserDto();
            BeanUtil.copyProperties(member, userDto);
            userDto.setRoles(CollUtil.toList("前台会员"));
            return userDto;
        }
        return null;
    }

    @Override
    public UmsMember getByUsername(String username) {
        QueryWrapper<UmsMember> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        List<UmsMember> memberList = baseMapper.selectList(wrapper);
        if (!CollectionUtils.isEmpty(memberList)) {
            return memberList.get(0);
        }
        return null;
    }

    @Override
    public UmsMember getById(Long id) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + id;
        UmsMember member = (UmsMember) redisService.get(key);
        if (member != null) {
            return member;
        }
        member = baseMapper.selectById(id);
        if (member != null) {
            redisService.set(key, member, REDIS_EXPIRE);
        }
        return member;
    }

    @Override
    public void updateIntegration(Long id, Integer integration) {
        UmsMember record = new UmsMember();
        record.setId(id);
        record.setIntegration(integration);
        baseMapper.updateById(record);
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + id;
        redisService.del(key);
    }
}

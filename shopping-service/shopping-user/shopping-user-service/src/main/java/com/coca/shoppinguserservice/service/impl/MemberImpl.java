package com.coca.shoppinguserservice.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.coca.shoppingcommon.service.RedisService;
import com.coca.shoppingmodel.domain.user.UmsMember;
import com.coca.shoppingmodel.domain.user.UmsMemberExample;
import com.coca.shoppingmodel.dto.UserDto;
import com.coca.shoppinguserservice.mapper.UmsMemberMapper;
import com.coca.shoppinguserservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class MemberImpl implements MemberService {
    @Autowired
    private UmsMemberMapper umsMemberMapper;
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
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsMember> memberList = umsMemberMapper.selectByExample(example);
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
        member = umsMemberMapper.selectByPrimaryKey(id);
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
        umsMemberMapper.updateByPrimaryKeySelective(record);
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + id;
        redisService.del(key);
    }
}

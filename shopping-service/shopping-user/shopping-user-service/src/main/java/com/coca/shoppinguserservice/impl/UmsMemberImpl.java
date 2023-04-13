package com.coca.shoppinguserservice.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.coca.shoppingcommon.constant.AuthConstant;
import com.coca.shoppingcommon.exception.Asserts;
import com.coca.shoppingcommon.service.RedisService;
import com.coca.shoppingmodel.api.ResultCode;
import com.coca.shoppingmodel.domain.user.UmsMember;
import com.coca.shoppingmodel.domain.user.UmsMemberExample;
import com.coca.shoppingmodel.dto.UserDto;
import com.coca.shoppinguserapi.UmsMemberService;
import com.coca.shoppinguserservice.mapper.UmsMemberMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@DubboService
public class UmsMemberImpl implements UmsMemberService {
    @Autowired
    private UmsMemberMapper umsMemberMapper;
    @Autowired
    private HttpServletRequest request;
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
        if(member!=null){
            UserDto userDto = new UserDto();
            BeanUtil.copyProperties(member,userDto);
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
    public UmsMember getCurrentMember() {
        String userStr = request.getHeader(AuthConstant.USER_TOKEN_HEADER);
        if(StrUtil.isEmpty(userStr)){
            Asserts.fail(ResultCode.UNAUTHORIZED);
        }
        UserDto userDto = JSONUtil.toBean(userStr, UserDto.class);
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + userDto.getId();
        UmsMember member =(UmsMember)redisService.get(key) ;
        if(member!=null){
            return member;
        }else{
            member = getById(userDto.getId());
            redisService.set(key,member,REDIS_EXPIRE);
            return member;
        }
    }
    @Override
    public UmsMember getById(Long id) {
        return umsMemberMapper.selectByPrimaryKey(id);
    }
    @Override
    public void updateIntegration(Long id, Integer integration) {
        UmsMember record=new UmsMember();
        record.setId(id);
        record.setIntegration(integration);
        umsMemberMapper.updateByPrimaryKeySelective(record);
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + id;
        redisService.del(key);
    }

}

package com.coca.shoppinguserservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coca.shoppingmodel.entity.ums.UmsMemberReceiveAddress;
import com.coca.shoppingmodel.entity.ums.UmsResource;
import com.coca.shoppinguserservice.mapper.UmsMemberReceiveAddressMapper;
import com.coca.shoppinguserservice.service.IUmsMemberReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 会员收货地址表 服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
@Service
public class UmsMemberReceiveAddressServiceImpl extends ServiceImpl<UmsMemberReceiveAddressMapper, UmsMemberReceiveAddress> implements IUmsMemberReceiveAddressService {

    @Override
    public List<UmsMemberReceiveAddress> GetMemberReceiveAddressList(Long memberId){
        QueryWrapper<UmsMemberReceiveAddress> wrapper = new QueryWrapper<>();
        wrapper.eq("member_id", memberId);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public UmsMemberReceiveAddress getItem(Long MemberId,Long id) {
        QueryWrapper<UmsMemberReceiveAddress> wrapper = new QueryWrapper<>();
        wrapper.eq("member_id", MemberId);
        wrapper.eq("id", id);
        List<UmsMemberReceiveAddress> addressList = baseMapper.selectList(wrapper);
        if(!CollectionUtils.isEmpty(addressList)){
            return addressList.get(0);
        }
        return null;
    }
}

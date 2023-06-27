package com.coca.shoppinguserservice.mapper;

import com.coca.shoppingmodel.entity.ums.UmsMemberReceiveAddress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 会员收货地址表 Mapper 接口
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
public interface UmsMemberReceiveAddressMapper extends BaseMapper<UmsMemberReceiveAddress> {
    List<UmsMemberReceiveAddress> GetMemberReceiveAddressList(Long memberId);

    /**
     * 获取地址详情
     * @param id 地址id
     */
    UmsMemberReceiveAddress getItem(Long MemberId,Long id);
}

package com.coca.shoppinguserservice.service;

import com.coca.shoppingmodel.entity.ums.UmsMemberReceiveAddress;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 会员收货地址表 服务类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
public interface IUmsMemberReceiveAddressService extends IService<UmsMemberReceiveAddress> {
    List<UmsMemberReceiveAddress> GetMemberReceiveAddressList(Long memberId);
    UmsMemberReceiveAddress getItem(Long MemberId,Long id);
}

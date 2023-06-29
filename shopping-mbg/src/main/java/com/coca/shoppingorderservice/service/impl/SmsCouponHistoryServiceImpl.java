package com.coca.shoppingorderservice.service.impl;

import com.coca.shoppingorderservice.entity.SmsCouponHistory;
import com.coca.shoppingorderservice.mapper.SmsCouponHistoryMapper;
import com.coca.shoppingorderservice.service.ISmsCouponHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 优惠券使用、领取历史表 服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-29
 */
@Service
public class SmsCouponHistoryServiceImpl extends ServiceImpl<SmsCouponHistoryMapper, SmsCouponHistory> implements ISmsCouponHistoryService {

}

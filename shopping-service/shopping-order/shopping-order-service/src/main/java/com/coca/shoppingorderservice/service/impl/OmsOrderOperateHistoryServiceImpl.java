package com.coca.shoppingorderservice.service.impl;

import com.coca.shoppingmodel.entity.oms.OmsOrderOperateHistory;
import com.coca.shoppingorderservice.mapper.OmsOrderOperateHistoryMapper;
import com.coca.shoppingorderservice.service.IOmsOrderOperateHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单操作历史记录 服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-28
 */
@Service
public class OmsOrderOperateHistoryServiceImpl extends ServiceImpl<OmsOrderOperateHistoryMapper, OmsOrderOperateHistory> implements IOmsOrderOperateHistoryService {

}

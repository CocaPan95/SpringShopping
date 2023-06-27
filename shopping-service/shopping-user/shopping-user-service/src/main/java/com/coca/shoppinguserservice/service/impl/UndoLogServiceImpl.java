package com.coca.shoppinguserservice.service.impl;

import com.coca.shoppingmodel.entity.ums.UndoLog;
import com.coca.shoppinguserservice.mapper.UndoLogMapper;
import com.coca.shoppinguserservice.service.IUndoLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
@Service
public class UndoLogServiceImpl extends ServiceImpl<UndoLogMapper, UndoLog> implements IUndoLogService {

}

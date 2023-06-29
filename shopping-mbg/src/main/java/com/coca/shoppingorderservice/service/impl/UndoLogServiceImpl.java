package com.coca.shoppingorderservice.service.impl;

import com.coca.shoppingorderservice.entity.UndoLog;
import com.coca.shoppingorderservice.mapper.UndoLogMapper;
import com.coca.shoppingorderservice.service.IUndoLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-29
 */
@Service
public class UndoLogServiceImpl extends ServiceImpl<UndoLogMapper, UndoLog> implements IUndoLogService {

}

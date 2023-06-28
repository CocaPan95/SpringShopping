package com.coca.shoppingproductservice.service.impl;

import com.coca.shoppingmodel.entity.pms.UndoLog;
import com.coca.shoppingproductservice.mapper.UndoLogMapper;
import com.coca.shoppingproductservice.service.IUndoLogService;
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

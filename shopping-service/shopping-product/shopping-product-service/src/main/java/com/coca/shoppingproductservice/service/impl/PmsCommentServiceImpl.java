package com.coca.shoppingproductservice.service.impl;

import com.coca.shoppingmodel.entity.pms.PmsComment;
import com.coca.shoppingproductservice.mapper.PmsCommentMapper;
import com.coca.shoppingproductservice.service.IPmsCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品评价表 服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
@Service
public class PmsCommentServiceImpl extends ServiceImpl<PmsCommentMapper, PmsComment> implements IPmsCommentService {

}

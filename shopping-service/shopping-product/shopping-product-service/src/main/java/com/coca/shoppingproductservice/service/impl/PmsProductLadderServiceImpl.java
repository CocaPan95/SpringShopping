package com.coca.shoppingproductservice.service.impl;

import com.coca.shoppingmodel.entity.pms.PmsProductLadder;
import com.coca.shoppingproductservice.mapper.PmsProductLadderMapper;
import com.coca.shoppingproductservice.service.IPmsProductLadderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品阶梯价格表(只针对同商品) 服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
@Service
public class PmsProductLadderServiceImpl extends ServiceImpl<PmsProductLadderMapper, PmsProductLadder> implements IPmsProductLadderService {

}

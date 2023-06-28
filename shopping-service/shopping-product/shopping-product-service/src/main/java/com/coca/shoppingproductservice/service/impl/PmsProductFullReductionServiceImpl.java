package com.coca.shoppingproductservice.service.impl;

import com.coca.shoppingmodel.entity.pms.PmsProductFullReduction;
import com.coca.shoppingproductservice.mapper.PmsProductFullReductionMapper;
import com.coca.shoppingproductservice.service.IPmsProductFullReductionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品满减表(只针对同商品) 服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
@Service
public class PmsProductFullReductionServiceImpl extends ServiceImpl<PmsProductFullReductionMapper, PmsProductFullReduction> implements IPmsProductFullReductionService {

}

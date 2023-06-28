package com.coca.shoppingproductservice.service.impl;

import com.coca.shoppingmodel.entity.pms.PmsSkuStock;
import com.coca.shoppingproductservice.mapper.PmsSkuStockMapper;
import com.coca.shoppingproductservice.service.IPmsSkuStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * sku的库存 服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
@Service
public class PmsSkuStockServiceImpl extends ServiceImpl<PmsSkuStockMapper, PmsSkuStock> implements IPmsSkuStockService {

}

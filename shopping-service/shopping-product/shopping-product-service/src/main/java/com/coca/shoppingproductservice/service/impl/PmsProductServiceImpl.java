package com.coca.shoppingproductservice.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coca.shoppingcommon.exception.Asserts;
import com.coca.shoppingmodel.dto.PmsPortalProductDetail;
import com.coca.shoppingmodel.dto.PmsProductParam;
import com.coca.shoppingmodel.dto.PromotionProduct;
import com.coca.shoppingmodel.entity.pms.*;
import com.coca.shoppingmodel.entity.ums.UmsAdmin;
import com.coca.shoppingmodel.es.EsProduct;
import com.coca.shoppingproductservice.mapper.*;
import com.coca.shoppingproductservice.repository.EsProductRepository;
import com.coca.shoppingproductservice.service.IPmsProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coca.shoppingsmsapi.ISmsCouponRpcService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements IPmsProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductServiceImpl.class);
    @Autowired
    private PmsMemberPriceMapper memberPriceMapper;
    @Autowired
    private PmsProductLadderMapper productLadderMapper;
    @Autowired
    private PmsProductFullReductionMapper productFullReductionMapper;
    @Autowired
    private PmsSkuStockMapper skuStockMapper;
    @Autowired
    private PmsProductAttributeValueMapper productAttributeValueMapper;
    @Autowired
    private EsProductDao productDao;
    @Autowired
    private PmsBrandMapper brandMapper;
    @Autowired
    private PmsProductAttributeMapper productAttributeMapper;
    @DubboReference
    private ISmsCouponRpcService couponHistoryService;

    @Override
    public int create(PmsProductParam productParam) {
        PmsProduct product = productParam;
        product.setId(null);
        baseMapper.insert(product);
        //根据促销类型设置价格：会员价格、阶梯价格、满减价格
        Long productId = product.getId();

        //会员价格
        relateAndInsertList(memberPriceMapper, productParam.getMemberPriceList(), productId);
        //阶梯价格
        relateAndInsertList(productLadderMapper, productParam.getProductLadderList(), productId);
        //满减价格
        relateAndInsertList(productFullReductionMapper, productParam.getProductFullReductionList(), productId);
        //处理sku的编码
        handleSkuStockCode(productParam.getSkuStockList(), productId);
        //添加sku库存信息
        relateAndInsertList(skuStockMapper, productParam.getSkuStockList(), productId);
        //处理商品详情
        handleProductAttribute(productParam.getProductAttributeValueList(), productId);
        //添加商品参数,添加自定义商品规格
        relateAndInsertList(productAttributeMapper, productParam.getProductAttributeValueList(), productId);
        return 1;
    }

    private void handleProductAttribute(List<PmsProductAttributeValue> productAttributeValueList, Long productId) {
        List<Long> attrIds = productAttributeValueList.stream().map(t -> t.getProductAttributeId()).collect(Collectors.toList());
        LambdaQueryWrapper<PmsProductAttribute> attributeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        attributeLambdaQueryWrapper.in(PmsProductAttribute::getId,attrIds);
        Map<Long, PmsProductAttribute> longPmsProductAttributeMap = productAttributeMapper.selectList(attributeLambdaQueryWrapper).stream().collect(Collectors.toMap(PmsProductAttribute::getId, t -> t));
        for (PmsProductAttributeValue item:productAttributeValueList){
            PmsProductAttribute pmsProductAttribute = longPmsProductAttributeMap.get(item.getProductAttributeId());
            if (pmsProductAttribute!=null ){
                item.setProductAttributeName(pmsProductAttribute.getName());
                item.setProductAttributeType(pmsProductAttribute.getType());
            }
        }
    }

    @Override
    public int update(Long id, PmsProductParam productParam) {
        int count;
        //更新商品信息
        PmsProduct product = productParam;
        product.setId(id);
        baseMapper.updateById(product);
        //会员价格
        LambdaQueryWrapper<PmsMemberPrice> memberPriceQueryWrapperwrapper = new LambdaQueryWrapper<>();
        memberPriceQueryWrapperwrapper.eq(PmsMemberPrice::getProductId, id);
        memberPriceMapper.delete(memberPriceQueryWrapperwrapper);
        relateAndInsertList(memberPriceMapper, productParam.getMemberPriceList(), id);
        //阶梯价格
        LambdaQueryWrapper<PmsProductLadder> productLadderQueryWrapper = new LambdaQueryWrapper<>();
        productLadderQueryWrapper.eq(PmsProductLadder::getProductId, id);
        productLadderMapper.delete(productLadderQueryWrapper);
        relateAndInsertList(productLadderMapper, productParam.getProductLadderList(), id);
        //满减价格
        LambdaQueryWrapper<PmsProductFullReduction> pmsProductFullReductionQueryWrapper = new LambdaQueryWrapper<>();
        pmsProductFullReductionQueryWrapper.eq(PmsProductFullReduction::getProductId, id);
        productFullReductionMapper.delete(pmsProductFullReductionQueryWrapper);
        relateAndInsertList(productFullReductionMapper, productParam.getProductFullReductionList(), id);
        //修改sku库存信息
        handleUpdateSkuStockList(id, productParam);
        //修改商品参数,添加自定义商品规格
        LambdaQueryWrapper<PmsProductAttributeValue> productAttributeValueQueryWrapper = new LambdaQueryWrapper<>();
        productAttributeValueQueryWrapper.eq(PmsProductAttributeValue::getProductId, id);
        productAttributeValueMapper.delete(productAttributeValueQueryWrapper);

        relateAndInsertList(productAttributeValueMapper, productParam.getProductAttributeValueList(), id);
//        //关联专题
//        CmsSubjectProductRelationExample subjectProductRelationExample = new CmsSubjectProductRelationExample();
//        subjectProductRelationExample.createCriteria().andProductIdEqualTo(id);
//        subjectProductRelationMapper.deleteByExample(subjectProductRelationExample);
//        relateAndInsertList(subjectProductRelationDao, productParam.getSubjectProductRelationList(), id);
//        //关联优选
//        CmsPrefrenceAreaProductRelationExample prefrenceAreaExample = new CmsPrefrenceAreaProductRelationExample();
//        prefrenceAreaExample.createCriteria().andProductIdEqualTo(id);
//        prefrenceAreaProductRelationMapper.deleteByExample(prefrenceAreaExample);
//        relateAndInsertList(prefrenceAreaProductRelationDao, productParam.getPrefrenceAreaProductRelationList(), id);
        count = 1;
        return count;
    }

    @Override
    public int updatePublishStatus(List<Long> ids, Integer publishStatus) {
        int count = 0;
        for (long id : ids) {
            PmsProduct product = baseMapper.selectById(id);
            product.setPublishStatus(publishStatus);
            if (baseMapper.updateById(product) > 0) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        int count = 0;
        for (long id : ids) {
            PmsProduct product = baseMapper.selectById(id);
            product.setRecommandStatus(recommendStatus);
            if (baseMapper.updateById(product) > 0) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int updateNewStatus(List<Long> ids, Integer newStatus) {
        int count = 0;
        for (long id : ids) {
            PmsProduct product = baseMapper.selectById(id);
            product.setNewStatus(newStatus);
            if (baseMapper.updateById(product) > 0) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
        int count = 0;
        for (long id : ids) {
            PmsProduct product = baseMapper.selectById(id);
            product.setDeleteStatus(deleteStatus);
            if (baseMapper.updateById(product) > 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * 查询所有商品的优惠相关信息
     */
    @Override
    public List<PromotionProduct> getPromotionProductList(List<Long> productIdList) {
        return baseMapper.getPromotionProductList(productIdList);
    }


    @Override
    public PmsPortalProductDetail getProductDetail(Long productId) {
        PmsPortalProductDetail result = new PmsPortalProductDetail();
        //查询产品信息
        PmsProduct products = baseMapper.selectById(productId);
        if (products == null) {
            Asserts.fail("该产品不存在！");
        }
        result.setProduct(products);
        //查询品牌信息
        PmsBrand brands = brandMapper.selectById(products.getBrandId());
        result.setBrand(brands);
        //查询属性信息
        LambdaQueryWrapper<PmsProductAttribute> productAttributeQueryWrapper = new LambdaQueryWrapper<>();
        productAttributeQueryWrapper.eq(PmsProductAttribute::getProductAttributeCategoryId, products.getProductAttributeCategoryId());
        List<PmsProductAttribute> productAttributes = productAttributeMapper.selectList(productAttributeQueryWrapper);
        result.setProductAttributeList(productAttributes);
        //查询属性值相关信息
        LambdaQueryWrapper<PmsProductAttributeValue> productAttributeValueQueryWrapper = new LambdaQueryWrapper<>();
        productAttributeValueQueryWrapper.eq(PmsProductAttributeValue::getProductId, products.getId());
        List<PmsProductAttributeValue> pmsProductAttributeValues = productAttributeValueMapper.selectList(productAttributeValueQueryWrapper);
        result.setProductAttributeValueList(pmsProductAttributeValues);
        //获取产品库存信息
        LambdaQueryWrapper<PmsSkuStock> skuStockQueryWrapper = new LambdaQueryWrapper<>();
        skuStockQueryWrapper.eq(PmsSkuStock::getProductId, products.getId());
        List<PmsSkuStock> skuStockList = skuStockMapper.selectList(skuStockQueryWrapper);
        result.setSkuStockList(skuStockList);
        //获取阶梯价格
        LambdaQueryWrapper<PmsProductLadder> productLadderQueryWrapper = new LambdaQueryWrapper<>();
        productLadderQueryWrapper.eq(PmsProductLadder::getProductId, products.getId());
        List<PmsProductLadder> productLadderList = productLadderMapper.selectList(productLadderQueryWrapper);
        if (CollectionUtil.isNotEmpty(productLadderList)) {
            result.setProductLadderList(productLadderList);
        }
        //商品满减价格设置
        LambdaQueryWrapper<PmsProductFullReduction> pmsProductFullReductionQueryWrapper = new LambdaQueryWrapper<>();
        pmsProductFullReductionQueryWrapper.eq(PmsProductFullReduction::getProductId, products.getId());
        List<PmsProductFullReduction> pmsProductFullReductionList = productFullReductionMapper.selectList(pmsProductFullReductionQueryWrapper);
        if (CollectionUtil.isNotEmpty(pmsProductFullReductionList)) {
            result.setProductFullReductionList(pmsProductFullReductionList);
        }
        //获取商品可用优惠券
        result.setCouponList(couponHistoryService.getAvailableCouponList(products.getId(), products.getProductCategoryId()));
        return result;
    }

    private void handleUpdateSkuStockList(Long id, PmsProductParam productParam) {
        //当前的sku信息
        List<PmsSkuStock> currSkuList = productParam.getSkuStockList();
        //当前没有sku直接删除
        if (CollUtil.isEmpty(currSkuList)) {
            LambdaQueryWrapper<PmsSkuStock> skuStockQueryWrapper = new LambdaQueryWrapper<>();
            skuStockQueryWrapper.eq(PmsSkuStock::getProductId, id);
            skuStockMapper.delete(skuStockQueryWrapper);
            return;
        }
        //获取初始sku信息
        LambdaQueryWrapper<PmsSkuStock> skuStockQueryWrapper = new LambdaQueryWrapper<>();
        skuStockQueryWrapper.eq(PmsSkuStock::getProductId, id);
        List<PmsSkuStock> oriStuList = skuStockMapper.selectList(skuStockQueryWrapper);
        //获取新增sku信息
        List<PmsSkuStock> insertSkuList = currSkuList.stream().filter(item -> item.getId() == null).collect(Collectors.toList());
        //获取需要更新的sku信息
        List<PmsSkuStock> updateSkuList = currSkuList.stream().filter(item -> item.getId() != null).collect(Collectors.toList());
        List<Long> updateSkuIds = updateSkuList.stream().map(PmsSkuStock::getId).collect(Collectors.toList());
        //获取需要删除的sku信息
        List<PmsSkuStock> removeSkuList = oriStuList.stream().filter(item -> !updateSkuIds.contains(item.getId())).collect(Collectors.toList());
        handleSkuStockCode(insertSkuList, id);
        handleSkuStockCode(updateSkuList, id);
        //新增sku
        if (CollUtil.isNotEmpty(insertSkuList)) {
            relateAndInsertList(skuStockMapper, insertSkuList, id);
        }
        //删除sku
        if (CollUtil.isNotEmpty(removeSkuList)) {
            List<Long> removeSkuIds = removeSkuList.stream().map(PmsSkuStock::getId).collect(Collectors.toList());
            skuStockMapper.deleteBatchIds(removeSkuIds);
        }
        //修改sku
        if (CollUtil.isNotEmpty(updateSkuList)) {
            for (PmsSkuStock pmsSkuStock : updateSkuList) {
                skuStockMapper.updateById(pmsSkuStock);
            }
        }

    }

    private void handleSkuStockCode(List<PmsSkuStock> skuStockList, Long productId) {
        if (CollectionUtils.isEmpty(skuStockList)) return;
        for (int i = 0; i < skuStockList.size(); i++) {
            PmsSkuStock skuStock = skuStockList.get(i);
            if (StringUtils.isEmpty(skuStock.getSkuCode())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                StringBuilder sb = new StringBuilder();
                //日期
                sb.append(sdf.format(new Date()));
                //四位商品id
                sb.append(String.format("%04d", productId));
                //三位索引id
                sb.append(String.format("%03d", i + 1));
                skuStock.setSkuCode(sb.toString());
            }
        }
    }

    private void relateAndInsertList(Object dao, List dataList, Long productId) {
        try {
            if (CollectionUtils.isEmpty(dataList)) return;
            for (Object item : dataList) {
                Method setId = item.getClass().getMethod("setId", Long.class);
                setId.invoke(item, (Long) null);
                Method setProductId = item.getClass().getMethod("setProductId", Long.class);
                setProductId.invoke(item, productId);
            }
            Method insertList = dao.getClass().getMethod("insertList", List.class);
            insertList.invoke(dao, dataList);
        } catch (Exception e) {
            LOGGER.warn("创建产品出错:{}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}

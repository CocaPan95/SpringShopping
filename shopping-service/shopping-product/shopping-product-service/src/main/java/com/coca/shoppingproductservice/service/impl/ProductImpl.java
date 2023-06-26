package com.coca.shoppingproductservice.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.coca.shoppingcommon.exception.Asserts;
import com.coca.shoppingmodel.domain.product.*;
import com.coca.shoppingmodel.dto.PmsPortalProductDetail;
import com.coca.shoppingmodel.dto.PmsProductParam;
import com.coca.shoppingmodel.dto.PromotionProduct;
import com.coca.shoppingmodel.es.EsProduct;
import com.coca.shoppingproductservice.mapper.*;
import com.coca.shoppingproductservice.repository.EsProductRepository;
import com.coca.shoppingproductservice.service.ProductService;
import com.coca.shoppingsmsapi.SmsCouponHistoryService;
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
import java.util.stream.Collectors;

@Service
public class ProductImpl implements ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductImpl.class);
    @Autowired
    private PmsProductMapper productMapper;
    @Autowired
    private PmsMemberPriceDao memberPriceDao;
    @Autowired
    private PmsProductLadderDao productLadderDao;
    @Autowired
    private PmsProductFullReductionDao productFullReductionDao;
    @Autowired
    private PmsSkuStockDao skuStockDao;
    @Autowired
    private PmsProductAttributeValueDao productAttributeValueDao;
    @Autowired
    private PmsMemberPriceMapper memberPriceMapper;
    @Autowired
    private PmsProductLadderMapper productLadderMapper;
    @Autowired
    private PmsSkuStockMapper skuStockMapper;
    @Autowired
    private PmsProductFullReductionMapper productFullReductionMapper;
    @Autowired
    private PmsProductAttributeValueMapper productAttributeValueMapper;
    @Autowired
    private EsProductRepository productRepository;
    @Autowired
    private EsProductDao productDao;
    @Autowired
    private PortalProductDao portalProductDao;
    @Autowired
    private PmsBrandMapper brandMapper;
    @Autowired
    private PmsProductAttributeMapper productAttributeMapper;
    @DubboReference
    private SmsCouponHistoryService couponHistoryService;

    @Override
    public int create(PmsProductParam productParam) {
        PmsProduct product = productParam;
        product.setId(null);
        productMapper.insertSelective(product);
        //根据促销类型设置价格：会员价格、阶梯价格、满减价格
        Long productId = product.getId();

        //会员价格
        relateAndInsertList(memberPriceDao, productParam.getMemberPriceList(), productId);
        //阶梯价格
        relateAndInsertList(productLadderDao, productParam.getProductLadderList(), productId);
        //满减价格
        relateAndInsertList(productFullReductionDao, productParam.getProductFullReductionList(), productId);
        //处理sku的编码
        handleSkuStockCode(productParam.getSkuStockList(), productId);
        //添加sku库存信息
        relateAndInsertList(skuStockDao, productParam.getSkuStockList(), productId);
        //添加商品参数,添加自定义商品规格
        relateAndInsertList(productAttributeValueDao, productParam.getProductAttributeValueList(), productId);
        List<EsProduct> esProductList = productDao.getAllEsProductList(productId);
        productRepository.saveAll(esProductList);
        return 1;
    }

    @Override
    public int update(Long id, PmsProductParam productParam) {
        int count;
        //更新商品信息
        PmsProduct product = productParam;
        product.setId(id);
        productMapper.updateByPrimaryKeySelective(product);
        //会员价格
        PmsMemberPriceExample pmsMemberPriceExample = new PmsMemberPriceExample();
        pmsMemberPriceExample.createCriteria().andProductIdEqualTo(id);
        memberPriceMapper.deleteByExample(pmsMemberPriceExample);
        relateAndInsertList(memberPriceDao, productParam.getMemberPriceList(), id);
        //阶梯价格
        PmsProductLadderExample ladderExample = new PmsProductLadderExample();
        ladderExample.createCriteria().andProductIdEqualTo(id);
        productLadderMapper.deleteByExample(ladderExample);
        relateAndInsertList(productLadderDao, productParam.getProductLadderList(), id);
        //满减价格
        PmsProductFullReductionExample fullReductionExample = new PmsProductFullReductionExample();
        fullReductionExample.createCriteria().andProductIdEqualTo(id);
        productFullReductionMapper.deleteByExample(fullReductionExample);
        relateAndInsertList(productFullReductionDao, productParam.getProductFullReductionList(), id);
        //修改sku库存信息
        handleUpdateSkuStockList(id, productParam);
        //修改商品参数,添加自定义商品规格
        PmsProductAttributeValueExample productAttributeValueExample = new PmsProductAttributeValueExample();
        productAttributeValueExample.createCriteria().andProductIdEqualTo(id);
        productAttributeValueMapper.deleteByExample(productAttributeValueExample);
        relateAndInsertList(productAttributeValueDao, productParam.getProductAttributeValueList(), id);
        List<EsProduct> esProductList = productDao.getAllEsProductList(id);
        EsProduct esProduct = productRepository.findById(id).get();
        productRepository.delete(esProduct);
        productRepository.saveAll(esProductList);
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
        PmsProduct record = new PmsProduct();
        record.setPublishStatus(publishStatus);
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andIdIn(ids);

        return productMapper.updateByExampleSelective(record, example);

    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        PmsProduct record = new PmsProduct();
        record.setRecommandStatus(recommendStatus);
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andIdIn(ids);
        return productMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateNewStatus(List<Long> ids, Integer newStatus) {
        PmsProduct record = new PmsProduct();
        record.setNewStatus(newStatus);
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andIdIn(ids);
        return productMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
        PmsProduct record = new PmsProduct();
        record.setDeleteStatus(deleteStatus);
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andIdIn(ids);
        return productMapper.updateByExampleSelective(record, example);
    }

    /**
     * 查询所有商品的优惠相关信息
     */
    @Override
    public List<PromotionProduct> getPromotionProductList(List<Long> productIdList) {
        return portalProductDao.getPromotionProductList(productIdList);
    }


    @Override
    public PmsPortalProductDetail getProductDetail(Long productId) {
        PmsPortalProductDetail result = new PmsPortalProductDetail();
        //查询产品信息
        PmsProduct products = productMapper.selectByPrimaryKey(productId);
        if (products == null) {
            Asserts.fail("该产品不存在！");
        }
        result.setProduct(products);
        //查询品牌信息
        PmsBrand brands = brandMapper.selectByPrimaryKey(products.getBrandId());
        result.setBrand(brands);
        //查询属性信息
        PmsProductAttributeExample productAttributeExample = new PmsProductAttributeExample();
        productAttributeExample.createCriteria().andProductAttributeCategoryIdEqualTo(products.getProductAttributeCategoryId());
        List<PmsProductAttribute> productAttributes = productAttributeMapper.selectByExample(productAttributeExample);
        result.setProductAttributeList(productAttributes);
        //查询属性值相关信息
        PmsProductAttributeValueExample productAttributeValueExample = new PmsProductAttributeValueExample();
        productAttributeValueExample.createCriteria().andProductIdEqualTo(products.getId());
        List<PmsProductAttributeValue> pmsProductAttributeValues = productAttributeValueMapper.selectByExample(productAttributeValueExample);
        result.setProductAttributeValueList(pmsProductAttributeValues);
        //获取产品库存信息
        PmsSkuStockExample skuStockExample = new PmsSkuStockExample();
        skuStockExample.createCriteria().andProductIdEqualTo(products.getId());
        List<PmsSkuStock> skuStockList = skuStockMapper.selectByExample(skuStockExample);
        result.setSkuStockList(skuStockList);
        //获取阶梯价格
        PmsProductLadderExample pmsProductLadderExample = new PmsProductLadderExample();
        pmsProductLadderExample.createCriteria().andProductIdEqualTo(products.getId());
        List<PmsProductLadder> productLadderList = productLadderMapper.selectByExample(pmsProductLadderExample);
        if (CollectionUtil.isNotEmpty(productLadderList)) {
            result.setProductLadderList(productLadderList);
        }
        //商品满减价格设置
        PmsProductFullReductionExample pmsProductFullReductionExample = new PmsProductFullReductionExample();
        pmsProductFullReductionExample.createCriteria().andProductIdEqualTo(products.getId());
        List<PmsProductFullReduction> pmsProductFullReductionList = productFullReductionMapper.selectByExample(pmsProductFullReductionExample);
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
            PmsSkuStockExample skuStockExample = new PmsSkuStockExample();
            skuStockExample.createCriteria().andProductIdEqualTo(id);
            skuStockMapper.deleteByExample(skuStockExample);
            return;
        }
        //获取初始sku信息
        PmsSkuStockExample skuStockExample = new PmsSkuStockExample();
        skuStockExample.createCriteria().andProductIdEqualTo(id);
        List<PmsSkuStock> oriStuList = skuStockMapper.selectByExample(skuStockExample);
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
            relateAndInsertList(skuStockDao, insertSkuList, id);
        }
        //删除sku
        if (CollUtil.isNotEmpty(removeSkuList)) {
            List<Long> removeSkuIds = removeSkuList.stream().map(PmsSkuStock::getId).collect(Collectors.toList());
            PmsSkuStockExample removeExample = new PmsSkuStockExample();
            removeExample.createCriteria().andIdIn(removeSkuIds);
            skuStockMapper.deleteByExample(removeExample);
        }
        //修改sku
        if (CollUtil.isNotEmpty(updateSkuList)) {
            for (PmsSkuStock pmsSkuStock : updateSkuList) {
                skuStockMapper.updateByPrimaryKeySelective(pmsSkuStock);
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

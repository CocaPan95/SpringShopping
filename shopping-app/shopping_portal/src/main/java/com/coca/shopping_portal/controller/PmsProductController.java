package com.coca.shopping_portal.controller;

import com.coca.shoppingmodel.api.CommonPage;
import com.coca.shoppingmodel.api.CommonResult;
import com.coca.shoppingmodel.dto.PmsPortalProductDetail;
import com.coca.shoppingmodel.es.EsProduct;
import com.coca.shoppingproductapi.IPmsEsProductRpcService;
import com.coca.shoppingproductapi.IPmsProductRpcService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(tags = "产品信息接口",description = "产品信息分类")
@Controller
@RequestMapping("/product")
public class PmsProductController {
    @DubboReference
    private IPmsEsProductRpcService productService;
    @DubboReference
    private IPmsProductRpcService pmsProductService;

    @ApiOperation("商品信息搜索——简单")
    @RequestMapping(value = "/search/simple", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<EsProduct>> search(@RequestParam(required = false) String keyword,
                                                      @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                      @RequestParam(required = false, defaultValue = "5") Integer pageSize){
        CommonPage<EsProduct> result=productService.search(keyword,pageNum,pageSize);
        return CommonResult.success(result);
    }

    @ApiOperation("商品信息搜索——复杂")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<EsProduct>> search(@RequestParam(required = false) String keyword,
                                                      @RequestParam(required = false) Long brandId,
                                                      @RequestParam(required = false) Long productCategoryId,
                                                      @RequestParam(required = false , defaultValue = "0") Integer pageNum,
                                                      @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                                      @RequestParam(required = false, defaultValue = "0") Integer sort) {
        CommonPage<EsProduct> result=productService.search(keyword, brandId, productCategoryId, pageNum, pageSize, sort);
        return CommonResult.success(result);
    }



    @ApiOperation("获取前台商品详情")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsPortalProductDetail> detail(@PathVariable Long id) {
        PmsPortalProductDetail productDetail = pmsProductService.getProductDetail(id);
        return CommonResult.success(productDetail);
    }
}

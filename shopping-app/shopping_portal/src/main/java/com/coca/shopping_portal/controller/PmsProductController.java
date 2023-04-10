package com.coca.shopping_portal.controller;

import com.coca.shoppingmodel.es.EsProduct;
import com.coca.shoppingproductapi.EsProductService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/product")
public class PmsProductController {
    @DubboReference
    private EsProductService productService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize){
        return productService.search(keyword,pageNum,pageSize);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public Page<EsProduct> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort) {
        return productService.search(keyword, brandId, productCategoryId, pageNum, pageSize, sort);
    }
}

package com.coca.shoppingadmin.controller;

import com.coca.shoppingmodel.dto.PmsProductParam;
import com.coca.shoppingmodel.es.EsProduct;
import com.coca.shoppingproductapi.EsProductService;
import com.coca.shoppingproductapi.PmsProductService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @DubboReference
    private PmsProductService productService;

    //新增
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public int create(PmsProductParam productParam) {
        return productService.create(productParam);
    }

    //修改
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public int update(Long id, PmsProductParam productParam) {
        return productService.update(id, productParam);
    }

    //删除
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public int updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
        return productService.updateDeleteStatus(ids, deleteStatus);
    }
}

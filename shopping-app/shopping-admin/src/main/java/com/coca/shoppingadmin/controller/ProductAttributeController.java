package com.coca.shoppingadmin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coca.shoppingmodel.dto.PageParam;
import com.coca.shoppingmodel.entity.pms.PmsProductAttribute;
import com.coca.shoppingmodel.entity.pms.PmsProductAttributeCategory;
import com.coca.shoppingproductapi.IPmsProductAttributeRpcService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/productattribute")
public class ProductAttributeController {
    @DubboReference
    private IPmsProductAttributeRpcService pmsProductAttributeRpcService;

    @RequestMapping(value = "/getattributecategorylist", method = RequestMethod.GET)
    @ResponseBody
    public Page<PmsProductAttributeCategory> GetProductAttributeCategoryAllList(PageParam param) {
        return pmsProductAttributeRpcService.GetProductAttributeCategoryAllList(param);
    }

    @RequestMapping(value = "/saveattributecategory", method = RequestMethod.POST)
    @ResponseBody
    public boolean SaveProductAttributeCategory(PmsProductAttributeCategory param) {
        return pmsProductAttributeRpcService.SaveProductAttributeCategory(param);
    }

    @RequestMapping(value = "/removeattributecategory", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean RemoveProductAttributeCategory(Long categoryId) {
        return pmsProductAttributeRpcService.RemoveProductAttributeCategory(categoryId);
    }

    @RequestMapping(value = "/getattributelist", method = RequestMethod.GET)
    @ResponseBody
    public List<PmsProductAttribute> GetProductAttributeList(Long productAttributeCategoryId) {
        return pmsProductAttributeRpcService.GetProductAttributeList(productAttributeCategoryId);
    }

    @RequestMapping(value = "/saveattribute", method = RequestMethod.POST)
    @ResponseBody
    public boolean PmsProductAttributeSave(PmsProductAttribute param) {
        return pmsProductAttributeRpcService.PmsProductAttributeSave(param);
    }

    @RequestMapping(value = "/removeattribute", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean PmsProductAttributeRemove(Long categoryId) {
        return pmsProductAttributeRpcService.PmsProductAttributeRemove(categoryId);
    }

}

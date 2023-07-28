package com.coca.shoppingadmin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coca.shoppingmodel.dto.PageParam;
import com.coca.shoppingmodel.entity.pms.PmsProductAttribute;
import com.coca.shoppingmodel.entity.pms.PmsProductAttributeCategory;
import com.coca.shoppingproductapi.IPmsProductAttributeRpcService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/productattribute")
@Api(tags = "产品属性接口",description = "操作产品属性和属性分类")
public class ProductAttributeController {
    @DubboReference
    private IPmsProductAttributeRpcService pmsProductAttributeRpcService;

    @ApiOperation("获取属性分类列表")
    @RequestMapping(value = "/getattributecategorylist", method = RequestMethod.GET)
    @ResponseBody
    public Page<PmsProductAttributeCategory> GetProductAttributeCategoryAllList(PageParam param) {
        return pmsProductAttributeRpcService.GetProductAttributeCategoryAllList(param);
    }

    @ApiOperation("保存属性分类")
    @RequestMapping(value = "/saveattributecategory", method = RequestMethod.POST)
    @ResponseBody
    public boolean SaveProductAttributeCategory(PmsProductAttributeCategory param) {
        return pmsProductAttributeRpcService.SaveProductAttributeCategory(param);
    }

    @ApiOperation("删除属性分类")
    @RequestMapping(value = "/removeattributecategory", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean RemoveProductAttributeCategory(Long categoryId) {
        return pmsProductAttributeRpcService.RemoveProductAttributeCategory(categoryId);
    }

    @ApiOperation("获取属性分类下属性列表")
    @RequestMapping(value = "/getattributelist", method = RequestMethod.GET)
    @ResponseBody
    public List<PmsProductAttribute> GetProductAttributeList(Long productAttributeCategoryId) {
        return pmsProductAttributeRpcService.GetProductAttributeList(productAttributeCategoryId);
    }

    @ApiOperation("保存产品属性")
    @RequestMapping(value = "/saveattribute", method = RequestMethod.POST)
    @ResponseBody
    public boolean PmsProductAttributeSave(PmsProductAttribute param) {
        return pmsProductAttributeRpcService.PmsProductAttributeSave(param);
    }

    @ApiOperation("删除产品属性")
    @RequestMapping(value = "/removeattribute", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean PmsProductAttributeRemove(Long categoryId) {
        return pmsProductAttributeRpcService.PmsProductAttributeRemove(categoryId);
    }

}

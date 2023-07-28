package com.coca.shoppingadmin.controller;

import com.coca.shoppingmodel.api.CommonResult;
import com.coca.shoppinguserapi.IUmsResourceRpcService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Api(tags = "获取后台资源",description = "操作后台资源分类")
@Controller
@RequestMapping("/resource")
public class UmsResourceController {
    @DubboReference
    private IUmsResourceRpcService umsResourceService;

    @ApiOperation("初始化资源信息")
    @RequestMapping(value = "/initResourceRolesMap", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult initResourceRolesMap() {
        Map<String, List<String>> resourceRolesMap = umsResourceService.initResourceRolesMap();
        return CommonResult.success(resourceRolesMap);
    }
}

package com.coca.shoppingadmin.component;

import com.coca.shoppinguserapi.IUmsResourceRpcService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 资源与角色访问对应关系操作组件
 * Created by macro on 2020/7/17.
 */
//@Component
public class ResourceRoleRulesHolder {
    //@DubboReference
    private IUmsResourceRpcService resourceService;

    //@PostConstruct
    public void initResourceRolesMap(){
        resourceService.initResourceRolesMap();
    }
}

package com.coca.shoppinguserservice.rpc;

import com.coca.shoppinguserapi.UmsResourceService;
import com.coca.shoppinguserservice.service.ResourceService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@DubboService
public class UmsResourceRpc implements UmsResourceService {
    @Autowired
    private ResourceService resourceService;

    public Map<String, List<String>> initResourceRolesMap() {
        return resourceService.initResourceRolesMap();
    }
}

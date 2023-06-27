package com.coca.shoppinguserservice.rpc;

import com.coca.shoppinguserapi.IUmsResourceRpcService;
import com.coca.shoppinguserservice.service.IUmsResourceService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@DubboService
public class UmsResourceRpc implements IUmsResourceRpcService {
    @Autowired
    private IUmsResourceService resourceService;

    public Map<String, List<String>> initResourceRolesMap() {
        return resourceService.initResourceRolesMap();
    }
}

package com.allencai.mycloud.tmp1.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "mycloud-gateway", path = "mycloud-tmp1-provider")
@RequestMapping(value = "/api/test")
public interface TestServiceApi {

    @RequestMapping("/getSomething")
    String getSomething(@RequestParam("something") String something);
}

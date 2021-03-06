package com.allencai.mycloud.tmp3.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(name = "mycloud-gateway", path = "mycloud-tmp3-provider")
@FeignClient(name = "mycloud-tmp3-provider")
@RequestMapping(value = "/business")
public interface Tmp3BusinessApi {

    @RequestMapping(value = "/step3")
    Long step3(@RequestParam("code") String code);
}

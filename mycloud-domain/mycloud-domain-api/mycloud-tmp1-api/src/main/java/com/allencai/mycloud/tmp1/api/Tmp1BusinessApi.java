package com.allencai.mycloud.tmp1.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(name = "mycloud-gateway", path = "mycloud-tmp1-provider")
@FeignClient(name = "mycloud-tmp1-provider")
@RequestMapping(value = "/business")
public interface Tmp1BusinessApi {

    @RequestMapping("/start")
    Long start(@RequestParam("code") String code);
}

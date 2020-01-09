package com.allencai.mycloud.tmp2.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "mycloud-gateway", path = "mycloud-tmp2-provider")
@RequestMapping(value = "/business")
public interface Tmp2BusinessApi {

    @RequestMapping(value = "/step2")
    Long step2(@RequestParam("code") String code);
}

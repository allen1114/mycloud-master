package com.allencai.mycloud.tmp1.provider.rest;

import com.allencai.mycloud.tmp1.api.Tmp1BusinessApi;
import com.allencai.mycloud.tmp1.provider.service.BusinessService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class Tmp1BusinessController implements Tmp1BusinessApi {

    @Resource
    private BusinessService businessService;

    @Override
    public Long start(String code) {
        return businessService.saveStep1AndCallStep2(code);
    }
}

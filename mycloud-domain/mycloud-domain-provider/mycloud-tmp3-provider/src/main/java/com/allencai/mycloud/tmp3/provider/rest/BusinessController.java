package com.allencai.mycloud.tmp3.provider.rest;

import com.allencai.mycloud.tmp3.api.Tmp3BusinessApi;
import com.allencai.mycloud.tmp3.provider.service.BusinessService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BusinessController implements Tmp3BusinessApi {

    @Resource
    BusinessService businessService;

    @Override
    public Long step3(String code) {
        return businessService.saveStep3(code);
    }
}

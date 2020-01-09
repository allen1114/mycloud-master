package com.allencai.mycloud.tmp2.provider.rest;

import com.allencai.mycloud.tmp2.api.Tmp2BusinessApi;
import com.allencai.mycloud.tmp2.provider.service.BusinessService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BusinessController implements Tmp2BusinessApi {

    @Resource
    private BusinessService businessService;

    @Override
    public Long step2(String code) {
        return businessService.saveStep2AndCallStep3(code);
    }
}

package com.allencai.mycloud.tmp2.provider.service.impl;

import com.allencai.mycloud.tmp2.provider.entity.Step2;
import com.allencai.mycloud.tmp2.provider.mapper.Step2Mapper;
import com.allencai.mycloud.tmp2.provider.service.BusinessService;
import com.allencai.mycloud.tmp3.api.Tmp3BusinessApi;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Resource
    private Step2Mapper step2Mapper;

    @Resource
    private Tmp3BusinessApi tmp3BusinessApi;

    @Override
    public long saveStep2AndCallStep3(String code) {
        Step2 step2 = new Step2();
        step2.setCode(code);
        step2Mapper.insert(step2);
        return tmp3BusinessApi.step3(code);
    }
}

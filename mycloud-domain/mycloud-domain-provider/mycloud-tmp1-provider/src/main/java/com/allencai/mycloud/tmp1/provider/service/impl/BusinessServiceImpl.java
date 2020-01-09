package com.allencai.mycloud.tmp1.provider.service.impl;

import com.allencai.mycloud.tmp1.provider.entity.Step1;
import com.allencai.mycloud.tmp1.provider.mapper.Step1Mapper;
import com.allencai.mycloud.tmp1.provider.service.BusinessService;
import com.allencai.mycloud.tmp2.api.Tmp2BusinessApi;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BusinessServiceImpl implements BusinessService {


    @Resource
    private Step1Mapper step1Mapper;

    @Resource
    private Tmp2BusinessApi tmp2BusinessApi;

    @GlobalTransactional
    @Override
    public Long saveStep1AndCallStep2(String code) {
        Step1 step1 = new Step1();
        step1.setCode(code);
        step1Mapper.insert(step1);
        return tmp2BusinessApi.step2(code);
    }
}

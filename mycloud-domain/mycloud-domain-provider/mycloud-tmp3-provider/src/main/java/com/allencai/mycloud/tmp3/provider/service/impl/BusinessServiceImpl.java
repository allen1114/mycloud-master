package com.allencai.mycloud.tmp3.provider.service.impl;

import com.allencai.mycloud.tmp3.provider.entity.Step3;
import com.allencai.mycloud.tmp3.provider.mapper.Step3Mapper;
import com.allencai.mycloud.tmp3.provider.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private Step3Mapper step3Mapper;

    @Override
    public long saveStep3(String code) {
        Step3 step3 = new Step3();
        step3.setCode(code);
        return step3Mapper.insert(step3);
    }
}

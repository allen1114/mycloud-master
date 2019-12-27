package com.allencai.mycloud.domain.tmp1.provider.web.rpc;

import com.allencai.mycloud.domain.tmp1.api.TestServiceApi;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestServiceProvider implements TestServiceApi {

    @Override
    public String getSomething(String something) {
        System.out.println("do " + something);
        return something;
    }
}

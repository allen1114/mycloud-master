package com.allencai.mycloud.domain.tmp2.provider.web.controller;

import com.allencai.mycloud.domain.tmp1.api.TestServiceApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final TestServiceApi testServiceApi;

    public TestController(TestServiceApi testServiceApi) {
        this.testServiceApi = testServiceApi;
    }

    @RequestMapping("hello")
    public String hello() {
        return testServiceApi.getSomething("hello");
    }
}

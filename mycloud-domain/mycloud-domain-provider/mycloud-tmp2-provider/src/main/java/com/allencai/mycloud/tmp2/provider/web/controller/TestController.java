package com.allencai.mycloud.tmp2.provider.web.controller;

import com.allencai.mycloud.tmp1.api.TestServiceApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zipkin2.reporter.Sender;

@RestController
public class TestController {

    private Sender sender;

    private final TestServiceApi testServiceApi;

    public TestController(TestServiceApi testServiceApi, Sender sender) {
        this.testServiceApi = testServiceApi;
        this.sender = sender;
    }

    @RequestMapping("hello")
    public String hello() {
        System.out.println(sender.getClass().getName());
        return testServiceApi.getSomething("hello");
    }
}

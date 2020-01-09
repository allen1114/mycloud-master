package com.allencai.mycloud.seata.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("seata.registry.sofa")
@Data
public class SeataRegistrySofa {
    private String serverAddr;
    private String region;
    private String datacenter;
    private String group;
    private String application;
    private String cluster;
    private String addressWaitTime;
}

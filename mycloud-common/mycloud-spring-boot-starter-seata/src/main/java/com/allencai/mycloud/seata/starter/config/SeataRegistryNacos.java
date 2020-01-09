package com.allencai.mycloud.seata.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("seata.registry.nacos")
@Data
public class SeataRegistryNacos {

    private String serverAddr;
    private String namespace;
    private String cluster;
}

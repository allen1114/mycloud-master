package com.allencai.mycloud.seata.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("seata.registry.consul")
@Data
public class SeataRegistryConsul {

    private String serverAddr;
    private String cluster;

}

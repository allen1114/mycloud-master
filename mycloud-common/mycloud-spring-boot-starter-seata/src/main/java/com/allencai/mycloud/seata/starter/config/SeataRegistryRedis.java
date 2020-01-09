package com.allencai.mycloud.seata.starter.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("seata.registry.redis")
@Data
public class SeataRegistryRedis {

    private String serverAddr;
    private String cluster;
    private String db;
    private String password;

}

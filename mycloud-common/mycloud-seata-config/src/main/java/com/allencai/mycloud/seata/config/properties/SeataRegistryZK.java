package com.allencai.mycloud.seata.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("seata.registry.zk")
@Data
public class SeataRegistryZK {

    private String serverAddr;
    private String cluster;
    private String sessionTimeout;
    private String connectTimeout;

}

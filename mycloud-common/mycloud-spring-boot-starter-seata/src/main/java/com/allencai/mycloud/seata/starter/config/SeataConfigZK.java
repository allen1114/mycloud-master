package com.allencai.mycloud.seata.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("seata.config.zk")
public class SeataConfigZK {

    private String serverAddr;
    private String sessionTimeout;
    private String connectTimeout;


}

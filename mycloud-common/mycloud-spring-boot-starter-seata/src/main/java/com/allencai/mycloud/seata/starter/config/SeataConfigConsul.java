package com.allencai.mycloud.seata.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("seata.config.consul")
public class SeataConfigConsul {
    private String serverAddr;
}

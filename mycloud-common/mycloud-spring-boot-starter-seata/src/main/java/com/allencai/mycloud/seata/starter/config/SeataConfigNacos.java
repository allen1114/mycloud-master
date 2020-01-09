package com.allencai.mycloud.seata.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("seata.config.nacos")
public class SeataConfigNacos {

    private String serverAddr;
    private String namespace;

}

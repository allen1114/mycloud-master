package com.allencai.mycloud.seata.starter.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("seata.config.apollo")
public class SeataConfigApollo {
    private String appId;
    private String apolloMeta;
}

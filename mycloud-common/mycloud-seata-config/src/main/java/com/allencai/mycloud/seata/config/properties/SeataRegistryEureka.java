package com.allencai.mycloud.seata.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("seata.registry.eureka")
@Data
public class SeataRegistryEureka {

    private String application;
    private String serviceUrl;
    private String weight;
}

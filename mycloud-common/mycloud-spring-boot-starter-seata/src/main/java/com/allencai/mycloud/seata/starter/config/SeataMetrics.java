package com.allencai.mycloud.seata.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("seata.metrics")
@Data
public class SeataMetrics {

    private String enabled;
    private String registryType;
    private String exporterList;
    private String exporterPrometheusPort;
}

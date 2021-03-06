package com.allencai.mycloud.seata.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("seata.registry.etcd3")
@Data
public class SeataRegistryEtcd3 {

    private String serverAddr;
    private String cluster;
}

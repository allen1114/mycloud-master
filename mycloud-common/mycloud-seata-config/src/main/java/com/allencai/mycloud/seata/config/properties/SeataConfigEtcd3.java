package com.allencai.mycloud.seata.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("seata.config.etcd3")
public class SeataConfigEtcd3 {

    private String serverAddr;

}

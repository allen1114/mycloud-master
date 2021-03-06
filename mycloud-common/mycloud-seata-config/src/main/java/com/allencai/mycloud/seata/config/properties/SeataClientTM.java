package com.allencai.mycloud.seata.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("seata.client.tm")
public class SeataClientTM {

    private String commitRetryCount;
    private String rollbackRetryCount;

}

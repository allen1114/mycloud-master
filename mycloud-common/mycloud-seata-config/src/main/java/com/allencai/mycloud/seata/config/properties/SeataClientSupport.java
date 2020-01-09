package com.allencai.mycloud.seata.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("seata.client.support")
public class SeataClientSupport {

    private String springDatasourceAutoproxy;

}

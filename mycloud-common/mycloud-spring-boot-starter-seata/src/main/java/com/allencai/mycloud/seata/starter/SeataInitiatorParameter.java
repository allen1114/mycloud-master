package com.allencai.mycloud.seata.starter;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.allencai.mycloud.seata.starter.SeataInitiatorParameter.PREFIX;

@Data
@ConfigurationProperties(PREFIX)
public class SeataInitiatorParameter {

    public static final String PREFIX = "seata.initiator";

    private boolean enabled = false;
    private String storeMode = "file";
    private String host;
    private int port = 8091;
    private int serverNode = 1;
    private String seataEnv;
    private int minServerPoolSize = 100;
    private int maxServerPoolSize = 500;
    private int maxTaskQueueSize = 20000;
    private int keepAliveTime = 500;
}

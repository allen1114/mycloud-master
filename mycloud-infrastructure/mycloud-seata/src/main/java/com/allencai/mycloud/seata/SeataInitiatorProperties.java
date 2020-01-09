package com.allencai.mycloud.seata;

import lombok.Data;

@Data
public class SeataInitiatorProperties {

    private boolean enabled = false;
    private String host;
    private int port = 8091;
    private int node = 1;
    private String env;
    private int minServerPoolSize = 100;
    private int maxServerPoolSize = 500;
    private int maxTaskQueueSize = 20000;
    private int keepAliveTime = 500;
}

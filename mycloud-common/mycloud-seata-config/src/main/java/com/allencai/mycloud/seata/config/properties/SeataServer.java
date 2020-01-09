package com.allencai.mycloud.seata.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("seata.server")
@Data
public class SeataServer {

    private boolean enabled = false;
    private String host;
    private int port = 8091;
    private int node = 1;
    private String env;
    private int minServerPoolSize = 100;
    private int maxServerPoolSize = 500;
    private int maxTaskQueueSize = 20000;
    private int keepAliveTime = 500;

    private String undoLogSaveDays;
    private String undoLogDeletePeriod;
    private String maxCommitRetryTimeout;
    private String maxRollbackRetryTimeout;
    private String recoveryCommittingRetryPeriod;
    private String recoveryAsynCommittingRetryPeriod;
    private String recoveryRollbackingRetryPeriod;
    private String recoveryTimeoutRetryPeriod;

}

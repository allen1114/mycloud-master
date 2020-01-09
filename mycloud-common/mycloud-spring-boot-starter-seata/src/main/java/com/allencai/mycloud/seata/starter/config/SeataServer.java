package com.allencai.mycloud.seata.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("seata.server")
@Data
public class SeataServer {

    private String undoLogSaveDays;
    private String undoLogDeletePeriod;
    private String maxCommitRetryTimeout;
    private String maxRollbackRetryTimeout;
    private String recoveryCommittingRetryPeriod;
    private String recoveryAsynCommittingRetryPeriod;
    private String recoveryRollbackingRetryPeriod;
    private String recoveryTimeoutRetryPeriod;

}

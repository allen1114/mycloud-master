package com.allencai.mycloud.seata.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("seata.client.rm")
public class SeataClientRM {

    private String asyncCommitBufferLimit;
    private String lockRetryInternal;
    private String lockRetryTimes;
    private String lockRetryPolicyBranchRollbackOnConflict;
    private String reportRetryCount;
    private String tableMetaCheckEnable;
    private String reportSuccessEnable;

}

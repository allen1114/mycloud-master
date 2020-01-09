package com.allencai.mycloud.seata.starter.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("seata.transport.thread-factory")
@Data
public class SeataTransportThreadFactory {

    private String bossThreadPrefix;
    private String workerThreadPrefix;
    private String serverExecutorThreadPrefix;
    private String shareBossWorker;
    private String clientSelectorThreadPrefix;
    private String clientSelectorThreadSize;
    private String clientWorkerThreadPrefix;
    private String bossThreadSize;
    private String workerThreadSize;


}

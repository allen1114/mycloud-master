package com.allencai.mycloud.seata.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Data
@ConfigurationProperties("seata.client")
@EnableConfigurationProperties({SeataClientRM.class, SeataClientTM.class, SeataClientUndo.class, SeataClientSupport.class})
public class SeataClient {

    private boolean enabled = false;
    private String applicationId;
    private String txServiceGroup;
    private SeataClientTM tm;
    private SeataClientRM rm;
    private SeataClientUndo undo;
    private SeataClientSupport support;
}

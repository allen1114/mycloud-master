package com.allencai.mycloud.seata.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@ConfigurationProperties("seata.store")
@EnableConfigurationProperties({SeataStoreDB.class, SeataStoreFile.class})
@Data
public class SeataStore {

    private String mode;
    private SeataStoreDB db = new SeataStoreDB();
    private SeataStoreFile file = new SeataStoreFile();
}

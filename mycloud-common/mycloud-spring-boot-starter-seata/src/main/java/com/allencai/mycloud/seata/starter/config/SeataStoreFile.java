package com.allencai.mycloud.seata.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("seata.store.file")
@Data
public class SeataStoreFile {
    private String dir;
    private String maxBranchSessionSize;
    private String maxGlobalSessionSize;
    private String fileWriteBufferCacheSize;
    private String sessionReloadReadSize;
    private String flushDiskMode;
}

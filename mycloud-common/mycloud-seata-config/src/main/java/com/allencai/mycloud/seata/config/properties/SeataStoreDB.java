package com.allencai.mycloud.seata.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("seata.store.db")
@Data
public class SeataStoreDB {

    private String datasource;
    private String dbType;
    private String driverClassName;
    private String url;
    private String user;
    private String password;
    private String minConn;
    private String maxConn;
    private String lockTable;
    private String queryLimit;
    private String globalTable;
    private String branchTable;
}

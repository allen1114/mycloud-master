package com.allencai.mycloud.seata.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.Map;

@ConfigurationProperties("seata")
@EnableConfigurationProperties(
        {SeataRegistry.class, SeataConfig.class, SeataTransport.class, SeataServer.class, SeataStore.class, SeataMetrics.class,
                SeataClient.class})
@Data
public class Seata {

    private SeataServer server;
    private SeataClient client;
    private SeataRegistry registry;
    private SeataConfig config;
    private SeataTransport transport;
    private SeataStore store;
    private SeataMetrics metrics;

    /**
     * #transaction service group mapping
     * vgroup_mapping.my_test_tx_group = "default"
     * #only support when registry.type=file, please don't set multiple addresses
     * default.grouplist = "127.0.0.1:8091"
     * #degrade, current not support
     * enableDegrade = false
     * #disable seata
     * disableGlobalTransaction = false
     * <p>
     * 因为源码莫名其妙用"service."+clusterName+".grouplist"这种格式，只能用个map解决
     */
    private Map<String, String> service;

}

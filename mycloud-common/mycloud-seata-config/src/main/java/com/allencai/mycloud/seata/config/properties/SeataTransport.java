package com.allencai.mycloud.seata.config.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@ConfigurationProperties("seata.transport")
@Data
@EnableConfigurationProperties(SeataTransportThreadFactory.class)
public class SeataTransport {

    private String type;
    private String server;
    private String serialization;
    private String compressor;
    private String heartbeat;
    private String shutdownWait;
    private SeataTransportThreadFactory threadFactory;

}

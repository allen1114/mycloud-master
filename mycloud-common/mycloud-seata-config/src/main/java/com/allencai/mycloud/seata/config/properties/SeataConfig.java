package com.allencai.mycloud.seata.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.HashMap;

@Data
@ConfigurationProperties("seata.config")
@EnableConfigurationProperties(
        {SeataConfigNacos.class, SeataConfigConsul.class, SeataConfigApollo.class, SeataConfigZK.class, SeataConfigEtcd3.class,
                SeataConfigFile.class})
public class SeataConfig extends HashMap<String, HashMap<String, String>> {

    private String type;
    private SeataConfigNacos nacos;
    private SeataConfigConsul consul;
    private SeataConfigApollo apollo;
    private SeataConfigZK zk;
    private SeataConfigEtcd3 etcd3;
    private SeataConfigFile file;

}

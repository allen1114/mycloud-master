package com.allencai.mycloud.seata.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@ConfigurationProperties("seata.registry")
@EnableConfigurationProperties(
        {SeataRegistryConsul.class, SeataRegistryEtcd3.class, SeataRegistryEureka.class, SeataRegistryNacos.class, SeataRegistryRedis.class,
                SeataRegistrySofa.class, SeataRegistryZK.class})
@Data
public class SeataRegistry {

    private String type;
    private SeataRegistryConsul consul;
    private SeataRegistryEtcd3 etcd3;
    private SeataRegistryEureka eureka;
    private SeataRegistryNacos nacos;
    private SeataRegistryRedis redis;
    private SeataRegistrySofa sofa;
    private SeataRegistryZK zk;

}

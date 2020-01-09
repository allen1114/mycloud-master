package com.allencai.mycloud.seata.config;

import com.allencai.mycloud.seata.config.properties.Seata;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(Seata.class)
public class SeataConfigAutoConfiguration {

    public SeataConfigAutoConfiguration(Seata seata) {
        SeataConfigurationProvider.setConfig(seata);
    }

}

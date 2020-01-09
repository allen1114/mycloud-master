package com.allencai.mycloud.seata.starter;

import com.allencai.mycloud.seata.starter.config.Seata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(Seata.class)
public class SeataInitiatorAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean(SeataServerInitiator.class)
    @ConditionalOnProperty(prefix = "seata.server", name = "enabled", havingValue = "true")
    public SeataServerInitiator seataInitiator(Seata seata) {

        return new SeataServerInitiator(seata.getServer());
    }
}

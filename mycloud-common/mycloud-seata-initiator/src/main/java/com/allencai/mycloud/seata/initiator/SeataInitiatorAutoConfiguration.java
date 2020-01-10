package com.allencai.mycloud.seata.initiator;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SeataInitiatorProperties.class)
public class SeataInitiatorAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(SeataInitiator.class)
    @ConditionalOnProperty(prefix = "seata.initiator", name = "enabled", havingValue = "true")
    public SeataInitiator seataInitiator(SeataInitiatorProperties properties) {

        return new SeataInitiator(properties);
    }
}

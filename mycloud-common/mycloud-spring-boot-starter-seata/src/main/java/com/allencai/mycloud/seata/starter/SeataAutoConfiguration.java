package com.allencai.mycloud.seata.starter;

import com.allencai.mycloud.seata.starter.config.Seata;
import io.seata.spring.annotation.GlobalTransactionScanner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(Seata.class)
public class SeataAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(SeataServerInitiator.class)
    @ConditionalOnProperty(prefix = "seata.server", name = "enabled", havingValue = "true")
    public SeataServerInitiator seataInitiator(Seata seata) {
        SeataConfigurationProvider.setConfig(seata);
        return new SeataServerInitiator(seata.getServer());
    }

    @Bean
    @ConditionalOnMissingBean(GlobalTransactionScanner.class)
    @ConditionalOnProperty(prefix = "seata.client", name = "enabled", havingValue = "true")
    public GlobalTransactionScanner globalTransactionScanner(Seata seata) {
        SeataConfigurationProvider.setConfig(seata);
        return new GlobalTransactionScanner(seata.getClient().getApplicationId(), seata.getClient().getTxServiceGroup());
    }

}

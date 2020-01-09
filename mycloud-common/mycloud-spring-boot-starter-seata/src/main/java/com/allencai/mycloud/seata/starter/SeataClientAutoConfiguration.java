package com.allencai.mycloud.seata.starter;

import com.allencai.mycloud.seata.starter.config.Seata;
import io.seata.spring.annotation.GlobalTransactionScanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(Seata.class)
@Slf4j
public class SeataClientAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(GlobalTransactionScanner.class)
    public GlobalTransactionScanner globalTransactionScanner(Seata seata) {
        SeataConfigurationProvider.setConfig(seata);
        return new GlobalTransactionScanner(seata.getApplicationId(), seata.getTxServiceGroup());
    }

}

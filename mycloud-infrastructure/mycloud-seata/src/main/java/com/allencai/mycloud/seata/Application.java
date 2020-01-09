package com.allencai.mycloud.seata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(SeataInitiatorProperties.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @ConditionalOnMissingBean(SeataInitiator.class)
    @ConditionalOnProperty(prefix = "seata.server", name = "enabled", havingValue = "true")
    public SeataInitiator seataInitiator() {

        return new SeataInitiator(seata.getServer());
    }
}

package com.allencai.mycloud.seata.starter;

import com.allencai.mycloud.seata.starter.config.Seata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(SeataMarkerConfiguration.ServerMarker.class)
@EnableConfigurationProperties({Seata.class, SeataInitiatorParameter.class})
@Slf4j
public class SeataInitiatorAutoConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = SeataInitiatorParameter.PREFIX, name = "enabled", havingValue = "true")
    public SeataInitiator seataInitiator(Seata seata, SeataInitiatorParameter parameter) {
        SeataConfigurationProvider.setConfig(seata);
        return new SeataInitiator(parameter);
    }

}

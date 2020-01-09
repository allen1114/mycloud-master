package com.allencai.mycloud.tmp3.provider.config;

import io.seata.config.springcloud.EnableSeataSpringConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.allencai.mycloud.tmp3.provider.mapper")
@EnableSeataSpringConfig
public class DBConfig {

}

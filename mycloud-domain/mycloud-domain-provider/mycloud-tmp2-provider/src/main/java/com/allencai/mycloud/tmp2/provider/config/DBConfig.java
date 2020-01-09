package com.allencai.mycloud.tmp2.provider.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.allencai.mycloud.tmp2.provider.mapper")
public class DBConfig {
}

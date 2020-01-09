package com.allencai.mycloud.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import zipkin2.server.internal.EnableZipkinServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Configuration
    static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            super.configure(http);
        }
    }
}

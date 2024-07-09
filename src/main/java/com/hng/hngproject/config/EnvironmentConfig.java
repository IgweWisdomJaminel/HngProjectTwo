package com.hng.hngproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;

@Configuration
public class EnvironmentConfig {

    @Value("${RAILWAY_TCP_PROXY_DOMAIN}")
    private String domain;

    @Value("${RAILWAY_TCP_PROXY_PORT}")
    private String port;

    @Value("${POSTGRES_DB}")
    private String dbName;

    @Value("${POSTGRES_USER}")
    private String username;

    @Value("${POSTGRES_PASSWORD}")
    private String password;

    @PostConstruct
    public void printEnvVariables() {
        System.out.println("RAILWAY_TCP_PROXY_DOMAIN: " + domain);
        System.out.println("RAILWAY_TCP_PROXY_PORT: " + port);
        System.out.println("POSTGRES_DB: " + dbName);
        System.out.println("POSTGRES_USER: " + username);
        System.out.println("POSTGRES_PASSWORD: " + password);
    }
}

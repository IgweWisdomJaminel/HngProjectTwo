package com.hng.hngproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class EnvironmentConfig {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String datasourceUsername;

    @Value("${spring.datasource.password}")
    private String datasourcePassword;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String hibernateDialect;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Value("${spring.datasource.hikari.connection-timeout}")
    private int connectionTimeout;

    @Value("${spring.datasource.hikari.minimum-idle}")
    private int minimumIdle;

    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private int maximumPoolSize;

    @Value("${spring.datasource.hikari.idle-timeout}")
    private int idleTimeout;

    @Value("${spring.datasource.hikari.max-lifetime}")
    private int maxLifetime;

    @PostConstruct
    public void printEnvVariables() {
        System.out.println("DataSource URL: " + datasourceUrl);
        System.out.println("DataSource Username: " + datasourceUsername);
        System.out.println("DataSource Password: " + datasourcePassword);
        System.out.println("Hibernate Dialect: " + hibernateDialect);
        System.out.println("DDL Auto: " + ddlAuto);
        System.out.println("Connection Timeout: " + connectionTimeout);
        System.out.println("Minimum Idle: " + minimumIdle);
        System.out.println("Maximum Pool Size: " + maximumPoolSize);
        System.out.println("Idle Timeout: " + idleTimeout);
        System.out.println("Max Lifetime: " + maxLifetime);
    }

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(datasourceUrl);
        dataSource.setUsername(datasourceUsername);
        dataSource.setPassword(datasourcePassword);
        dataSource.setConnectionTimeout(connectionTimeout);
        dataSource.setMinimumIdle(minimumIdle);
        dataSource.setMaximumPoolSize(maximumPoolSize);
        dataSource.setIdleTimeout(idleTimeout);
        dataSource.setMaxLifetime(maxLifetime);
        return dataSource;
    }
}

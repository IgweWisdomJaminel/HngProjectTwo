package com.hng.hngproject;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HngProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(HngProjectApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            // Log the resolved JDBC URL for debugging
            String jdbcUrl = String.format("jdbc:postgresql://%s:%s/%s",
                    System.getenv("RAILWAY_TCP_PROXY_DOMAIN"),
                    System.getenv("RAILWAY_TCP_PROXY_PORT"),
                    System.getenv("POSTGRES_DB")
            );
            System.out.println("Resolved JDBC URL: " + jdbcUrl);

            // Log other environment variables
            System.out.println("RAILWAY_TCP_PROXY_DOMAIN: " + System.getenv("RAILWAY_TCP_PROXY_DOMAIN"));
            System.out.println("RAILWAY_TCP_PROXY_PORT: " + System.getenv("RAILWAY_TCP_PROXY_PORT"));
            System.out.println("POSTGRES_DB: " + System.getenv("POSTGRES_DB"));
            System.out.println("POSTGRES_USER: " + System.getenv("POSTGRES_USER"));
            System.out.println("POSTGRES_PASSWORD: " + System.getenv("POSTGRES_PASSWORD"));
        };
    }
}
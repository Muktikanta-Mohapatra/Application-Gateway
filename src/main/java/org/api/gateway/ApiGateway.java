package org.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

@ComponentScan(basePackages = {"gateway.security", "gateway.security.entity", "gateway.security.repository", "gateway.security.filter", "gateway.security.strategy", "gateway.security.validator", "gateway.security.validator.helper", "gateway.security.config", "gateway.security.service"})
@EnableJpaRepositories(basePackages = "gateway.security.repository")
@EntityScan(basePackages = "gateway.security.entity")

public class ApiGateway {
    public static void main(String[] args) {
        SpringApplication.run(ApiGateway.class, args);
        SpringApplication.run(ApiGateway.class, args);
    }
}

package com.uams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {
		"com.uams.configuration",
		"com.uams.controller",
		"com.uams.exception.handler"
})
@EntityScan(basePackages = "com.uams.model.entity")
@EnableJpaRepositories(basePackages = "com.uams.repository")
@SpringBootApplication
public class UserAuthenticationManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthenticationManagementServiceApplication.class, args);
	}
}


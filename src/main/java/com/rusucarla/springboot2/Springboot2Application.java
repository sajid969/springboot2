package com.rusucarla.springboot2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.rusucarla.repository")
@EntityScan("com.rusucarla.entity")
@SpringBootApplication(scanBasePackages = "com.rusucarla")
public class Springboot2Application {

	public static void main(String[] args) {
		SpringApplication.run(Springboot2Application.class, args);
	}

}

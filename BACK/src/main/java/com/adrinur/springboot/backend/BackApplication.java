package com.adrinur.springboot.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EnableJpaRepositories(basePackages = "com.adrinur.springboot.backend")
@EntityScan(basePackages = {"com.adrinur.springboot.backend"})
@ComponentScan(basePackages = "com.adrinur.springboot.backend")

@SpringBootApplication
public class BackApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(BackApplication.class, args);
	}

}

package com.example.cloneinstagramback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableJpaRepositories(basePackages = "com.example.cloneinstagramback.repository")
public class CloneInstagramBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloneInstagramBackApplication.class, args);
	}

}

package com.pdrsoft.pdrfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.pdrsoft.pdrfood.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class PdrfoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdrfoodApiApplication.class, args);
	}

}

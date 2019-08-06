package com.wilkom.mythymeleafrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // très important pour l'insertion automatique dans les champs CREATED_AT et UPDATED_AT
public class MythymeleafRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MythymeleafRestApiApplication.class, args);
	}

}

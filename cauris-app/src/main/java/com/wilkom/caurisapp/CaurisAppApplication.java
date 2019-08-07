package com.wilkom.caurisapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import com.wilkom.caurisapp.security.model.entity.MyUser;
import com.wilkom.caurisapp.security.model.entity.Role;
import com.wilkom.caurisapp.security.model.service.MyUserService;

@SpringBootApplication
@EnableJpaAuditing //pour l insertion automatique dans les champs createAt et updateAt
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class CaurisAppApplication {
	
	private static final Logger log = LoggerFactory.getLogger(CaurisAppApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(CaurisAppApplication.class, args);
	}
	
//	@Bean
//	public CommandLineRunner demo(MyUserService service) {
//		return (args) -> {
//			// save a couple of customers
//			service.registerUser("user", "pass",Arrays.asList(new Role("ROLE_USER")));
//			service.registerUser("admin", "admin",Arrays.asList(new Role("ROLE_ADMIN")));
//			service.registerUser("db", "db",Arrays.asList(new Role("ROLE_DB")));
//
//			// fetch all customers
//			log.info("users found with findAll().:");
//			log.info(""+service.findByEmail("user"));
//						log.info("");
//		};
//	}
//	@Bean
//	public CommandLineRunner demo(CustomerRepository repository) {
//		return (args) -> {
//			// save a couple of customers
//			repository.save(new Customer("Jack", "Bauer"));
//			repository.save(new Customer("Chloe", "O'Brian"));
//			repository.save(new Customer("Kim", "Bauer"));
//			repository.save(new Customer("David", "Palmer"));
//			repository.save(new Customer("Michelle", "Dessler"));
//
//			// fetch all customers
//			log.info("Customers found with findAll():");
//			log.info("-------------------------------");
//			for (Customer customer : repository.findAll()) {
//				log.info(customer.toString());
//			}
//			log.info("");
//
//			// fetch an individual customer by ID
//			repository.findById(1L)
//				.ifPresent(customer -> {
//					log.info("Customer found with findById(1L):");
//					log.info("--------------------------------");
//					log.info(customer.toString());
//					log.info("");
//				});
//
//			// fetch customers by last name
//			log.info("Customer found with findByLastName('Bauer'):");
//			log.info("--------------------------------------------");
//			repository.findByLastName("Bauer").forEach(bauer -> {
//				log.info(bauer.toString());
//			});
//			// for (Customer bauer : repository.findByLastName("Bauer")) {
//			// 	log.info(bauer.toString());
//			// }
//			log.info("");
//		};
//	}

}

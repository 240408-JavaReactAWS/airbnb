package com.revature.airbnb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.revature.airbnb") //Scan com.revature package for Spring Beans
@EntityScan("com.revature.airbnb.models") //Scan models package for Entities
@EnableJpaRepositories("com.revature.airbnb.daos")
public class AirbnbApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirbnbApplication.class, args);
	}

}

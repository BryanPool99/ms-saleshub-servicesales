package com.saleshub.ms.servicesales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class ServicesalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicesalesApplication.class, args);
	}

}

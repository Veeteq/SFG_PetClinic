package com.sfg.petclinic.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sfg.petclinic.web", "com.sfg.petclinic.data"})
@EntityScan(basePackages = {"com.sfg.petclinic.data.model"})
public class PetClinicApplication {

	public static void main(String[] args) {
	    System.out.println("Running: PetClinicApplication");
		SpringApplication.run(PetClinicApplication.class, args);
	}
}


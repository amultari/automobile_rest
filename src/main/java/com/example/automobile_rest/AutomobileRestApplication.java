package com.example.automobile_rest;

import java.text.SimpleDateFormat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.automobile_rest.model.Automobile;
import com.example.automobile_rest.service.AutomobileService;

@SpringBootApplication
public class AutomobileRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomobileRestApplication.class, args);
	}

	@Bean
	public CommandLineRunner initAutomobili(AutomobileService automobileService) {
		return (args) -> {

			automobileService.save(new Automobile("Fiat", "Tipo", "AB987UI",
					new SimpleDateFormat("dd/MM/yyyy").parse("28/12/2018"), true));
			
			System.out.println("Elenco automobili");
			automobileService.listAll().forEach(autoItem ->{
				System.out.println(autoItem);
			});
		};
	}

}

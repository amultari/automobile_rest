package com.example.automobile_rest;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.automobile_rest.model.Authority;
import com.example.automobile_rest.model.AuthorityName;
import com.example.automobile_rest.model.Automobile;
import com.example.automobile_rest.model.User;
import com.example.automobile_rest.security.repository.AuthorityRepository;
import com.example.automobile_rest.security.repository.UserRepository;
import com.example.automobile_rest.service.AutomobileService;

@SpringBootApplication
public class AutomobileRestApplication {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	AuthorityRepository authorityRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AutomobileRestApplication.class, args);
	}

	@Bean
	public CommandLineRunner initAutomobili(AutomobileService automobileService) {
		return (args) -> {

			// inizializzo il Db
			automobileService.save(new Automobile("Fiat", "Tipo", "AB987UI",
					new SimpleDateFormat("dd/MM/yyyy").parse("28/12/2018"), true));
			automobileService.save(new Automobile("Fiat", "500", "HG987UI",
					new SimpleDateFormat("dd/MM/yyyy").parse("28/09/2018"), true));
			automobileService.save(new Automobile("VW", "Golf", "YU547UI",
					new SimpleDateFormat("dd/MM/yyyy").parse("28/12/2018"), true));
			automobileService.save(new Automobile("Opel", "Tigra", "XX127UI",
					new SimpleDateFormat("dd/MM/yyyy").parse("28/12/1998"), false));

			automobileService.save(new Automobile("Fiat", "500", "RR123UI",
					new SimpleDateFormat("dd/MM/yyyy").parse("28/03/2015"), true));
			automobileService.save(new Automobile("Toyota", "Yaris", "ZZ987UI",
					new SimpleDateFormat("dd/MM/yyyy").parse("12/10/2018"), true));
			automobileService.save(new Automobile("Peugeot", "208", "JJ789UI",
					new SimpleDateFormat("dd/MM/yyyy").parse("08/12/2017"), true));
			automobileService.save(new Automobile("Opel", "Corsa", "LK987HB",
					new SimpleDateFormat("dd/MM/yyyy").parse("01/05/2018"), false));

			automobileService.save(new Automobile("Fiat", "Punto", "XZ987UI",
					new SimpleDateFormat("dd/MM/yyyy").parse("19/12/2016"), true));
			automobileService.save(new Automobile("Fiat", "500", "BN343MM",
					new SimpleDateFormat("dd/MM/yyyy").parse("01/04/2018"), true));
			automobileService.save(new Automobile("Audi", "A3", "KK547UI",
					new SimpleDateFormat("dd/MM/yyyy").parse("06/06/2019"), true));
			automobileService.save(new Automobile("Renault", "Clio", "BV878HJ",
					new SimpleDateFormat("dd/MM/yyyy").parse("02/04/2020"), false));

			// verifico inserimento
			System.out.println("Elenco automobili");
			automobileService.listAll().forEach(autoItem -> {
				System.out.println(autoItem);
			});

			// Ora la parte di sicurezza
			User user = userRepository.findByUsername("admin").orElse(null);

			if (user == null) {

				/**
				 * Inizializzo i dati del mio test
				 */

				Authority authorityAdmin = new Authority();
				authorityAdmin.setName(AuthorityName.ROLE_ADMIN);
				authorityAdmin = authorityRepository.save(authorityAdmin);

				Authority authorityUser = new Authority();
				authorityUser.setName(AuthorityName.ROLE_USER);
				authorityUser = authorityRepository.save(authorityUser);

				List<Authority> authorities = Arrays.asList(new Authority[] { authorityAdmin, authorityUser });

				user = new User();
				user.setAuthorities(authorities);
				user.setEnabled(true);
				user.setUsername("admin");
				user.setPassword(passwordEncoder.encode("admin"));
				user.setEmail("admin@example.com");

				user = userRepository.save(user);

			}

			User commonUser = userRepository.findByUsername("commonUser").orElse(null);

			if (commonUser == null) {

				/**
				 * Inizializzo i dati del mio test
				 */

				Authority authorityUser = authorityRepository.findByName(AuthorityName.ROLE_USER);
				if (authorityUser == null) {
					authorityUser = new Authority();
					authorityUser.setName(AuthorityName.ROLE_USER);
					authorityUser = authorityRepository.save(authorityUser);
				}

				List<Authority> authorities = Arrays.asList(new Authority[] { authorityUser });

				commonUser = new User();
				commonUser.setAuthorities(authorities);
				commonUser.setEnabled(true);
				commonUser.setUsername("commonUser");
				commonUser.setPassword(passwordEncoder.encode("commonUser"));
				commonUser.setEmail("commonUser@example.com");

				commonUser = userRepository.save(commonUser);

			}
		};
	}

}

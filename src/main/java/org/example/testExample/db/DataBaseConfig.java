package org.example.testExample.db;

import org.example.testExample.dao.CompanyRepository;
import org.example.testExample.dao.UserRepository;
import org.example.testExample.resources.Company;
import org.example.testExample.resources.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableAutoConfiguration
public class DataBaseConfig {
    @Bean
	public CommandLineRunner initDb(UserRepository uRepository, CompanyRepository cRepository)  {
		return args -> {
			List<User> users = new ArrayList<>(Arrays.asList(
					new User(1L, "Renos", "Renos87", "Bardis", "renos@gmail.com"),
					new User(2L, "Omar", "Omar90", "Matter", "omar@gmail.com"),
					new User(3L, "Marc", "Marc97", "Better", "marc@gmail.com")));

			List<Company> companies = new ArrayList<>(Arrays.asList(
					new Company(1L, "Alten", "IT-Sector", "alten@esn-alten.fr"),
					new Company(2L, "Astek", "IT-Sector", "astek@esn-astek.fr"),
					new Company(3L, "ReiffesenBank", "Banking", "rb@banking.cz")));

			Company company = companies.get(0);
			company.setUsers(users);

			uRepository.saveAll(users);
			cRepository.saveAll(companies);
 		};
 	}
 }

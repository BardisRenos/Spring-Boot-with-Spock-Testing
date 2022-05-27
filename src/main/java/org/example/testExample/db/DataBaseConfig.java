package org.example.testExample.db;

import org.example.testExample.dao.UserRepository;
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
	public CommandLineRunner initDb(UserRepository uRepository)  {
		return args ->{
			List<User> users = new ArrayList<>(Arrays.asList(
					new User(1L, "Renos", "Renos87", "Bardis", "renos@gmail.com"),
					new User(2L, "Omar", "Omar90", "Matter", "omar@gmail.com"),
					new User(3L, "Marc", "Marc97", "Better", "marc@gmail.com")));

			uRepository.saveAll(users);
 		};
 	}
 }

package org.example.testExample.db;

import org.example.testExample.dao.CompanyRepository;
import org.example.testExample.dao.EmployeeRepository;
import org.example.testExample.dao.UserRepository;
import org.example.testExample.resources.Company;
import org.example.testExample.resources.Employee;
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
	public CommandLineRunner initDb(UserRepository uRepository, CompanyRepository cRepository, EmployeeRepository eEmployeeRepository)  {
		return args -> {
			List<User> users = new ArrayList<>(Arrays.asList(
					new User(1L, "Renos", "Renos87", "Bardis", "renos@gmail.com"),
					new User(2L, "Omar", "Omar90", "Matter", "omar@gmail.com"),
					new User(3L, "Marc", "Marc97", "Better", "marc@gmail.com")));

			List<Company> companies = new ArrayList<>(Arrays.asList(
					new Company(1L, "Alten", "IT-Sector", "alten@esn-alten.fr"),
					new Company(2L, "Astek", "IT-Sector", "astek@esn-astek.fr"),
					new Company(3L, "ReiffesenBank", "Banking", "rb@banking.cz")));

			List<Employee> employees = new ArrayList<>(Arrays.asList(
					new Employee(1L, "John", "Smith", "123 Boulevard"),
					new Employee(2L, "Nicolas", "Ahten", "589 North Street"),
					new Employee(3L, "Nikki", "Mentoza", "745 South Street")));

			Company company = companies.get(0);
			company.setUsers(users);

			eEmployeeRepository.saveAll(employees);
			uRepository.saveAll(users);
			cRepository.saveAll(companies);
 		};
 	}
 }

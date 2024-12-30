package com.example.employeeallocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.example.employeeallocation.repository")
public class EmployeeallocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeallocationApplication.class, args);
	}

}

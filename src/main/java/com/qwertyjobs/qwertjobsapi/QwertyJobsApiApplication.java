package com.qwertyjobs.qwertjobsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QwertyJobsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(QwertyJobsApiApplication.class, args);
	}
/*
	@Bean
	CommandLineRunner run(User_ServiceImpl user_service){
		return args -> {
			user_service.saveUser(new User(null, "ts@gmail.com", "ts", "1234"));
			*//*user_service.saveUser(new User(null, "ms@gmail.com", "ms", "kasim"));
			user_service.saveUser(new User(null, "ns@gmail.com", "ns", "kasim"));*//*
		};
	}*/

}

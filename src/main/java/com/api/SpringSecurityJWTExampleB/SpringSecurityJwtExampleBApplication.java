package com.api.SpringSecurityJWTExampleB;

import com.api.SpringSecurityJWTExampleB.entity.Role;
import com.api.SpringSecurityJWTExampleB.entity.User;
import com.api.SpringSecurityJWTExampleB.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;


@SpringBootApplication
public class SpringSecurityJwtExampleBApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtExampleBApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		// inject UserService logic into this commandline runner
		// This will run after the application has initialized
		return  args -> {
			/*
			// Create roles
			userService.saveRole( new Role(null, "ROLE_USER"));
			userService.saveRole( new Role(null, "ROLE_MANAGER"));
			userService.saveRole( new Role(null, "ROLE_ADMIN"));
			userService.saveRole( new Role(null, "ROLE_SUPER_ADMIN"));

			// create Users
			userService.saveUser(new User(null, "Shiroe Shirogane",		"Shiroe",	"1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Naotsugu Hasegawa",	"Naotsugu",	"1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Akatsuki Hanekura", 	"Akatsuki",	"1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Nyanta Chief", 		"Nyanta ",	"1234", new ArrayList<>()));

			 */

			// Add role to user
			userService.addRoleToUser("Akatsuki", "ROLE_USER");
			userService.addRoleToUser("Naotsugu", "ROLE_MANAGER");
			userService.addRoleToUser("Nyanta", "ROLE_ADMIN");
			userService.addRoleToUser("Shiroe", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("Shiroe", "ROLE_ADMIN");
			userService.addRoleToUser("Shiroe", "ROLE_MANAGER");
		};
	}

}

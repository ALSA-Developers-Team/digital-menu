package com.alsa.menuapp;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MenuAppApplication /*implements CommandLineRunner*/ {

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(MenuAppApplication.class, args);
	}

	// public void run(String... args) throws Exception{
	// 	Role user = new Role("user");
	// 	Role admin = new Role("admin");
	// 	Role customer = new Role("customer");
	// 	repoRole.saveAll(List.of(user, admin, customer));
	// 	List<Role> lRoles = repoRole.findAll();
	// 	System.out.println(lRoles);

	// 	User newUser = new User("TEST2", "1234");
	// 	uService.saveUser(newUser);
	// 	uService.addRoleToUser("TEST2", "admin");
	// }

}

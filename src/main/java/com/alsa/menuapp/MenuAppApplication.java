package com.alsa.menuapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alsa.menuapp.model.Role;
import com.alsa.menuapp.model.User;
import com.alsa.menuapp.repository.RoleRespository;
import com.alsa.menuapp.repository.UserRepository;

@SpringBootApplication
public class MenuAppApplication implements CommandLineRunner {
	
	@Autowired
	private RoleRespository roleRespository;
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(MenuAppApplication.class, args);
	}

	public void run(String... args) throws Exception{
		// Role role = new Role();
		User user = new User("Test2", "1234", 4);


		// role.setName("TEST");

		// roleRespository.save(role);

		// user.setUsername("Tets");
		// user.setPassword("1234");

		// user.addRole(role);

		userRepository.save(user);
		// Role role = roleRespository.findById(4).orElse(null);

		// System.out.println(role.getUsers().get(0).toString());

		
	}

}

package com.alsa.menuapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alsa.menuapp.model.Role;
import com.alsa.menuapp.repository.RoleRespository;

@SpringBootApplication
public class MenuAppApplication implements CommandLineRunner {
	
	@Autowired
	private RoleRespository roleRespository;

	public static void main(String[] args) {
		SpringApplication.run(MenuAppApplication.class, args);
	}

	public void run(String... args) throws Exception{
		Role role = new Role();
		role.setName("TEST");

		roleRespository.save(role);
	}

}

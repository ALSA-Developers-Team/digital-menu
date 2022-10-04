package com.alsa.menuapp;

import java.util.List;

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
	RoleRespository repoRole;

	@Autowired
	UserRepository repoUser;

	public static void main(String[] args) {
		SpringApplication.run(MenuAppApplication.class, args);
	}

	public void run(String... args) throws Exception{
		Role user = new Role("user");
		Role admin = new Role("admin");
		Role customer = new Role("customer");
		repoRole.saveAll(List.of(user, admin, customer));
		List<Role> lRoles = repoRole.findAll();
		System.out.println(lRoles);

		User newUser = new User("TEST", "1234");
		Role roleUser = repoRole.findByName("user");
		newUser.addRole(roleUser);
		User savedUser = repoUser.save(newUser);
		System.out.println(savedUser);
	}

}

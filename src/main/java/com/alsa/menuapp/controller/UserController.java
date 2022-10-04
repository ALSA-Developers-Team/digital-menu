package com.alsa.menuapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alsa.menuapp.model.User;
import com.alsa.menuapp.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController{
  @Autowired
  UserRepository userRepository;

  @GetMapping("/")
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
}

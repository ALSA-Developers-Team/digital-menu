package com.alsa.menuapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alsa.menuapp.model.User;
import com.alsa.menuapp.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController{
  @Autowired
  UserRepository userRepository;

  @GetMapping("/")
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = new ArrayList<User>();

    userRepository.findAll().forEach(users::add);

    if (users.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(users, HttpStatus.OK);
  }
}

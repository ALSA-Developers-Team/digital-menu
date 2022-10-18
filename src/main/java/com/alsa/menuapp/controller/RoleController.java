package com.alsa.menuapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alsa.menuapp.model.Role;
import com.alsa.menuapp.service.UserService;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private UserService userService;    

    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return userService.getRoles();
    }
}

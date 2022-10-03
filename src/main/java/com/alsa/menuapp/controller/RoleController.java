package com.alsa.menuapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alsa.menuapp.model.Role;
import com.alsa.menuapp.repository.RoleRespository;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleRespository roleRespository;    

    @GetMapping("roles")
    public List<Role> getAllRoles(){
        return roleRespository.findAll();
    }
}

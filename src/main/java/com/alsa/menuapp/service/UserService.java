package com.alsa.menuapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alsa.menuapp.exception.ResourceAlreadyExistsException;
import com.alsa.menuapp.model.Role;
import com.alsa.menuapp.model.User;
import com.alsa.menuapp.repository.RoleRespository;
import com.alsa.menuapp.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {
    private UserRepository userRepo;
    private RoleRespository roleRepo;

    @Autowired
    public UserService(UserRepository userRepo, RoleRespository roleRepo){
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    public User saveUser(User user) throws ResourceAlreadyExistsException{
        User existsUser = userRepo.findByUsername(user.getUsername());
        if(existsUser != null){
            throw new ResourceAlreadyExistsException("user already exists"); 
        }

        log.info("Saving new user {} to the database", user.getUsername());
        return userRepo.save(user);
    }

    public Role saveRole(Role role) throws ResourceAlreadyExistsException{
        Role existsRole = roleRepo.findByName(role.getName());
        if(existsRole != null){
            throw new ResourceAlreadyExistsException("role already exists");
        }

        log.info("Saving new role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    public void addRoleToUser(String username, String roleName){
        log.info("Adding new role {} to user {}", roleName, username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);

        user.addRole(role);
    }

    public User getUser(String username){
        log.info("Fetching user {}", username);
        return userRepo.findByUsername(username);
    }

    public List<User> getUsers(){
        log.info("Fethcing all users");
        return userRepo.findAll();
    }
}

package com.alsa.menuapp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alsa.menuapp.exception.ResourceAlreadyExistsException;
import com.alsa.menuapp.exception.ResourceNotFoundException;
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
public class UserService implements UserDetailsService{
    private UserRepository userRepo;
    private RoleRespository roleRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepo, RoleRespository roleRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null){
            log.error("user not found");
            throw new UsernameNotFoundException("User not found in database");
        }else{
            log.info("User found in database", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    /**
     * Saves a user in database given a user
     * @param user - user instance
     * @return {user}
     * @throws ResourceAlreadyExistsException
     */
    public User saveUser(User user) throws ResourceAlreadyExistsException{
        User existsUser = userRepo.findByUsername(user.getUsername());
        if(existsUser != null){
            throw new ResourceAlreadyExistsException("user already exists"); 
        }

        log.info("Saving new user {} to the database", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    /**
     * saves a role in database given a role
     * @param role - role instance
     * @return {role}
     * @throws ResourceAlreadyExistsException
     */
    public Role saveRole(Role role) throws ResourceAlreadyExistsException{
        Role existsRole = roleRepo.findByName(role.getName());
        if(existsRole != null){
            throw new ResourceAlreadyExistsException("role already exists");
        }

        log.info("Saving new role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    /**
     * ads a role to a user from database
     * @param username username {String}
     * @param roleName rolename {String}
     */
    public void addRoleToUser(String username, String roleName) throws ResourceNotFoundException{
        log.info("Adding new role {} to user {}", roleName, username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);

        if(user == null) throw new ResourceNotFoundException("user not found");
        if(role == null) throw new ResourceNotFoundException("role not found");

        user.addRole(role);
    }

    /**
     * fetchs user from database given a username
     * @param username username {String}
     * @return User instance
     */
    public User getUser(String username){
        log.info("Fetching user {}", username);
        return userRepo.findByUsername(username);
    }

    /**
     * retrieves a list of all the users from database
     * @return a list of users {List<User>}
     */
    public List<User> getUsers(){
        log.info("Fethcing all users");
        return userRepo.findAll();
    }
}

package com.alsa.menuapp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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
            log.error("user with username: {} not found", username);
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
            log.info("user: {} already exists", user);
            throw new ResourceAlreadyExistsException("user already exists"); 
        }

        log.info("Saving new user {} to the database", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User newUser = userRepo.save(user);
        addRoleToUser(newUser.getUsername(), "user");
        return newUser;
    }

    /**
     * deletes user from database
     * @param id - user id
     * @return {user}
     * @throws ResourceNotFoundException
     */
    public int deleteUser(int id) throws ResourceNotFoundException{
        boolean existsUser = userRepo.existsById(id);
        if(!existsUser){
            log.info("user with id: {} does not exists", id);
            throw new ResourceNotFoundException("user does not exists");
        }
        
        log.info("deleting user with id: {} from database", id);
        userRepo.deleteById(id);
        return id;
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
            log.info("failed to complete operation, role: {} already exists", role);
            throw new ResourceAlreadyExistsException("role already exists");
        }

        log.info("Saving new role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    /**
     * deletes role from database
     * @param roleId - role id
     * @throws ResourceNotFoundException
     */
    public void deleteRole(int roleId) throws ResourceNotFoundException{
        Role existsRole = roleRepo.findById(roleId).orElse(null);
        if(existsRole == null){
            log.info("failed to complete operation, role with id: {} does not exists", roleId);
            throw new ResourceNotFoundException("role does not exists");
        }

        log.info("deleting role {} from database", roleId);
        roleRepo.delete(existsRole);
    }

    /**
     * adds role to user from database
     * @param username - username
     * @param roleName - role name
     * @throws ResourceNotFoundException
     */
    public void addRoleToUser(String username, String roleName) throws ResourceNotFoundException{
        log.info("Adding new role {} to user {}", roleName, username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);

        if(user == null) {
            log.info("no user found with username: {}", username);
            throw new ResourceNotFoundException("user not found");
        } 
        if(role == null){
            log.info("no role found with role name: {}", roleName);
            throw new ResourceNotFoundException("role not found");
        } 
        Set<Role> roles = user.getRoles();
        if(roles.contains(role)) throw new ResourceAlreadyExistsException("user already has this role");

        user.addRole(role);
    }

    /**
     * removes role from user from database
     * @param userId - user id
     * @param roleId - role id
     * @throws ResourceNotFoundException
     */
    public void removeRoleFromUser(int userId, int roleId) throws ResourceNotFoundException{
        User user = userRepo.findById(userId).orElse(null);
        Role role = roleRepo.findById(roleId).orElse(null);
        if(user == null){
            log.info("failed to complete operation, user with id: {} does not exists", userId);
        }
        if(role == null){
            log.info("failed to complete operation, role with id: {} does not exists", roleId);
            throw new ResourceNotFoundException("role does not exists");
        }

        log.info("deleting role {} from database", roleId);
        Set<Role> roles = user.getRoles(); //nunca va a ser null porque cuando se guarda el usuario se asigna un role por defecto
        if(roles.contains(role)){
            roles.remove(role);
            user.setRoles(roles);
        }
        
        userRepo.save(user);
    }

    /**
     * retrieves a list of all the users from database
     * @return a list of users {List<User>}
     */
    public List<User> getUsers(){
        log.info("Fetching all users");
        return userRepo.findAll();
    }

    /**
     * retrieves a list of all roles from database
     * @return a list of roles {List<Role>}
     */
    public List<Role> getRoles(){
        log.info("Fethcing all roles");
        return roleRepo.findAll();
    }
}

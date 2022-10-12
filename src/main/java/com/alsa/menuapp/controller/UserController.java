package com.alsa.menuapp.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alsa.menuapp.model.Role;
import com.alsa.menuapp.model.User;
import com.alsa.menuapp.service.UserService;

import lombok.Data;

@RestController
@RequestMapping("/api/users")
public class UserController{
  @Autowired
  UserService userService;

  @GetMapping()
  @Description(value = "returns a list of all users")
  public ResponseEntity<List<User>> getAllUsers() {
    return ResponseEntity.ok().body(userService.getUsers());
  }

  @PostMapping("/{roleName}")
  @Description(value = "saves a new user given a user by the body")
  public ResponseEntity<User> saveUser(@RequestBody User user, @PathVariable String roleName) {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/users/save").toUriString());
    return ResponseEntity.created(uri).body(userService.saveUser(user, roleName));
  }

  @DeleteMapping("/{id}")
  @Description(value = "deletes a user given an id by params")
  public ResponseEntity<Integer> deleteUser(@PathVariable String id) {
    return ResponseEntity.ok().body(userService.deleteUser(Integer.parseInt(id)));
  }

  @GetMapping("/roles")
  @Description(value = "returns a list of all roles")
  public ResponseEntity<List<Role>> getAllRoles() {
    return ResponseEntity.ok().body(userService.getRoles());
  }

  @PostMapping("/roles")
  @Description(value = "saves a new role given a role by the body")
  public ResponseEntity<Role> saveRole(@RequestBody Role role) {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/users/save").toUriString());
    return ResponseEntity.created(uri).body(userService.saveRole(role));
  }

  @DeleteMapping("/roles/{id}")
  @Description(value = "deletes a role given an id by params")
  public ResponseEntity<?> deleteRole(@PathVariable String id) {
    userService.deleteRole(Integer.parseInt(id));
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{userId}/roles/{roleId}")
  @Description(value = "deletes a role from user given an user id and role id by params")
  public ResponseEntity<?> deleteRoleFromUser(@PathVariable String userId, @PathVariable String roleId) {
    userService.removeRoleFromUser(Integer.parseInt(userId), Integer.parseInt(roleId));
    return ResponseEntity.ok().build();
  }

  @PostMapping("/roles/addtouser")
  @Description(value = "adds role to user given a username and a role name")
  public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
    userService.addRoleToUser(form.getUsername(), form.getRoleName());
    return ResponseEntity.ok().build();
  }

}

@Data
class RoleToUserForm{
  private String username;
  private String roleName;
}

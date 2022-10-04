package com.alsa.menuapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {

    public User (String username, String password){
        this.username = username;
        this.password = password;
    }

    public User (String username, String password, int roleId){
        this.username = username;
        this.password = password;
        this.addRole(roleId);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(name = "users_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<Role>();

    public void addRole(Role role){
        this.roles.add(role);
    }

    public void addRole(int id){
        Role existingRole = this.roles.stream().filter(role -> role.getId() == id).findFirst().orElse(null);
        if (existingRole != null){
            this.addRole(existingRole);
        }
    }

    public void removeRole(int id){
        Role existingRole = this.roles.stream().filter(role -> role.getId() == id).findFirst().orElse(null);
        if (existingRole != null){
            this.roles.remove(existingRole);
        }
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", roles='" + getRoles() + "'" +
            "}";
    }

}

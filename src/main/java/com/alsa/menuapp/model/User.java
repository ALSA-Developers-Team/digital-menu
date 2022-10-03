package com.alsa.menuapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
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

    @OneToMany
    @JoinTable(name = "role_user", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<Role>();

    public void addRole(Role role){
        this.roles.add(role);
        role.getUsers().add(this);
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
            existingRole.getUsers().remove(this);
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

package com.alsa.menuapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alsa.menuapp.model.Role;

@Repository
public interface RoleRespository extends JpaRepository<Role, Integer>{
    public Role findByName(String name);
}

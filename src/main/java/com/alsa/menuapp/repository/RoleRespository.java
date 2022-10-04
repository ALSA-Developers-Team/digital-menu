package com.alsa.menuapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alsa.menuapp.model.Role;

@Repository
public interface RoleRespository extends JpaRepository<Role, Integer>{
    @Query("SELECT r FROM Role r WHERE r.name = ?1")
    public Role findByName(String name);
}

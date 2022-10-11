package com.alsa.menuapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alsa.menuapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    
    User findByUsername(String username);
    
}

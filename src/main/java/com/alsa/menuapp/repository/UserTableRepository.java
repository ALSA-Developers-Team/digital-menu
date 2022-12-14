package com.alsa.menuapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alsa.menuapp.model.UserTable;

@Repository
public interface UserTableRepository extends JpaRepository<UserTable, Integer>{
    
    UserTable findByNumber(int number);
}
package com.alsa.menuapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alsa.menuapp.model.Status;

public interface StatusRepository extends JpaRepository<Status, Integer>{
    Status getByName(String name);
}

package com.alsa.menuapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alsa.menuapp.model.Plate;

@Repository
public interface PlateRepository extends JpaRepository<Plate, Integer>{
    
}
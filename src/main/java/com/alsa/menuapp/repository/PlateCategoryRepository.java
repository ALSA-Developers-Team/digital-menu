package com.alsa.menuapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alsa.menuapp.model.PlateCategory;

@Repository
public interface PlateCategoryRepository extends JpaRepository<PlateCategory, Integer>{
    
}
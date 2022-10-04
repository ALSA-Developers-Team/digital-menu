package com.alsa.menuapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alsa.menuapp.model.TableSection;

@Repository
public interface TableSectionRepository extends JpaRepository<TableSection, Integer>{
    
}
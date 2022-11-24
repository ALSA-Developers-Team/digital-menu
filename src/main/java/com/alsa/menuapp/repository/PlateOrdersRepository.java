package com.alsa.menuapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alsa.menuapp.model.PlateOrder;

@Repository
public interface PlateOrdersRepository extends JpaRepository<PlateOrder, Integer> {
    
}

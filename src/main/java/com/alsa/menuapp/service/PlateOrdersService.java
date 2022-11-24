package com.alsa.menuapp.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.alsa.menuapp.exception.ResourceAlreadyExistsException;
import com.alsa.menuapp.exception.ResourceNotFoundException;
import com.alsa.menuapp.exception.UpdateFaildeException;
import com.alsa.menuapp.model.PlateOrder;
import com.alsa.menuapp.repository.PlateOrdersRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PlateOrdersService {
    @Autowired
    PlateOrdersRepository plateOrdersRepo;

    public List<PlateOrder> getAllPlates(){
        log.info("fetching all plate orders");
        return plateOrdersRepo.findAll();
    }

    public PlateOrder getPlateOrder(int id){
        log.info("fetching plate order with id: {}", id);
        PlateOrder existsPlate = plateOrdersRepo.findById(id).orElse(null);
        if(existsPlate == null){
            log.error("plate order with id: '{}'", id);
            throw new ResourceNotFoundException("plate order does not exists"); 
        }
        return existsPlate;
    }

    public PlateOrder savePlate(PlateOrder plate){
        log.info("saving new plate order: {}", plate);
        return plateOrdersRepo.save(plate);
    }

    public PlateOrder updatePlate(int id, Map<Object, Object> fields){
        log.info("updating plate order with id: {}", id);
        PlateOrder existsPlate = plateOrdersRepo.findById(id).orElse(null);
        if(existsPlate == null){
            log.error("plate order with id: {} does not exists", id);
            throw new ResourceNotFoundException("plate order does not exists"); 
        }
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(PlateOrder.class, (String) key);
            if(field != null){
                field.setAccessible(true);
                ReflectionUtils.setField(field, existsPlate, value);
            }else{
                log.error("error happened while updating plate order with id: {}", id);
                throw new UpdateFaildeException("error while updating");
            }
        });
        
        return plateOrdersRepo.save(existsPlate);
    }

    public void deletePlate(int id){
        log.info("deleting plate order with id: {}", id);
        PlateOrder existsPlate = plateOrdersRepo.findById(id).orElse(null);
        if(existsPlate == null){
            log.error("plate order with id: '{}'", id);
            throw new ResourceNotFoundException("plate order does not exists"); 
        }
        plateOrdersRepo.deleteById(id);
    }
}

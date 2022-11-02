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
import com.alsa.menuapp.model.Plate;
import com.alsa.menuapp.repository.PlateRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PlateService {
    @Autowired
    PlateRepository plateRepo;

    public List<Plate> getAllPlates(){
        log.info("fetching all plates");
        return plateRepo.findAll();
    }

    public Plate getPlate(int id){
        log.info("fetching plate with id: {}", id);
        Plate existsPlate = plateRepo.findById(id).orElse(null);
        if(existsPlate == null){
            log.error("plate with id: '{}'", id);
            throw new ResourceNotFoundException("plate does not exists"); 
        }
        return existsPlate;
    }

    public Plate savePlate(Plate plate){
        log.info("saving new plate: {}", plate);
        Plate existsPlate = plateRepo.findByName(plate.getName());
        if(existsPlate != null){
            if(existsPlate.getCategory().getId() == plate.getCategory().getId()){
                log.error("plate with name: {} does not exists", plate.getName());
                throw new ResourceAlreadyExistsException("plate already exists");
            }
            return plateRepo.save(plate);
        }

        return plateRepo.save(plate);
    }

    public Plate updatePlate(int id, Map<Object, Object> fields){
        log.info("updating plate with id: {}", id);
        Plate existsPlate = plateRepo.findById(id).orElse(null);
        if(existsPlate == null){
            log.error("plate with id: {} does not exists", id);
            throw new ResourceNotFoundException("plate does not exists"); 
        }
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Plate.class, (String) key);
            if(field != null){
                field.setAccessible(true);
                ReflectionUtils.setField(field, existsPlate, value);
            }else{
                log.error("error happened while updating plate with id: {}", id);
                throw new UpdateFaildeException("error while updating");
            }
        });
        
        return plateRepo.save(existsPlate);
    }

    public void deletePlate(int id){
        log.info("deleting plate with id: {}", id);
        Plate existsPlate = plateRepo.findById(id).orElse(null);
        if(existsPlate == null){
            log.error("plate with id: '{}'", id);
            throw new ResourceNotFoundException("plate does not exists"); 
        }
        plateRepo.deleteById(id);
    }
}

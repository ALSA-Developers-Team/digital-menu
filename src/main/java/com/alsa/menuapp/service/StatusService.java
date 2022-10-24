package com.alsa.menuapp.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.alsa.menuapp.exception.ResourceNotFoundException;
import com.alsa.menuapp.model.Status;
import com.alsa.menuapp.repository.StatusRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StatusService {
    @Autowired
    StatusRepository statusRepo;

    public List<Status> getAllStatus(){
        log.info("fetching all status");
        return statusRepo.findAll();
    }

    public Status getStatus(int id){
        log.info("fetching status with id: {}", id);
        Status existsStatus = statusRepo.findById(id).orElse(null);
        if(existsStatus == null){
            log.error("status with id: '{}'", id);
            throw new ResourceNotFoundException("status does not exists"); 
        }
        return existsStatus;
    }

    public Status saveStatus(Status status){
        log.info("saving new status: {}", status);
        return statusRepo.save(status);
    }

    public Status updateStatus(int id, Map<Object, Object> fields){
        log.info("updating status with id: {}", id);
        Status existsStatus = statusRepo.findById(id).orElse(null);
        if(existsStatus == null){
            log.error("status with id: {} does not exists", id);
            throw new ResourceNotFoundException("status does not exists"); 
        }
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Status.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existsStatus, value);
        });
        
        return statusRepo.save(existsStatus);
    }

    public void deleteStatus(int id){
        log.info("deleting status with id: {}", id);
        Status existsStatus = statusRepo.findById(id).orElse(null);
        if(existsStatus == null){
            log.error("status with id: '{}'", id);
            throw new ResourceNotFoundException("status does not exists"); 
        }
        statusRepo.deleteById(id);
    }
}

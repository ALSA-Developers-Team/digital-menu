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
import com.alsa.menuapp.model.PlateCategory;
import com.alsa.menuapp.repository.PlateCategoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PlateCategoryService {
    @Autowired
    PlateCategoryRepository categoryRepo;

    public List<PlateCategory> getAllPlateCategories(){
        log.info("fetching all plate categories");
        return categoryRepo.findAll();
    }

    public PlateCategory getPlateCategory(int id){
        log.info("fetching plate category with id: {}", id);
        PlateCategory existsCategory = categoryRepo.findById(id).orElse(null);
        if(existsCategory == null){
            log.error("plate category with id: '{}'", id);
            throw new ResourceNotFoundException("plate category does not exists"); 
        }
        return existsCategory;
    }

    public PlateCategory savePlateCategory(PlateCategory category){
        log.info("saving new plate category: {}", category);
        PlateCategory existsCategory = categoryRepo.findByName(category.getName());
        if(existsCategory != null){
            log.error("plate category with name: {} already exists", category.getName());
            throw new ResourceAlreadyExistsException("plate already exists");
        }

        return categoryRepo.save(category);
    }

    public PlateCategory updatePlateCategory(int id, Map<Object, Object> fields){
        log.info("updating plate category with id: {}", id);
        PlateCategory existsCategory = categoryRepo.findById(id).orElse(null);
        if(existsCategory == null){
            log.error("plate category with id: {} does not exists", id);
            throw new ResourceNotFoundException("plate category does not exists"); 
        }
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(PlateCategory.class, (String) key);
            if(field != null){
                field.setAccessible(true);
                ReflectionUtils.setField(field, existsCategory, value);
            }else{
                log.error("error happened while updating plate category with id: {}", id);
                throw new UpdateFaildeException("error while updating");
            }
        });
        
        return categoryRepo.save(existsCategory);
    }

    public void deletePlateCategory(int id){
        log.info("deleting plate category with id: {}", id);
        PlateCategory existsCategory = categoryRepo.findById(id).orElse(null);
        if(existsCategory == null){
            log.error("plate category with id: '{}'", id);
            throw new ResourceNotFoundException("plate category does not exists"); 
        }
        categoryRepo.deleteById(id);
    }
}

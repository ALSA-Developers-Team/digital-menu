package com.alsa.menuapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alsa.menuapp.exception.ResourceAlreadyExistsException;
import com.alsa.menuapp.exception.ResourceNotFoundException;
import com.alsa.menuapp.model.TableSection;
import com.alsa.menuapp.repository.TableSectionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TableSectionService {
    @Autowired
    private TableSectionRepository tableRepo;

    public List<TableSection> getAllTableSections(){
        log.info("fetching all table sections");
        return tableRepo.findAll();
    }

    public TableSection getTableSectionByName(String tSectionName){
        log.info("fetching table section with name {}", tSectionName);
        TableSection existsSection = tableRepo.findByName(tSectionName);
        if(existsSection == null){
            log.error("table section: '{}' does not exists", tSectionName);
            throw new ResourceNotFoundException("table section does not exists"); 
        }
        return existsSection;
    }

    public TableSection saveTableSection(TableSection tableSection){
        log.info("saving new table section: {}", tableSection);
        TableSection existsSection = tableRepo.findByName(tableSection.getName());
        if(existsSection != null){
            log.error("table section: {} already exists", existsSection);
            throw new ResourceAlreadyExistsException("table section already exists"); 
        }

        return tableRepo.save(tableSection);
    }

    public TableSection updateTableSection(int id, TableSection tSection){
        log.info("updating table section with id: {}", id);
        TableSection existsSection = tableRepo.findById(id).orElse(null);
        if(existsSection == null){
            log.error("table section: {} does not exists", existsSection);
            throw new ResourceNotFoundException("table section does not exists"); 
        }

        if(existsSection.getName() != tSection.getName()) existsSection.setName(tSection.getName());
        
        return tableRepo.save(existsSection);
    }

    public void deleteTableSection(int id){
        log.info("deleting table section with id: {} from database", id);
        TableSection existsSection = tableRepo.findById(id).orElse(null);
        if(existsSection == null){
            log.error("table section: {} does not exists", existsSection);
            throw new ResourceNotFoundException("table section does not exists"); 
        }

        tableRepo.delete(existsSection);
    }

}

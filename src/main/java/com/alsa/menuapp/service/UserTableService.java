package com.alsa.menuapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alsa.menuapp.exception.ResourceAlreadyExistsException;
import com.alsa.menuapp.exception.ResourceNotFoundException;
import com.alsa.menuapp.model.UserTable;
import com.alsa.menuapp.repository.UserTableRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserTableService {
    @Autowired
    private UserTableRepository tableRepo;

    public List<UserTable> getAllTables(){
        log.info("fetching all table sections");
        return tableRepo.findAll();
    }

    public UserTable getTableByNumber(int number){
        log.info("fetching table with number {}", number);
        UserTable existsTable = tableRepo.findByNumber(number);
        if(existsTable == null){
            log.error("table with number: '{}' does not exists", number);
            throw new ResourceNotFoundException("table does not exists"); 
        }
        return existsTable;
    }

    public UserTable saveTable(UserTable table){
        log.info("saving new table: {}", table);
        UserTable existsTable = tableRepo.findByNumber(table.getNumber());
        if(existsTable != null){
            log.error("table: {} already exists", existsTable);
            throw new ResourceAlreadyExistsException("table section already exists"); 
        }

        return tableRepo.save(table);
    }

    public UserTable updateTableSection(int id, UserTable table){
        log.info("updating table with id: {}", id);
        UserTable existsTable = tableRepo.findById(id).orElse(null);
        if(existsTable == null){
            log.error("table with id: {} does not exists", id);
            throw new ResourceNotFoundException("table does not exists"); 
        }

        if(existsTable.getNumber() != table.getNumber()) existsTable.setNumber(table.getNumber());
        if(existsTable.getToken() != table.getToken()) existsTable.setToken(table.getToken());
        if(existsTable.getTableSection() != table.getTableSection()) existsTable.setTableSection(table.getTableSection());
        
        return tableRepo.save(existsTable);
    }

    public void deleteTable(int id){
        log.info("deleting table with id: {} from database", id);
        UserTable existsTable = tableRepo.findById(id).orElse(null);
        if(existsTable == null){
            log.error("table: {} does not exists", existsTable);
            throw new ResourceNotFoundException("table does not exists"); 
        }

        tableRepo.delete(existsTable);
    }
}

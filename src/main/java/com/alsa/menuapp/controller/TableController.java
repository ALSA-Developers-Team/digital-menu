package com.alsa.menuapp.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alsa.menuapp.model.UserTable;
import com.alsa.menuapp.service.UserTableService;

@RestController
@RequestMapping("/api/restaurant/tables")
public class TableController {
    @Autowired
    UserTableService tService;
    
    @GetMapping()
    @Description(value = "returns a list of all tables")
    public ResponseEntity<List<UserTable>> getAllSections() {
      return ResponseEntity.ok().body(tService.getAllTables());
    }

    @GetMapping("/{number}")
    @Description(value = "returns table section by its number")
    public ResponseEntity<UserTable> getTable(@PathVariable int number) {
      return ResponseEntity.ok().body(tService.getTableByNumber(number));
    }

    @PostMapping()
    @Description(value = "saves a new table given a table by the body")
    public ResponseEntity<UserTable> saveTable(@RequestBody UserTable table) {
      URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/restaurant/tables").toUriString());
      return ResponseEntity.created(uri).body(tService.saveTable(table));
    }

    @PutMapping("/{id}")
    @Description(value = "updates table section given a table section by the body")
    public ResponseEntity<UserTable> updateTable(@PathVariable int id, @RequestBody UserTable table) {
        return ResponseEntity.ok().body(tService.updateTableSection(id, table));
    }

    @DeleteMapping("/{id}")
    @Description(value = "deletes a table given an id by path variable")
    public ResponseEntity<Integer> deleteTable(@PathVariable int id) {
        tService.deleteTable(id);
        return ResponseEntity.ok().body(id);
    }
}
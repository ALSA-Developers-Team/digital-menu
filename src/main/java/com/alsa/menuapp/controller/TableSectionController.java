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

import com.alsa.menuapp.model.TableSection;
import com.alsa.menuapp.service.TableSectionService;

@RestController
@RequestMapping("/api/restaurant/tableSections")
public class TableSectionController {
    @Autowired
    TableSectionService tSectionService;

    @GetMapping()
    @Description(value = "returns a list of all table sections")
    public ResponseEntity<List<TableSection>> getAllSections() {
      return ResponseEntity.ok().body(tSectionService.getAllTableSections());
    }

    @GetMapping("/{name}")
    @Description(value = "returns table section by name")
    public ResponseEntity<TableSection> getSection(@PathVariable String name) {
      return ResponseEntity.ok().body(tSectionService.getTableSectionByName(name));
    }

    @PostMapping()
    @Description(value = "saves a new table section given a table section by the body")
    public ResponseEntity<TableSection> saveUser(@RequestBody TableSection tSection) {
      URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/resaurant/tableSections").toUriString());
      return ResponseEntity.created(uri).body(tSectionService.saveTableSection(tSection));
    }

    @PutMapping("/{id}")
    @Description(value = "updates table section given a table section by the body")
    public ResponseEntity<TableSection> updateTableSection(@PathVariable int id, @RequestBody TableSection tSection) {
        return ResponseEntity.ok().body(tSectionService.updateTableSection(id, tSection));
    }

    @DeleteMapping("/{id}")
    @Description(value = "deletes a table given an id by path variable")
    public ResponseEntity<Integer> deleteTableSection(@PathVariable int id) {
        tSectionService.deleteTableSection(id);
        return ResponseEntity.ok().body(id);
    }
}

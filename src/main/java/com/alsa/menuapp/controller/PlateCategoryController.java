package com.alsa.menuapp.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

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

import com.alsa.menuapp.model.PlateCategory;
import com.alsa.menuapp.service.PlateCategoryService;

@RestController
@RequestMapping("/api/restaurant/plateCategories")
public class PlateCategoryController {
    @Autowired
    PlateCategoryService cService;

    @GetMapping()
    @Description(value = "returns a list of all plate categories")
    public ResponseEntity<List<PlateCategory>> getAllBills() {
      return ResponseEntity.ok().body(cService.getAllPlateCategories());
    }

    @GetMapping("/{id}")
    @Description(value = "returns plate category by its id")
    public ResponseEntity<PlateCategory> getBill(@PathVariable int id) {
      return ResponseEntity.ok().body(cService.getPlateCategory(id));
    }

    @PostMapping()
    @Description(value = "saves a new plate category given a plate category by the body")
    public ResponseEntity<PlateCategory> saveBill(@RequestBody PlateCategory plateCategory) {
      URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/restaurant/plateCategories").toUriString());
      return ResponseEntity.created(uri).body(cService.savePlateCategory(plateCategory));
    }

    @PutMapping("/{id}")
    @Description(value = "updates plate category given a plate category by the body")
    public ResponseEntity<PlateCategory> updateBill(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        return ResponseEntity.ok().body(cService.updatePlateCategory(id, fields));
    }

    @DeleteMapping("/{id}")
    @Description(value = "deletes a plate category given an id by path variable")
    public ResponseEntity<Integer> deletePlate(@PathVariable int id) {
        cService.deletePlateCategory(id);
        return ResponseEntity.ok().body(id);
    }
}

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

import com.alsa.menuapp.model.Plate;
import com.alsa.menuapp.service.PlateService;

@RestController
@RequestMapping("/api/restaurant/plates")
public class PlateController {
    @Autowired
    PlateService pService;

    @GetMapping()
    @Description(value = "returns a list of all plates")
    public ResponseEntity<List<Plate>> getAllPlates() {
      return ResponseEntity.ok().body(pService.getAllPlates());
    }

    @GetMapping("/{id}")
    @Description(value = "returns plate by its id")
    public ResponseEntity<Plate> getPlate(@PathVariable int id) {
      return ResponseEntity.ok().body(pService.getPlate(id));
    }

    @PostMapping()
    @Description(value = "saves a new plate given a plate by the body")
    public ResponseEntity<Plate> savePlate(@RequestBody Plate plate) {
      URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/restaurant/plates").toUriString());
      return ResponseEntity.created(uri).body(pService.savePlate(plate));
    }

    @PutMapping("/{id}")
    @Description(value = "updates plate given a bill by the body")
    public ResponseEntity<Plate> updatePlate(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        return ResponseEntity.ok().body(pService.updatePlate(id, fields));
    }

    @DeleteMapping("/{id}")
    @Description(value = "deletes a plate given an id by path variable")
    public ResponseEntity<Integer> deletePlate(@PathVariable int id) {
        pService.deletePlate(id);
        return ResponseEntity.ok().body(id);
    }
}

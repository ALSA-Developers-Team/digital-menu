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

import com.alsa.menuapp.model.PlateOrder;
import com.alsa.menuapp.service.PlateOrdersService;

@RestController
@RequestMapping("/api/restaurant/plateOrders")
public class PlateOrderController {
    @Autowired
    PlateOrdersService pService;

    @GetMapping()
    @Description(value = "returns a list of all plate orders")
    public ResponseEntity<List<PlateOrder>> getAllPlates() {
      return ResponseEntity.ok().body(pService.getAllPlates());
    }

    @GetMapping("/{id}")
    @Description(value = "returns plate order by its id")
    public ResponseEntity<PlateOrder> getPlate(@PathVariable int id) {
      return ResponseEntity.ok().body(pService.getPlateOrder(id));
    }

    @PostMapping()
    @Description(value = "saves a new plate order given a plate by the body")
    public ResponseEntity<PlateOrder> savePlate(@RequestBody PlateOrder pOrder) {
      URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/restaurant/plates").toUriString());
      return ResponseEntity.created(uri).body(pService.savePlate(pOrder));
    }

    @PutMapping("/{id}")
    @Description(value = "updates plate order given a bill by the body")
    public ResponseEntity<PlateOrder> updatePlate(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        return ResponseEntity.ok().body(pService.updatePlate(id, fields));
    }

    @DeleteMapping("/{id}")
    @Description(value = "deletes a plate order given an id by path variable")
    public ResponseEntity<Integer> deletePlate(@PathVariable int id) {
        pService.deletePlate(id);
        return ResponseEntity.ok().body(id);
    }
}

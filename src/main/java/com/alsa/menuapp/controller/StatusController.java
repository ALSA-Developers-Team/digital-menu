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

import com.alsa.menuapp.model.Status;
import com.alsa.menuapp.service.StatusService;

@RestController
@RequestMapping("/api/restaurant/status")
public class StatusController {
    @Autowired
    StatusService sService;

    @GetMapping()
    @Description(value = "returns a list of all status")
    public ResponseEntity<List<Status>> getAllStatus() {
      return ResponseEntity.ok().body(sService.getAllStatus());
    }

    @GetMapping("/{id}")
    @Description(value = "returns status by its id")
    public ResponseEntity<Status> getStatus(@PathVariable int id) {
      return ResponseEntity.ok().body(sService.getStatus(id));
    }

    @PostMapping()
    @Description(value = "saves a new status given a status by the body")
    public ResponseEntity<Status> saveStatus(@RequestBody Status status) {
      URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/restaurant/status").toUriString());
      return ResponseEntity.created(uri).body(sService.saveStatus(status));
    }

    @PutMapping("/{id}")
    @Description(value = "updates status given a status by the body")
    public ResponseEntity<Status> updateBill(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        return ResponseEntity.ok().body(sService.updateStatus(id, fields));
    }

    @DeleteMapping("/{id}")
    @Description(value = "deletes a status given an id by path variable")
    public ResponseEntity<Integer> deleteStatus(@PathVariable int id) {
        sService.deleteStatus(id);
        return ResponseEntity.ok().body(id);
    }
}

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

import com.alsa.menuapp.model.Bill;
import com.alsa.menuapp.service.BillService;

@RestController
@RequestMapping("/api/restaurant/bills")
public class BillController {
    @Autowired
    BillService bService;

    @GetMapping()
    @Description(value = "returns a list of all bills")
    public ResponseEntity<List<Bill>> getAllBills() {
      return ResponseEntity.ok().body(bService.getAllBills());
    }

    @GetMapping("/{id}")
    @Description(value = "returns bill by its id")
    public ResponseEntity<Bill> getBill(@PathVariable int id) {
      return ResponseEntity.ok().body(bService.getBill(id));
    }

    @PostMapping()
    @Description(value = "saves a new bill given a bill by the body")
    public ResponseEntity<Bill> saveBill(@RequestBody Bill bill) {
      URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/restaurant/bills").toUriString());
      return ResponseEntity.created(uri).body(bService.saveBill(bill));
    }

    @PutMapping("/{id}")
    @Description(value = "updates bill given a bill by the body")
    public ResponseEntity<Bill> updateBill(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        return ResponseEntity.ok().body(bService.updateBill(id, fields));
    }

    @DeleteMapping("/{id}")
    @Description(value = "deletes a table given an id by path variable")
    public ResponseEntity<Integer> deleteBill(@PathVariable int id) {
        bService.deleteBill(id);
        return ResponseEntity.ok().body(id);
    }
}

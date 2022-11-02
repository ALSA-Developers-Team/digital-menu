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

import com.alsa.menuapp.model.Order;
import com.alsa.menuapp.service.OrderService;

@RestController
@RequestMapping("/api/restaurant/orders")
public class OderController {
  @Autowired
  OrderService oService;

  @GetMapping()
  @Description(value = "returns a list of all orders")
  public ResponseEntity<List<Order>> getAllBills() {
    return ResponseEntity.ok().body(oService.getAllOrders());
  }

  @GetMapping("/{id}")
  @Description(value = "returns order by its id")
  public ResponseEntity<Order> getBill(@PathVariable int id) {
    return ResponseEntity.ok().body(oService.getOrder(id));
  }

  @PostMapping()
  @Description(value = "saves a new order given a order by the body")
  public ResponseEntity<Order> saveBill(@RequestBody Order order) {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/restaurant/orders").toUriString());
    return ResponseEntity.created(uri).body(oService.saveOrder(order));
  }

  @PutMapping("/{id}")
  @Description(value = "updates order given a order by the body")
  public ResponseEntity<Order> updateBill(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
      return ResponseEntity.ok().body(oService.updateOrder(id, fields));
  }

  @DeleteMapping("/{id}")
  @Description(value = "deletes a order given an id by path variable")
  public ResponseEntity<Integer> deleteBill(@PathVariable int id) {
      oService.deleteOrder(id);
      return ResponseEntity.ok().body(id);
  }

}

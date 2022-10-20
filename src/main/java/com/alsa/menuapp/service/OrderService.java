package com.alsa.menuapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alsa.menuapp.exception.ResourceNotFoundException;
import com.alsa.menuapp.model.Order;
import com.alsa.menuapp.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {
   @Autowired
    OrderRepository orderRepo;

    public List<Order> getAllOrders(){
        log.info("fetching all orders");
        return orderRepo.findAll();
    }

    public Order getOrder(int id){
        log.info("fetching order with id: {}", id);
        Order existsOrder = orderRepo.findById(id).orElse(null);
        if(existsOrder == null){
            log.error("order with id: '{}'", id);
            throw new ResourceNotFoundException("order does not exists"); 
        }
        return existsOrder;
    }

    // public Order saveOrder(Order order){
    //     log.info("saving new bill: {}", bill);
    //     return billRepo.save(bill);
    // }

    // public Bill updateBill(int id, Map<Object, Object> fields){
    //     log.info("updating bill with id: {}", id);
    //     Bill existsBill = billRepo.findById(id).orElse(null);
    //     if(existsBill == null){
    //         log.error("bill with id: {} does not exists", id);
    //         throw new ResourceNotFoundException("bill does not exists"); 
    //     }
    //     fields.forEach((key, value) -> {
    //         Field field = ReflectionUtils.findField(Bill.class, (String) key);
    //         field.setAccessible(true);
    //         ReflectionUtils.setField(field, existsBill, value);
    //     });
        
    //     return billRepo.save(existsBill);
    // }
}

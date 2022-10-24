package com.alsa.menuapp.service;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.alsa.menuapp.exception.ResourceNotFoundException;
import com.alsa.menuapp.model.Order;
import com.alsa.menuapp.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
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

    public Order saveOrder(Order order){
        log.info("saving new order: {}", order);
        order.setPlaceDate(new Date());
        return orderRepo.save(order);
    }

    public Order updateOrder(int id, Map<Object, Object> fields){
        log.info("updating order with id: {}", id);
        Order existsOrder = orderRepo.findById(id).orElse(null);
        if(existsOrder == null){
            log.error("order with id: {} does not exists", id);
            throw new ResourceNotFoundException("order does not exists"); 
        }
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Order.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existsOrder, value);
        });
        
        return orderRepo.save(existsOrder);
    }

    public void deleteOrder(int id){
        log.info("deleting order with id: {}", id);
        Order existsOrder = orderRepo.findById(id).orElse(null);
        if(existsOrder == null){
            log.error("order with id: '{}'", id);
            throw new ResourceNotFoundException("order does not exists"); 
        }
        orderRepo.deleteById(id);
    }
}

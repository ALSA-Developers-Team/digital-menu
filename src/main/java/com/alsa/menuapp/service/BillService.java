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
import com.alsa.menuapp.exception.UpdateFaildeException;
import com.alsa.menuapp.model.Bill;
import com.alsa.menuapp.repository.BillRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BillService {
    @Autowired
    private BillRepository billRepo;

    public List<Bill> getAllBills(){
        log.info("fetching all bills");
        return billRepo.findAll();
    }

    public Bill getBill(int id){
        log.info("fetching bill with id: {}", id);
        Bill existsBill = billRepo.findById(id).orElse(null);
        if(existsBill == null){
            log.error("bill with id: '{}'", id);
            throw new ResourceNotFoundException("bill does not exists"); 
        }
        return existsBill;
    }

    public Bill saveBill(Bill bill){
        log.info("saving new bill: {}", bill);
        bill.setDate(new Date());
        return billRepo.save(bill);
    }

    public Bill updateBill(int id, Map<Object, Object> fields){
        log.info("updating bill with id: {}", id);
        Bill existsBill = billRepo.findById(id).orElse(null);
        if(existsBill == null){
            log.error("bill with id: {} does not exists", id);
            throw new ResourceNotFoundException("bill does not exists"); 
        }
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Bill.class, (String) key);
            if(field != null){
                field.setAccessible(true);
                ReflectionUtils.setField(field, existsBill, value);
            }else{
                log.error("error happened while updating bill with id: {}", id);
                throw new UpdateFaildeException("error while updating");
            }
        });
        
        return billRepo.save(existsBill);
    }

    public void deleteBill(int id){
        log.info("deleting bill with id: {}", id);
        Bill existsBill = billRepo.findById(id).orElse(null);
        if(existsBill == null){
            log.error("bill with id: '{}'", id);
            throw new ResourceNotFoundException("bill does not exists"); 
        }
        billRepo.deleteById(id);
    }
}

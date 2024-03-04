package com.example.springboot.service.impl;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.entity.Orders;
import com.example.springboot.repository.OrderRepo;
import com.example.springboot.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
   @Autowired
   private OrderRepo repo;

   @Transactional(value = TxType.REQUIRED)
   @Override
   public Orders insert(Orders order) {
      return repo.saveAndFlush(order);
   }
}

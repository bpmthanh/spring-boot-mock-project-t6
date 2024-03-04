package com.example.springboot.service.impl;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.dto.CartDetailDTO;
import com.example.springboot.entity.Orders;
import com.example.springboot.repository.OrderDetailRepo;
import com.example.springboot.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepo repo;

    @Transactional(value = TxType.REQUIRED)
    @Override
    public void insert(CartDetailDTO cartDetailDTO) {
        repo.insert(cartDetailDTO);
    }
}

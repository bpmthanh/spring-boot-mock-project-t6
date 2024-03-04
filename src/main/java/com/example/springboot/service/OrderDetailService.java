package com.example.springboot.service;

import com.example.springboot.dto.CartDetailDTO;
import com.example.springboot.entity.Orders;

public interface OrderDetailService {
    void insert(CartDetailDTO cartDetailDTO);
}

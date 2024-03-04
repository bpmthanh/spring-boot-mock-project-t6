package com.example.springboot.service;

import com.example.springboot.dto.CartDTO;
import com.example.springboot.entity.Users;

public interface CartService {
    CartDTO updateCartDetail(CartDTO cart, Long productId, Integer quantity, boolean isReplace);

    Integer getTotalQuantity(CartDTO cart);

    Double getTotalPrice(CartDTO cart);

    void insert(CartDTO cartDTO, Users user, String address, String phone) throws Exception;
}

package com.example.springboot.service;

import com.example.springboot.dto.CartDTO;

public interface CartService {
    CartDTO updateCartDetail(CartDTO cart, Long productId, Integer quan, boolean isReplace);
    Integer getTotalQuantity(CartDTO cart);
    Double getTotalPrice(CartDTO cart);
}

package com.example.springboot.service.impl;

import com.example.springboot.dto.CartDTO;
import com.example.springboot.dto.CartDetailDTO;
import com.example.springboot.entity.Products;
import com.example.springboot.service.CartService;
import com.example.springboot.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    ProductsService productsService;

    @Override
    public CartDTO updateCartDetail(CartDTO cart, Long productId, Integer quan, boolean isReplace) {
        Products product = productsService.findById(productId);
        HashMap<Long, CartDetailDTO> details = cart.getDetails();

        // Thêm mới
        if (!details.containsKey(productId)) {
            CartDetailDTO cartDetailDTO = createNewCartDetail(product, quan);
            details.put(productId, cartDetailDTO);
        }

        // Update
        else if (quan > 0) {
            // Thay thế
            if (isReplace) {

                details.get(productId).setQuan(quan);

            }
            // Cong don
            else {

                Integer currentQuan = details.get(productId).getQuan();
                Integer newQuan = currentQuan + quan;
                details.get(productId).setQuan(newQuan);
            }
        }
        // Xoa
        else {
            details.remove(productId);
        }
        cart.setTotalQuan(getTotalQuantity(cart));
        cart.setTotalPrice(getTotalPrice(cart));
        return cart;
    }

    @Override
    public Integer getTotalQuantity(CartDTO cart) {
        Integer totalQuantity = 0;
        HashMap<Long, CartDetailDTO> details = cart.getDetails();

        if (details != null) {
            for (CartDetailDTO cartDetail : details.values()) {
                if (cartDetail.getQuan() != null) {
                    totalQuantity += cartDetail.getQuan();
                }
            }
        }

        return totalQuantity;
    }

    @Override
    public Double getTotalPrice(CartDTO cart) {
        Double totalPrice = 0D;
        HashMap<Long, CartDetailDTO> details = cart.getDetails();

        if (details != null) {
            for (CartDetailDTO cartDetail : details.values()) {
                if (cartDetail.getPrice() != null) {
                    totalPrice += cartDetail.getPrice();
                }
            }
        }

        return totalPrice;
    }

    private CartDetailDTO createNewCartDetail(Products product, Integer quan) {
        CartDetailDTO cartDetailDTO = new CartDetailDTO();
        cartDetailDTO.setProductId(product.getId());
        cartDetailDTO.setQuan(quan);
        cartDetailDTO.setPrice(product.getPrice());
        cartDetailDTO.setImgUrl(product.getImgUrl());
        cartDetailDTO.setSlug(product.getSlug());
        cartDetailDTO.setName(product.getName());
        return cartDetailDTO;
    }
}

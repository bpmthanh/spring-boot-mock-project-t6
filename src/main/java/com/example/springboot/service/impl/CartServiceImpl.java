package com.example.springboot.service.impl;

import com.example.springboot.dto.CartDTO;
import com.example.springboot.dto.CartDetailDTO;
import com.example.springboot.entity.Products;
import com.example.springboot.entity.Users;
import com.example.springboot.entity.Orders;
import com.example.springboot.service.CartService;
import com.example.springboot.service.OrderDetailService;
import com.example.springboot.service.OrderService;
import com.example.springboot.service.ProductsService;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import javax.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    ProductsService productsService;

    @Autowired
    CartService cartService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailService orderDetailService;

    @Override
    public CartDTO updateCartDetail(CartDTO cart, Long productId, Integer quantity, boolean isReplace) {
        Products product = productsService.findById(productId);
        HashMap<Long, CartDetailDTO> details = cart.getDetails();

        // Thêm mới
        if (!details.containsKey(productId)) {
            CartDetailDTO cartDetailDTO = createNewCartDetail(product, quantity);
            details.put(productId, cartDetailDTO);
        }

        // Update
        else if (quantity > 0) {
            // Thay thế
            if (isReplace) {
                details.get(productId).setQuantity(quantity);

            }
            // Cong don
            else {
                Integer currentquantity = details.get(productId).getQuantity();
                Integer newquantity = currentquantity + quantity;
                details.get(productId).setQuantity(newquantity);
            }
        }
        // Xoa
        else {
            details.remove(productId);
        }
        cart.setTotalQuantity(getTotalQuantity(cart));
        cart.setTotalPrice(getTotalPrice(cart));
        return cart;
    }

    @Override
    public Integer getTotalQuantity(CartDTO cart) {
        Integer totalquantity = 0;
        HashMap<Long, CartDetailDTO> details = cart.getDetails();

        if (details != null) {
            for (CartDetailDTO cartDetail : details.values()) {
                if (cartDetail.getQuantity() != null) {
                    totalquantity += cartDetail.getQuantity();
                }
            }
        }

        return totalquantity;
    }

    @Override
    public Double getTotalPrice(CartDTO cart) {
        Double totalPrice = 0D;
        HashMap<Long, CartDetailDTO> details = cart.getDetails();

        if (details != null) {
            for (CartDetailDTO cartDetail : details.values()) {
                if (cartDetail.getPrice() != null) {
                    totalPrice += (cartDetail.getPrice() * cartDetail.getQuantity());
                }
            }
        }

        return totalPrice;
    }

    // Sau này nếu mà bị bug thì bỏ value = TxType đi
    @Transactional(rollbackOn = { Exception.class })
    @Override
    public void insert(CartDTO cartDTO, Users user, String address, String phone) throws Exception {
        // Insert vào bảng Orders
        Orders order = new Orders();
        order.setUsers(user);
        order.setAddress(address);
        order.setPhone(phone);

        Orders orderRes = orderService.insert(order);
        if (ObjectUtils.isEmpty(orderRes)) {
            throw new Exception("Insert into order table failed!");
        }

        // Insert vào bảng Order details
        for (CartDetailDTO cartDetailDTO : cartDTO.getDetails().values()) {
            cartDetailDTO.setOrderId(orderRes.getId());
            orderDetailService.insert(cartDetailDTO);

            // Update new quantity cho bằng Products
            Products product = productsService.findById(cartDetailDTO.getProductId());
            Integer newquantity = product.getQuantity() - cartDetailDTO.getQuantity();
            productsService.updateQuantity(newquantity, product.getId());
        }
    }

    private CartDetailDTO createNewCartDetail(Products product, Integer quantity) {
        CartDetailDTO cartDetailDTO = new CartDetailDTO();
        cartDetailDTO.setProductId(product.getId());
        cartDetailDTO.setQuantity(quantity);
        cartDetailDTO.setPrice(product.getPrice());
        cartDetailDTO.setImgUrl(product.getImgUrl());
        cartDetailDTO.setSlug(product.getSlug());
        cartDetailDTO.setName(product.getName());
        return cartDetailDTO;
    }
}

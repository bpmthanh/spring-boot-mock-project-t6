package com.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO implements Serializable {
    private Long orderId;
    private String address;
    private String phone;
    private Double totalPrice = 0D;
    private Integer totalQuantity = 0;
    private HashMap<Long, CartDetailDTO> details = new HashMap<>();
}

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
public class CartDetailDTO implements Serializable {
    private Long orderId;
    private Long productId;
    private String name;
    private String imgUrl;
    private String slug;
    private Double price ;
    private Integer quan;
}

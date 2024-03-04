package com.example.springboot.service;

import com.example.springboot.entity.Products;

import java.util.List;

public interface ProductsService {
    List<Products> findAll();
    Products findById(Long id);
    Products findBySlug(String slug);
    List<Products> findAllAvailable();
    List<Products> findByIsDeletedAndQuantityGreaterThan();
    void updateQuantity(Integer newQuantity, Long id);
}

package com.example.springboot.service.impl;

import com.example.springboot.entity.Products;
import com.example.springboot.repository.ProductsRepo;
import com.example.springboot.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService {
    @Autowired
    private ProductsRepo repo;

    @Override
    public List<Products> findAll() {
        return repo.findAll();
    }

    @Override
    public Products findById(Long id) {
        Optional<Products> result = repo.findById(id);
        return result.isPresent() ? result.get() : null;
    }

    @Override
    public Products findBySlug(String slug) {
        return repo.findBySlug(slug);
    }

    @Override
    public List<Products> findAllAvailable() {
        return repo.findAllAvailable();
    }
    @Override
    public List<Products> findByIsDeletedAndQuantityGreaterThan() {
        return repo.findByIsDeletedAndQuantityGreaterThan(Boolean.FALSE, 0);
    }
}

package com.example.springboot.api;

import com.example.springboot.entity.Products;
import com.example.springboot.service.ProductsService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductApi {
    @Autowired
    private ProductsService productsService;

    // /api/products
    @GetMapping("")
    public ResponseEntity<List<Products>> doGetAll() {
        List<Products> products = productsService.findAll();
        if (ObjectUtils.isEmpty(products)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(products);
    }

    // /api/products/find?slug={...}
    @GetMapping("/find-by-slug")
    public ResponseEntity<Products> doGetBySlug(@RequestParam("slug") String slug) {
        Products product = productsService.findBySlug(slug);
        if(ObjectUtils.isEmpty(product)){
            return new ResponseEntity<Products>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(product);
    }

    // /api/products/find/id/{...}
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Products> doGetById(@PathVariable("id") Long id) {
        Products product = productsService.findById(id);
        if(ObjectUtils.isEmpty(product)){
            return new ResponseEntity<Products>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(product);
    }
}

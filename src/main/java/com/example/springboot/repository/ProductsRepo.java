package com.example.springboot.repository;

import com.example.springboot.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// Dùng để truy vấn những SQL của repo tương ứng
@Repository
public interface ProductsRepo extends JpaRepository<Products, Long> {
    List<Products> findByProductType_Id(Long typeId);

    Products findBySlug(String slug);

    List<Products> findByIsDeletedAndQuantityGreaterThan(Boolean isDeleted, Integer quantity);

    @Query(value = "SELECT * FROM products WHERE isDeleted = 0 AND quantity > 0", nativeQuery = true)
    List<Products> findAllAvailable();

    @Query(value = "update products set quantity = ?1 where id = ?2", nativeQuery = true)
    void updateQuantity(Integer newQuantity, Long id);
}

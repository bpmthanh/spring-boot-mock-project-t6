package com.example.springboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.springboot.entity.Orders;

// Dùng để truy vấn những SQL của repo tương ứng
@Repository
public interface OrderRepo extends JpaRepository<Orders, Long> {
    

}

package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springboot.dto.CartDetailDTO;
import com.example.springboot.entity.OrderDetails;

// Dùng để truy vấn những SQL của repo tương ứng
@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetails, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = "insert into order_details(orderId, productId, price, quantity) values(:#{#dto.orderId}, :#{#dto.productId}, :#{#dto.price}, :#{#dto.quantity})", nativeQuery = true)
    void insert(@Param("dto") CartDetailDTO cartDetailDTO);
}

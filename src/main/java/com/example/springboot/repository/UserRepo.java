package com.example.springboot.repository;

import com.example.springboot.entity.Products;
import com.example.springboot.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
    Users findByUserName(String userName);
    boolean existsByUserName(String userName);
}

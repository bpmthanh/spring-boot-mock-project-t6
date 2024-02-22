package com.example.springboot.repository;

import com.example.springboot.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepo extends JpaRepository<Roles,Long> {
    Roles findByDes(String des);
}

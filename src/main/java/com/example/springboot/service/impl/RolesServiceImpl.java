package com.example.springboot.service.impl;

import com.example.springboot.constant.RolesConstant;
import com.example.springboot.entity.Roles;
import com.example.springboot.repository.RolesRepo;
import com.example.springboot.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesServiceImpl implements RolesService {
    @Autowired
    private RolesRepo repo;

    @Override
    public Roles findByDescription(String description) {
        return repo.findByDescription(RolesConstant.ROLE_USER);
    }
}

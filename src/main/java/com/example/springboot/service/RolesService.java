package com.example.springboot.service;

import com.example.springboot.entity.Roles;
import com.example.springboot.entity.Users;

public interface RolesService {
    Roles findByDes(String des);
}

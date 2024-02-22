package com.example.springboot.service;

import com.example.springboot.entity.Users;

import java.sql.SQLException;

public interface UserService {
    Users doLogin(Users userReq);
    Users Save(Users user) throws SQLException;
    boolean existsByUserName(String userName);
}

package com.example.springboot.service.impl;

import com.example.springboot.constant.RolesConstant;
import com.example.springboot.entity.Roles;
import com.example.springboot.entity.Users;
import com.example.springboot.repository.RolesRepo;
import com.example.springboot.repository.UserRepo;
import com.example.springboot.service.RolesService;
import com.example.springboot.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
    @Autowired
    private UserRepo repo;

    @Autowired
    private RolesService rolesService;

    @Override
    public Users doLogin(Users userReq) {
        Users userRes = repo.findByUserName(userReq.getUserName());
        if (ObjectUtils.isNotEmpty(userRes)) {
            boolean checkPassword = bcrypt.matches(userReq.getHashPassword(), userRes.getHashPassword());
            return checkPassword ? userRes : null;
        }
        return userRes;
    }

    @Override
    @Transactional(rollbackOn = {Throwable.class})
    public Users Save(Users user) throws SQLException {
        Roles role = rolesService.findByDes(RolesConstant.ROLE_USER);
        user.setRoles(role);
        user.setIsDeleted(Boolean.FALSE);
        user.setHashPassword(bcrypt.encode(user.getHashPassword()));
        return repo.saveAndFlush(user);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return repo.existsByUserName(userName);
    }
}

package com.example.springboot.util;

import com.example.springboot.constant.SessionConstant;
import com.example.springboot.dto.CartDTO;
import com.example.springboot.entity.Users;

import javax.servlet.http.HttpSession;

import static com.example.springboot.constant.SessionConstant.*;

public class SessionUtil {
    public static CartDTO getCurrentCart(HttpSession session) {
        return (CartDTO) session.getAttribute(CURRENT_CART);
    }

    public static Users getCurrentUser(HttpSession session) {
        return (Users) session.getAttribute(CURRENT_USER);
    }
}

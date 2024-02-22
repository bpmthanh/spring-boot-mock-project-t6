package com.example.springboot.util;

import com.example.springboot.constant.SessionConstant;
import com.example.springboot.dto.CartDTO;

import javax.servlet.http.HttpSession;

import static com.example.springboot.constant.SessionConstant.CURRENT_CART;

public class SessionUtil {
    public static CartDTO getCurrentCart(HttpSession session){
        return (CartDTO) session.getAttribute(CURRENT_CART);
    }
}

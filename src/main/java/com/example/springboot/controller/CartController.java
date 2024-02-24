package com.example.springboot.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springboot.dto.CartDTO;
import com.example.springboot.service.CartService;
import com.example.springboot.util.SessionUtil;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("")
    public String doViewCart(){
        return "user/cart";
    }

    // /cart/update?productId=...&quantity=...&isReplace=...
    @GetMapping("/update")
    public String doGetUpdate(@RequestParam("productId") Long productId, @RequestParam("quantity") Integer quantity, @RequestParam("isReplace") Boolean isReplace, HttpSession session) {
        CartDTO currentCart = SessionUtil.getCurrentCart(session);
        cartService.updateCartDetail(currentCart, productId, quantity, isReplace);
        return "user/cart::#view-cart-fragment";
    }
}

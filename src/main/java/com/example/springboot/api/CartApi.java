package com.example.springboot.api;

import com.example.springboot.constant.SessionConstant;
import com.example.springboot.dto.CartDTO;
import com.example.springboot.entity.Products;
import com.example.springboot.entity.Users;
import com.example.springboot.service.CartService;
import com.example.springboot.util.SessionUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/cart")
public class CartApi {
    // /api/cart/update?productId=...&quantity=...&isReplace=...

    @Autowired
    private CartService cartService;

    @GetMapping("/update")
    public ResponseEntity<?> doGetUpdate(@RequestParam("productId") Long productId,
            @RequestParam("quantity") Integer quantity, @RequestParam("isReplace") Boolean isReplace,
            HttpSession session) {
        CartDTO currentCart = SessionUtil.getCurrentCart(session);
        cartService.updateCartDetail(currentCart, productId, quantity, isReplace);
        return ResponseEntity.ok(currentCart);
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> doGetRefreshData(HttpSession session) {
        CartDTO currentCart = SessionUtil.getCurrentCart(session);
        return ResponseEntity.ok(currentCart);
    }

    @GetMapping("/checkout")
    public ResponseEntity<?> doGetCheckout(@RequestParam("address") String address,
            @RequestParam("phone") String phone, HttpSession session) {
        Users currentUsers = SessionUtil.getCurrentUser(session);
        if (ObjectUtils.isEmpty(currentUsers)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        CartDTO currentCart = SessionUtil.getCurrentCart(session);
        try {
            cartService.insert(currentCart, currentUsers, address, phone);
            session.setAttribute(SessionConstant.CURRENT_CART, new CartDTO());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

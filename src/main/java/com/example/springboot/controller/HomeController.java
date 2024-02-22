package com.example.springboot.controller;

import com.example.springboot.constant.SessionConstant;
import com.example.springboot.entity.Products;
import com.example.springboot.entity.Users;
import com.example.springboot.service.ProductsService;
import com.example.springboot.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductsService productsService;

    @GetMapping(value = {"/", ""})
    public String doGetRedirectIndex() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String doGetIndex(Model model) {
        List<Products> products = productsService.findByIsDeletedAndQuantityGreaterThan();
        model.addAttribute("products", products);
        return "user/index";
    }

    @GetMapping("/login")
    public String doGetLogin(Model model) {
        model.addAttribute("userReq", new Users());
        return "user/login";
    }

    @PostMapping("/login")
    public String doPostLogin(@ModelAttribute("userReq") Users userReq, HttpSession session) {
        Users userRes = userService.doLogin(userReq);
        if (ObjectUtils.isNotEmpty(userRes)) {
            session.setAttribute(SessionConstant.CURRENT_USER, userRes);
            return "redirect:/index";
        }
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String doGetLogout(HttpSession session) {
        session.removeAttribute(SessionConstant.CURRENT_USER);
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String doGetResgister(Model model) {
        model.addAttribute("userReq", new Users());
        return "user/register";
    }

    @PostMapping("/register")
    public String doPostRegister(@ModelAttribute("userReq") Users userReq, HttpSession session) {
        try {
            if (userService.existsByUserName(userReq.getUserName())) {
                return "redirect:/register";
            }
            Users userRes = userService.Save(userReq);
            if (ObjectUtils.isNotEmpty(userRes)) {
                session.setAttribute(SessionConstant.CURRENT_USER, userRes);
                return "redirect:/index";
            }
        } catch (Exception ex) {
            return "redirect:/register";
        }
        return "redirect:/register";
    }
}

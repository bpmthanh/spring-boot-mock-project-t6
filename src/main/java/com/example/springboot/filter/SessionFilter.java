package com.example.springboot.filter;

import com.example.springboot.constant.SessionConstant;
import com.example.springboot.dto.CartDTO;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class SessionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        validateCart(session);
        filterChain.doFilter(httpServletRequest,servletResponse);
    }

    private void validateCart(HttpSession session){
        if (ObjectUtils.isEmpty(session.getAttribute(SessionConstant.CURRENT_CART))) {
            session.setAttribute(SessionConstant.CURRENT_CART, new CartDTO());
        }
    }
}

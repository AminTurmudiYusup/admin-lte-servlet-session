package com.servlet.adminlte.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        HttpSession session= request.getSession(false);

        if (session==null ||session.getAttribute("user")==null){
            response.sendRedirect(request.getContextPath()+"/login");
        }else{
            filterChain.doFilter(request, response);
        }
    }
}

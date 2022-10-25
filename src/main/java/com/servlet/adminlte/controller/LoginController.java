package com.servlet.adminlte.controller;

import com.servlet.adminlte.entity.User;
import com.servlet.adminlte.repository.UserRepository;
import com.servlet.adminlte.service.Md5Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class LoginController extends HttpServlet {

    private UserRepository userRepository=new UserRepository();
    private Md5Service md5Service = new  Md5Service();
    private static final Charset UTF_8= StandardCharsets.UTF_8;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email=req.getParameter("email");
        String password=req.getParameter("password");
        if (email==null||password==null){
            req.setAttribute("message","username or password null");
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req,resp);
        }
        User user = userRepository.findByUsername(email);
        byte[] bytesPassword= md5Service.digest(password.getBytes(UTF_8));
        String hexPassword= md5Service.byteToHex(bytesPassword);
        if(user==null){
            req.setAttribute("message","username or password invalid");
            doGet(req, resp);
        } else if (!user.getUserPassword().equals(hexPassword)||!user.getUserName().equals(email)) {
            req.setAttribute("message","username or password invalid");
            doGet(req, resp);
        }else {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("home-servlet");
        }

    }
}

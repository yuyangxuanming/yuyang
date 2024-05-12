package com.example.demo4;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // 获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String captcha = request.getParameter("captcha");
        // 这里应该有一个验证码的校验逻辑
        // 假设session中存储的正确验证码为correctCaptcha
        HttpSession session1 = request.getSession();
        String correctCaptcha = (String) session1.getAttribute("vcode");
        // 检查验证码是否正确
        if (correctCaptcha != null && correctCaptcha.equals(captcha)) {
            // 验证码正确，进行用户名和密码的校验
            // 这里应该是您的用户名和密码校验逻辑
            if ("admin".equals(username) && "1".equals(password)) {
                // 登录成功，重定向到主页或其他页面
                response.sendRedirect("home.jsp");
            } else {
                // 登录失败，显示错误信息
                request.setAttribute("error", "用户名或密码错误");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else {
            // 验证码错误，显示错误信息
            request.setAttribute("error", "验证码错误");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        request.removeAttribute("error");
    }
}
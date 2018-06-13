package com.lxhdj.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wangguijun1 on 2018/4/2.
 */
@WebServlet(name = "cookieServlet", urlPatterns = "/cookie")
public class CookieServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(request.getRequestedSessionId());
        javax.servlet.http.Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (javax.servlet.http.Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("userName".equals(name)) {
                    request.setAttribute("userName", cookie.getValue());
                    request.getRequestDispatcher("/cookie.jsp").forward(request, response);
                    return;
                }
            }
        }
        String userName = (String) request.getParameter("userName");
        String userPwd = (String) request.getParameter("userPwd");
        if ("wgj".equals(userName) && "123456".equals(userPwd)) {
            javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("userName", userName);
            response.addCookie(cookie);
            request.setAttribute("userName", userName);
            request.getRequestDispatcher("/cookie.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/loginCookie.jsp").forward(request, response);
        }
    }
}

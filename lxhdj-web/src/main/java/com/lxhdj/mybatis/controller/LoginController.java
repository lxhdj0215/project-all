package com.lxhdj.mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping("login")
    public String login(String userName, String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionId = request.getRequestedSessionId();
        String localName = request.getLocalName();
        session.setAttribute("sessionId", sessionId);
        session.setAttribute("localName", localName);
        String sessionUserName = (String) session.getAttribute("userName");
        if (sessionUserName != null) {
            return "manageUI";
        }
        // 首次登录
        if (userName != null && "admin".equals(password)) {
            session.setAttribute("userName", userName);
            return "manageUI";
        }

        return "redirect:/index.jsp";
    }


    @RequestMapping("logout")
    public String logout(HttpSession session) {

        session.removeAttribute("userName");
        session.removeAttribute("url");

        return "redirect:/index.jsp";
    }

}

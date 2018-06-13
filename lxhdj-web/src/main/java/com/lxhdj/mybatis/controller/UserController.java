package com.lxhdj.mybatis.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lxhdj.mybatis.inter.IUserOperation;
import com.lxhdj.mybatis.model.Article;

@Controller
@RequestMapping("/article")
public class UserController {
    @Autowired
    private IUserOperation userMapper;

    @RequestMapping("/list")
    public ModelAndView listall(HttpServletRequest request, HttpServletResponse response) {
        List<Article> articles = userMapper.getUserArticles(1);
        System.out.println(articles);
        ModelAndView mav = new ModelAndView("list");
        mav.addObject("articles", articles);
        return mav;
    }
}
